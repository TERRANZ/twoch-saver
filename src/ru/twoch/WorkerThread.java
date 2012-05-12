package ru.twoch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.twoch.entity.Board;
import ru.twoch.entity.db.Message;
import ru.twoch.entity.db.MessagePersistanceManager;
import ru.twoch.entity.PojoMapper;
import ru.twoch.entity.ThreadDTO;
import ru.twoch.entity.db.ThreadPersistanceManager;
import ru.twoch.entity.Threads;
import ru.twoch.entity.db.TThread;
import ru.twoch.gui.WorkIsDoneListener;

/**
 *
 * @author terranz
 */
public class WorkerThread implements Runnable
{
    private final static String SERVER_URL = "http://2-ch.so";
    private String boardName;
    private final static String WAKABA_URL = "wakaba.json";
    private final static String JSON = ".json";
    private WorkIsDoneListener wdl;
    private Boolean isDownloadImages;

    public WorkerThread(String board, WorkIsDoneListener wd, Boolean downloadImages)
    {
        this.boardName = board;
        this.wdl = wd;
        isDownloadImages = downloadImages;
    }

    @Override
    public void run()
    {
        try
        {
            String result = "";
            URLConnection conn = new URL(SERVER_URL + boardName + WAKABA_URL).openConnection();
            String json = readStreamToString(conn.getInputStream(), "UTF-8");
            //System.out.println("Received json: " + json);
            Board brd = (Board) PojoMapper.fromJson(json, Board.class);
            if (brd != null)
            {
                ThreadPersistanceManager tpm = new ThreadPersistanceManager();
                MessagePersistanceManager mpm = new MessagePersistanceManager();
                //F//ileWriter fstream = new FileWriter("images.txt", true);
                //BufferedWriter out = new BufferedWriter(fstream);
                ExecutorService service = Executors.newFixedThreadPool(20);
                for (Threads ts : brd.getThreads())
                {
                    for (Message m : ts.getPosts().get(0))
                    {
                        //System.out.println("thread: " + m.getNum());
                        result += "thread: " + m.getNum() + "\n";
                        //first message in thread - num of thread

                        TThread t = (ru.twoch.entity.db.TThread) tpm.findById(m.getNum());
                        if (t == null)
                        {
                            t = new TThread(0L, m.getNum(), boardName);
                            tpm.insert(t);
                        }
                        //download thread and save it
                        t = (ru.twoch.entity.db.TThread) tpm.findById(m.getNum());
                        conn = new URL(SERVER_URL + boardName + "/res/" + m.getNum().toString() + JSON).openConnection();
                        json = readStreamToString(conn.getInputStream(), "UTF-8");
                        ThreadDTO tdto = (ThreadDTO) PojoMapper.fromJson(json, ThreadDTO.class);
                        for (List<Message> msgsInThread : tdto.getThread())
                        {
                            Message msg = msgsInThread.get(0);
                            //System.out.println("Message : " + msg.getNum());
                            if (!mpm.isMessageExists(msg.getNum()))
                            {
                                result += "Message " + msg.getNum() + " for thread " + m.getNum() + " cached" + "\n";
                                mpm.insert(msg);
                                if (msg.getImage() != null)
                                {
                                    String url = SERVER_URL + boardName + msg.getImage();
                                    // out.write(url + "\n");
                                    if (isDownloadImages)
                                    {
                                        service.submit(new DownloaderThread(SERVER_URL + boardName + msg.getImage()));
//                                        Thread dt = new Thread(new DownloaderThread(SERVER_URL + boardName + msg.getImage()));
//                                        dt.start();
                                    }
                                }
                            }
                        }
                    }
                }               
                service.shutdown();
                // out.close();
                // fstream.close();
                if (wdl != null)
                {
                    wdl.done(result);
                } else
                {
                    System.out.print(result);
                }
            } else
            {
                System.out.println("board is null");
            }
        } catch (IOException ex)
        {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String readStreamToString(InputStream in, String encoding)
            throws IOException
    {
        StringBuffer b = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));
        String s = "";
        while ((s = br.readLine()) != null)
        {
            //System.out.println(s);
            b.append(s);
        }
        return b.toString();
    }
}
