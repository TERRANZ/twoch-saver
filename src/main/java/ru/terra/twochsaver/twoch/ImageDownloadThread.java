package ru.terra.twochsaver.twoch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author terranz
 */
public class ImageDownloadThread implements Runnable {
    private String url;
    private CountDownLatch countDownLatch;
    private String folder = "2ch-images";
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    public ImageDownloadThread(String url, String threadId, CountDownLatch countDownLatch) {
        this.url = url;
        this.countDownLatch = countDownLatch;

        folder += "/" + threadId + "/";
        File f = new File(folder);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    @Override
    public void run() {
        logger.info("Starting to download " + url);
        for (int i = 0; i <= 2; i++) {
            FileOutputStream fos = null;
            try {
                URL google = new URL(url);
                ReadableByteChannel rbc = Channels.newChannel(google.openStream());
                fos = new FileOutputStream(folder + url.substring(url.lastIndexOf("/")));
                fos.getChannel().transferFrom(rbc, 0, 1 << 24);
                break;
            } catch (IOException ex) {
                Logger.getLogger(ImageDownloadThread.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (fos != null)
                    try {
                        fos.close();
                    } catch (IOException e) {
                        Logger.getLogger(ImageDownloadThread.class.getName()).log(Level.SEVERE, null, e);
                    }
            }
        }
        logger.info("Image downloading done");
        if (countDownLatch != null)
            countDownLatch.countDown();
    }
}
