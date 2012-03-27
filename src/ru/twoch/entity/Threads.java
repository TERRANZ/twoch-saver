package ru.twoch.entity;

import ru.twoch.entity.db.Message;
import java.util.List;

/**
 *
 * @author terranz
 */
public class Threads
{
    private Long image_count;
    private Long omitimages;
    private List<List<Message>> posts;
    private Long omit;
    private Long reply_count;

    public Long getImage_count()
    {
        return image_count;
    }

    public void setImage_count(Long image_count)
    {
        this.image_count = image_count;
    }

    public Long getOmitimages()
    {
        return omitimages;
    }

    public void setOmitimages(Long omitimages)
    {
        this.omitimages = omitimages;
    }

    public Long getOmit()
    {
        return omit;
    }

    public void setOmit(Long omit)
    {
        this.omit = omit;
    }

    public Long getReply_count()
    {
        return reply_count;
    }

    public void setReply_count(Long reply_count)
    {
        this.reply_count = reply_count;
    }

    public List<List<Message>> getPosts()
    {
        return posts;
    }

    public void setPosts(List<List<Message>> posts)
    {
        this.posts = posts;
    }
}
