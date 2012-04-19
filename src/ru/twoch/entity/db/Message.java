package ru.twoch.entity.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author terranz
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByLasthit", query = "SELECT m FROM Message m WHERE m.lasthit = :lasthit"),
    @NamedQuery(name = "Message.findByNum", query = "SELECT m FROM Message m WHERE m.num = :num"),
    @NamedQuery(name = "Message.findByBanned", query = "SELECT m FROM Message m WHERE m.banned = :banned"),
    @NamedQuery(name = "Message.findByMsgdate", query = "SELECT m FROM Message m WHERE m.msgdate = :msgdate"),
    @NamedQuery(name = "Message.findByMsgsize", query = "SELECT m FROM Message m WHERE m.msgsize = :msgsize"),
    @NamedQuery(name = "Message.findByMsgtimestamp", query = "SELECT m FROM Message m WHERE m.msgtimestamp = :msgtimestamp"),
    @NamedQuery(name = "Message.findByClosed", query = "SELECT m FROM Message m WHERE m.closed = :closed"),
    @NamedQuery(name = "Message.findByThumbnail", query = "SELECT m FROM Message m WHERE m.thumbnail = :thumbnail"),
    @NamedQuery(name = "Message.findByParent", query = "SELECT m FROM Message m WHERE m.parent = :parent"),
    @NamedQuery(name = "Message.findByVideo", query = "SELECT m FROM Message m WHERE m.video = :video"),
    @NamedQuery(name = "Message.findByName", query = "SELECT m FROM Message m WHERE m.name = :name"),
    @NamedQuery(name = "Message.findByImage", query = "SELECT m FROM Message m WHERE m.image = :image"),
    @NamedQuery(name = "Message.findByComment", query = "SELECT m FROM Message m WHERE m.comment = :comment"),
    @NamedQuery(name = "Message.findByOp", query = "SELECT m FROM Message m WHERE m.op = :op")
})
public class Message implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "lasthit", nullable = false)
    private long lasthit = 0L;
    @Id
    @Basic(optional = false)
    @Column(name = "num", nullable = false)
    private Long num = 0L;
    @Basic(optional = false)
    @Column(name = "banned", nullable = false)
    private long banned = 0L;
    @Basic(optional = false)
    @Column(name = "msgdate", nullable = false, length = 60)
    private String date = "";
    @Basic(optional = false)
    @Column(name = "msgsize", nullable = false)
    private long size = 0L;
    @Basic(optional = false)
    @Column(name = "msgtimestamp", nullable = false)
    private long timestamp = 0L;
    @Basic(optional = false)
    @Column(name = "closed", nullable = false)
    private long closed = 0L;
    @Basic(optional = true)
    @Column(name = "thumbnail", nullable = true, length = 150)
    private String thumbnail = "";
    @Basic(optional = false)
    @Column(name = "parent", nullable = false)
    private long parent = 0L;
    @Basic(optional = true)
    @Column(name = "video", nullable = true, length = 150)
    private String video = "";
    @Basic(optional = true)
    @Lob
    @Column(name = "subject", nullable = true, length = 65535)
    private String subject = "";
    @Basic(optional = true)
    @Column(name = "name", nullable = true, length = 50)
    private String name = "";
    @Basic(optional = true)
    @Column(name = "image", nullable = true, length = 150)
    private String image = "";
    @Basic(optional = false)
    @Column(name = "comment", nullable = false, length = 1024)
    private String comment = "";
    @Basic(optional = false)
    @Column(name = "op", nullable = false)
    private long op = 0L;
    private Long width = 0L;
    private String sticky = "";
    private Long tn_width = 0L;
    private Long height = 0L;
    private Long tn_height = 0L;
    private String bannnned = "";

    public String getBannnned()
    {
        return bannnned;
    }

    public void setBannnned(String bannnned)
    {
        this.bannnned = bannnned;
    }

    public Long getTn_height()
    {
        return tn_height;
    }

    public void setTn_height(Long tn_height)
    {
        this.tn_height = tn_height;
    }

    public Long getHeight()
    {
        return height;
    }

    public void setHeight(Long height)
    {
        this.height = height;
    }

    public Long getTn_width()
    {
        return tn_width;
    }

    public void setTn_width(Long tn_width)
    {
        this.tn_width = tn_width;
    }

    public String getSticky()
    {
        return sticky;
    }

    public void setSticky(String sticky)
    {
        this.sticky = sticky;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public Long getWidth()
    {
        return width;
    }

    public void setWidth(Long width)
    {
        this.width = width;
    }

    public Message()
    {
    }

    public Message(Long num)
    {
        this.num = num;
    }

    public Message(Long num, long lasthit, long banned, String msgdate, long msgsize, long msgtimestamp, long closed, String thumbnail, long parent, String video, String subject, String name, String image, String comment, long op)
    {
        this.num = num;
        this.lasthit = lasthit;
        this.banned = banned;
        this.date = msgdate;
        this.size = msgsize;
        this.timestamp = msgtimestamp;
        this.closed = closed;
        this.thumbnail = thumbnail;
        this.parent = parent;
        this.video = video;
        this.subject = subject;
        this.name = name;
        this.image = image;
        this.comment = comment;
        this.op = op;
    }

    public long getLasthit()
    {
        return lasthit;
    }

    public void setLasthit(long lasthit)
    {
        this.lasthit = lasthit;
    }

    public Long getNum()
    {
        return num;
    }

    public void setNum(Long num)
    {
        this.num = num;
    }

    public long getBanned()
    {
        return banned;
    }

    public void setBanned(long banned)
    {
        this.banned = banned;
    }

    public String getMsgdate()
    {
        return date;
    }

    public void setMsgdate(String msgdate)
    {
        this.date = msgdate;
    }

    public long getMsgsize()
    {
        return size;
    }

    public void setMsgsize(long msgsize)
    {
        this.size = msgsize;
    }

    public long getMsgtimestamp()
    {
        return timestamp;
    }

    public void setMsgtimestamp(long msgtimestamp)
    {
        this.timestamp = msgtimestamp;
    }

    public long getClosed()
    {
        return closed;
    }

    public void setClosed(long closed)
    {
        this.closed = closed;
    }

    public String getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public long getParent()
    {
        return parent;
    }

    public void setParent(long parent)
    {
        this.parent = parent;
    }

    public String getVideo()
    {
        return video;
    }

    public void setVideo(String video)
    {
        this.video = video;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public long getOp()
    {
        return op;
    }

    public void setOp(long op)
    {
        this.op = op;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message))
        {
            return false;
        }
        Message other = (Message) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ru.terra.twoch.entity.Message[ num=" + num + " ]";
    }
}
