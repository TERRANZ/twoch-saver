package ru.twoch.entity;

import java.util.List;

/**
 *
 * @author terranz
 */
public class ThreadDTO
{
    private List<List<Message>> thread;

    public List<List<Message>> getThread()
    {
        return thread;
    }

    public void setThread(List<List<Message>> thread)
    {
        this.thread = thread;
    }
}
