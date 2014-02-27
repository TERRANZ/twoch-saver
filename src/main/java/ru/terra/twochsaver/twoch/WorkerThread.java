package ru.terra.twochsaver.twoch;

import flexjson.JSONDeserializer;
import org.apache.log4j.Logger;
import ru.terra.twochsaver.twoch.dto.BoardDTO;
import ru.terra.twochsaver.twoch.dto.PostDTO;
import ru.terra.twochsaver.twoch.dto.ThreadDTO;
import ru.terra.twochsaver.twoch.model.JPAModelImpl;
import ru.terra.twochsaver.twoch.model.Model;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author terranz
 */
public class WorkerThread implements Runnable {
    private final static String SERVER_URL = "http://2ch.hk/";
    private String boardName;
    private final static String WAKABA_URL = "wakaba.json";
    private final static String JSON = ".json";
    private WorkIsDoneListener wdl;
    private Boolean isDownloadImages;
    private Model model = new JPAModelImpl();
    private Logger logger = Logger.getLogger(this.getClass());
    private volatile int threads = 0;
    private List<Pair<String, String>> imagesToDownload = new ArrayList<Pair<String, String>>();
    private CountDownLatch countDownLatch;

    public WorkerThread(String board, WorkIsDoneListener wd, Boolean downloadImages) {
        this.boardName = board;
        this.wdl = wd;
        isDownloadImages = downloadImages;
    }

    @Override
    public void run() {
        try {
            String result = "";
            BoardDTO brd = null;
            URLConnection conn = null;
            boolean success = false;

            for (int i = 0; i <= 2; i++) {
                System.out.println("Loading board " + i + " try");
                try {
                    conn = new URL(SERVER_URL + boardName + WAKABA_URL).openConnection();
                    conn.setConnectTimeout(10000);
                    brd = new JSONDeserializer<BoardDTO>().deserialize(new InputStreamReader(conn.getInputStream()), BoardDTO.class);
                    success = true;
                    logger.info("Board loaded");
                    break;
                } catch (Exception e) {
                    logger.error("Unable to parse json", e);
                }
            }
            if (brd != null && success) {
                final ExecutorService downloadService = Executors.newFixedThreadPool(10);
                WorkIsDoneListener threadDownloadworkIsDoneListener = new WorkIsDoneListener() {
                    @Override
                    synchronized public void done(String... params) {
                        threads--;
                        logger.info("thread loading done, estimated threads : " + threads);
                        ExecutorService downloadImageService = Executors.newFixedThreadPool(10);
                        if (threads == 0) {

                            if (imagesToDownload.size() == 0) {
                                if (wdl != null)
                                    wdl.done();
                            } else {
                                logger.info("Thread downloading complete, starting to download " + imagesToDownload.size() + " images");
                                countDownLatch = new CountDownLatch(imagesToDownload.size());

                                for (Pair<String, String> image : imagesToDownload)
                                    downloadImageService.submit(new ImageDownloadThread(image.arg1, image.arg2, countDownLatch));
                            }
                        }

                        if (countDownLatch != null) {
                            try {
                                countDownLatch.await();
                                downloadService.shutdown();
                                downloadImageService.shutdown();
                            } catch (InterruptedException e) {
                                logger.error("Unable to wait images", e);
                            }

                        }
                    }
                };

                UpdateImageCount updateImageCount = new UpdateImageCount() {
                    @Override
                    public void update(List<Pair<String, String>> images) {
                        synchronized (imagesToDownload) {
                            imagesToDownload.addAll(images);
                            logger.info("Added " + images.size() + " to download list");
                        }
                    }
                };

                threads = brd.threads.size();
                for (ThreadDTO ts : brd.threads) {
                    for (PostDTO m : ts.posts.get(0)) {
                        result += "thread: " + m.num + "\n";
                        if (!model.isThreadExists(m.num)) {
                            model.createThread(0L, m.num, boardName);
                        }
                        logger.info("Loading thread " + m.num);
                        downloadService.submit(new ThreadDownloadAsyncTask(
                                SERVER_URL,
                                boardName,
                                m.num.toString(),
                                isDownloadImages,
                                downloadService,
                                threadDownloadworkIsDoneListener,
                                updateImageCount));
                    }
                }
                //TODO: do not shutdown, cause exception if we allow download images
                //     downloadService.shutdown();

            } else {
                logger.info("Board is null");

            }
        } catch (Exception e) {
            logger.error("Unable to load board", e);
        }


    }
}
