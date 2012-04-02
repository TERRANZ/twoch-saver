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
@TemplateId(id = Constants.THREAD_TEMPLATE_ID)
public class Thread implements Serializable
{
    private Integer id;
    private Integer threadId;
    private Integer startMessage;

    public Thread()
    {
    }

    public Thread(Integer id)
    {
        this.id = id;
    }

    public Thread(Integer id, Integer startMessage, Integer threadId)
    {
        this.id = id;
        this.startMessage = startMessage;
        this.threadId = threadId;
    }

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

    @PropGetter(id = Constants.THREAD_START_MESSAGE, type = TOPropertyType.TYPE_INT)
    public Integer getStartMessage()
    {
        return startMessage;
    }

    @PropSetter(id = Constants.THREAD_START_MESSAGE, type = TOPropertyType.TYPE_INT)
    public void setStartMessage(Integer startMessage)
    {
        this.startMessage = startMessage;
    }

    @PropGetter(id = Constants.THREAD_ID, type = TOPropertyType.TYPE_INT)
    public Integer getThreadId()
    {
        return threadId;
    }

    @PropSetter(id = Constants.THREAD_ID, type = TOPropertyType.TYPE_INT)
    public void setThreadId(Integer threadId)
    {
        this.threadId = threadId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thread))
        {
            return false;
        }
        Thread other = (Thread) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ru.terra.twoch.entity.Thread[ id=" + id + " ]";
    }
}
