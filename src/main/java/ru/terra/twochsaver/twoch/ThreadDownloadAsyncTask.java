package ru.terra.twochsaver.twoch;

import flexjson.JSONDeserializer;
import org.apache.log4j.Logger;
import ru.terra.twochsaver.twoch.dto.PostDTO;
import ru.terra.twochsaver.twoch.dto.SingleThreadDTO;
import ru.terra.twochsaver.twoch.model.JPAModelImpl;
import ru.terra.twochsaver.twoch.model.Model;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Date: 18.11.13
 * Time: 18:21
 */
public class ThreadDownloadAsyncTask implements Runnable {

    private URLConnection conn = null;
    private final String serverUrl;
    private final String boardName;
    private String threadNum;
    private boolean downloadImages;
    private ExecutorService service;
    private WorkIsDoneListener workIsDoneListener;
    private UpdateImageCount updateImageCount;
    private Logger logger = Logger.getLogger(this.getClass());
    private Model model = new JPAModelImpl();


    public ThreadDownloadAsyncTask(
            String serverUrl,
            String boardName,
            String threadNum,
            boolean isDownloadImages,
            ExecutorService service,
            WorkIsDoneListener workIsDoneListener,
            UpdateImageCount updateImageCount) {
        this.serverUrl = serverUrl;
        this.boardName = boardName;
        this.threadNum = threadNum;
        this.downloadImages = isDownloadImages;
        this.service = service;
        this.workIsDoneListener = workIsDoneListener;
        this.updateImageCount = updateImageCount;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 2; i++) {
            try {
                conn = new URL(serverUrl + boardName + "/res/" + threadNum + ".json").openConnection();
                conn.setConnectTimeout(10000);
                SingleThreadDTO tdto = new JSONDeserializer<SingleThreadDTO>().deserialize(new InputStreamReader(conn.getInputStream()), SingleThreadDTO.class);
                List<Pair<String, String>> images = new ArrayList<Pair<String, String>>();
                for (List<PostDTO> msgsInThread : tdto.thread) {
                    final PostDTO msg = msgsInThread.get(0);
                    if (!model.isMessageExists(msg.num)) {
                        model.createMessage(msg);
                        if (msg.image != null) {
                            if (downloadImages)
                                images.add(new Pair<String, String>(serverUrl + boardName + msg.image, threadNum));
                        }
                    }
                }
                updateImageCount.update(images);
                break;
            } catch (Exception e) {
                logger.error("Unable to load thread", e);
            }
        }
        if (workIsDoneListener != null)
            workIsDoneListener.done();
    }
}
