package ru.twoch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author terranz
 */
public class DownloaderThread implements Runnable
{
    private String url;
    private String folder = "2ch-images";
    
    public DownloaderThread(String url)
    {
        this.url = url;
        File f = new File(folder);
        if (!f.exists())
        {
            f.mkdir();
        }
    }
    
    @Override
    public void run()
    {
        System.out.println("Starting to download image " + url);
        try
        {
            URL google = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(google.openStream());
            FileOutputStream fos = new FileOutputStream(folder + url.substring(url.lastIndexOf("/")));
            fos.getChannel().transferFrom(rbc, 0, 1 << 24);
            fos.close();
        } catch (IOException ex)
        {
            Logger.getLogger(DownloaderThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
