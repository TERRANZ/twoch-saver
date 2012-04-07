package ru.twoch.entity.db;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import ru.terraobjects.entity.TOPropertyType;
import ru.terraobjects.entity.annotations.PropGetter;
import ru.terraobjects.entity.annotations.PropSetter;
import ru.terraobjects.entity.annotations.TemplateId;
import ru.twoch.entity.constants.Constants;

/**
 *
 * @author terranz
 */
@XmlRootElement
@TemplateId(id = Constants.MESSAGE_TEMPLATE_ID)
public class Message implements Serializable
{
    private static final Integer serialVersionUID = 1;
    private Integer lasthit = 0;
    private Integer num = 0;
    private Integer banned = 0;
    private String date = "";
    private Integer size = 0;
    private Integer timestamp = 0;
    private Integer closed = 0;
    private String thumbnail = "";
    private Integer parent = 0;
    private String video = "";
    private String subject = "";
    private String name = "";
    private String image = "";
    private String comment = "";
    private Integer op = 0;
    private Integer width = 0;
    private String sticky = "";
    private Integer tn_width = 0;
    private Integer height = 0;
    private Integer tn_height = 0;
    private String bannnned = "";
    private Integer id;

    @PropGetter(autoincrement = true, id = Constants.ID_PROP_ID, type = TOPropertyType.TYPE_INT)
    public Integer getId()
    {
        return id;
    }

    @PropSetter(id = Constants.ID_PROP_ID, type = TOPropertyType.TYPE_INT)
    public void setId(Integer id)
    {
        this.id = id;
    }

    @PropGetter(id = Constants.MESSAGE_bannnned, type = TOPropertyType.TYPE_STR)
    public String getBannnned()
    {
        return bannnned;
    }

    @PropSetter(id = Constants.MESSAGE_bannnned, type = TOPropertyType.TYPE_STR)
    public void setBannnned(String bannnned)
    {
        this.bannnned = bannnned;
    }

    @PropGetter(id = Constants.MESSAGE_TN_HEIGHT, type = TOPropertyType.TYPE_INT)
    public Integer getTn_height()
    {
        return tn_height;
    }

    @PropSetter(id = Constants.MESSAGE_HEIGHT, type = TOPropertyType.TYPE_INT)
    public void setTn_height(Integer tn_height)
    {
        this.tn_height = tn_height;
    }

    @PropGetter(id = Constants.MESSAGE_HEIGHT, type = TOPropertyType.TYPE_INT)
    public Integer getHeight()
    {
        return height;
    }

    @PropSetter(id = Constants.MESSAGE_HEIGHT, type = TOPropertyType.TYPE_INT)
    public void setHeight(Integer height)
    {
        this.height = height;
    }

    @PropGetter(id = Constants.MESSAGE_TN_WIDTH, type = TOPropertyType.TYPE_INT)
    public Integer getTn_width()
    {
        return tn_width;
    }

    @PropSetter(id = Constants.MESSAGE_TN_WIDTH, type = TOPropertyType.TYPE_INT)
    public void setTn_width(Integer tn_width)
    {
        this.tn_width = tn_width;
    }

    @PropGetter(id = Constants.MESSAGE_STICKY, type = TOPropertyType.TYPE_STR)
    public String getSticky()
    {
        return sticky;
    }

    @PropSetter(id = Constants.MESSAGE_STICKY, type = TOPropertyType.TYPE_STR)
    public void setSticky(String sticky)
    {
        this.sticky = sticky;
    }

    @PropGetter(id = Constants.MESSAGE_DATE, type = TOPropertyType.TYPE_STR)
    public String getDate()
    {
        return date;
    }

    @PropSetter(id = Constants.MESSAGE_DATE, type = TOPropertyType.TYPE_STR)
    public void setDate(String date)
    {
        this.date = date;
    }

    @PropGetter(id = Constants.MESSAGE_SIZE, type = TOPropertyType.TYPE_INT)
    public Integer getSize()
    {
        return size;
    }

    @PropSetter(id = Constants.MESSAGE_SIZE, type = TOPropertyType.TYPE_INT)
    public void setSize(Integer size)
    {
        this.size = size;
    }

    @PropGetter(id = Constants.MESSAGE_TIMESTAMP, type = TOPropertyType.TYPE_INT)
    public Integer getTimestamp()
    {
        return timestamp;
    }

    @PropSetter(id = Constants.MESSAGE_TIMESTAMP, type = TOPropertyType.TYPE_INT)
    public void setTimestamp(Integer timestamp)
    {
        this.timestamp = timestamp;
    }

