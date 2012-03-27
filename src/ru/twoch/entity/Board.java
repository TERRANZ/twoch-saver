package ru.twoch.entity;

import java.util.List;

/**
 *
 * @author terranz
 */
public class Board
{
    private List<Threads> threads;
    private List<Page> pages;

    public List<Page> getPages()
    {
        return pages;
    }

    public void setPages(List<Page> pages)
    {
        this.pages = pages;
    }

    public List<Threads> getThreads()
    {
        return threads;
    }

    public void setThreads(List<Threads> threads)
    {
        this.threads = threads;
    }


}