    @PropGetter(id = Constants.MESSAGE_WIDTH, type = TOPropertyType.TYPE_INT)
    public Integer getWidth()
    {
        return width;
    }

    @PropSetter(id = Constants.MESSAGE_WIDTH, type = TOPropertyType.TYPE_INT)
    public void setWidth(Integer width)
    {
        this.width = width;
    }

    public Message()
    {
    }

    public Message(Integer num)
    {
        this.num = num;
    }

    public Message(Integer num, Integer lasthit, Integer banned, String msgdate, Integer msgsize, Integer msgtimestamp, Integer closed, String thumbnail, Integer parent, String video, String subject, String name, String image, String comment, Integer op)
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

    @PropGetter(id = Constants.MESSAGE_LASTHIT, type = TOPropertyType.TYPE_INT)
    public Integer getLasthit()
    {
        return lasthit;
    }

    @PropSetter(id = Constants.MESSAGE_LASTHIT, type = TOPropertyType.TYPE_INT)
    public void setLasthit(Integer lasthit)
    {
        this.lasthit = lasthit;
    }

    @PropGetter(id = Constants.MESSAGE_NUM, type = TOPropertyType.TYPE_INT)
    public Integer getNum()
    {
        return num;
    }

    @PropSetter(id = Constants.MESSAGE_NUM, type = TOPropertyType.TYPE_INT)
    public void setNum(Integer num)
    {
        this.num = num;
    }

    @PropGetter(id = Constants.MESSAGE_BANNED, type = TOPropertyType.TYPE_INT)
    public Integer getBanned()
    {
        return banned;
    }

    @PropSetter(id = Constants.MESSAGE_BANNED, type = TOPropertyType.TYPE_INT)
    public void setBanned(Integer banned)
    {
        this.banned = banned;
    }

    @PropGetter(id = Constants.MESSAGE_CLOSED, type = TOPropertyType.TYPE_INT)
    public Integer getClosed()
    {
        return closed;
    }

    @PropSetter(id = Constants.MESSAGE_CLOSED, type = TOPropertyType.TYPE_INT)
    public void setClosed(Integer closed)
    {
        this.closed = closed;
    }

    @PropGetter(id = Constants.MESSAGE_THUMBNAIL, type = TOPropertyType.TYPE_STR)
    public String getThumbnail()
    {
        return thumbnail;
    }

    @PropSetter(id = Constants.MESSAGE_THUMBNAIL, type = TOPropertyType.TYPE_STR)
    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    @PropGetter(id = Constants.MESSAGE_PARENT, type = TOPropertyType.TYPE_INT)
    public Integer getParent()
    {
        return parent;
    }

    @PropSetter(id = Constants.MESSAGE_PARENT, type = TOPropertyType.TYPE_INT)
    public void setParent(Integer parent)
    {
        this.parent = parent;
    }

    @PropGetter(id = Constants.MESSAGE_VIDEO, type = TOPropertyType.TYPE_STR)
    public String getVideo()
    {
        return video;
    }

    @PropSetter(id = Constants.MESSAGE_VIDEO, type = TOPropertyType.TYPE_STR)
    public void setVideo(String video)
    {
        this.video = video;
    }

    @PropGetter(id = Constants.MESSAGE_SUBJECT, type = TOPropertyType.TYPE_STR)
    public String getSubject()
    {
        return subject;
    }

    @PropSetter(id = Constants.MESSAGE_SUBJECT, type = TOPropertyType.TYPE_STR)
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @PropGetter(id = Constants.MESSAGE_NAME, type = TOPropertyType.TYPE_STR)
    public String getName()
    {
        return name;
    }

    @PropSetter(id = Constants.MESSAGE_NAME, type = TOPropertyType.TYPE_STR)
    public void setName(String name)
    {
        this.name = name;
    }

    @PropGetter(id = Constants.MESSAGE_IMAGE, type = TOPropertyType.TYPE_STR)
    public String getImage()
    {
        return image;
    }

    @PropSetter(id = Constants.MESSAGE_IMAGE, type = TOPropertyType.TYPE_STR)
    public void setImage(String image)
    {
        this.image = image;
    }

    @PropGetter(id = Constants.MESSAGE_COMMENT, type = TOPropertyType.TYPE_STR)
    public String getComment()
    {
        return comment;
    }

    @PropSetter(id = Constants.MESSAGE_COMMENT, type = TOPropertyType.TYPE_STR)
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    @PropGetter(id = Constants.MESSAGE_OP, type = TOPropertyType.TYPE_INT)
    public Integer getOp()
    {
        return op;
    }

    @PropSetter(id = Constants.MESSAGE_OP, type = TOPropertyType.TYPE_INT)
    public void setOp(Integer op)
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