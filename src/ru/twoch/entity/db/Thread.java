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
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long threadId;
    private long startMessage;

    public Thread()
    {
    }

    public Thread(Long id)
    {
        this.id = id;
    }

    public Thread(Long id, long startMessage, Long threadId)
    {
        this.id = id;
        this.startMessage = startMessage;
        this.threadId = threadId;
    }

    @PropGetter(autoincrement = true, id = Constants.ID_PROP_ID, type = TOPropertyType.TYPE_LONG)       
    public Long getId()
    {
        return id;
    }

    @PropSetter(id = Constants.ID_PROP_ID, type = TOPropertyType.TYPE_LONG)
    public void setId(Long id)
    {
        this.id = id;
    }

    @PropGetter(id = Constants.THREAD_START_MESSAGE, type = TOPropertyType.TYPE_LONG)
    public long getStartMessage()
    {
        return startMessage;
    }

    @PropSetter(id = Constants.THREAD_START_MESSAGE, type = TOPropertyType.TYPE_LONG)
    public void setStartMessage(long startMessage)
    {
        this.startMessage = startMessage;
    }

    @PropGetter(id = Constants.THREAD_ID, type = TOPropertyType.TYPE_LONG)
    public Long getThreadId()
    {
        return threadId;
    }

    @PropSetter(id = Constants.THREAD_ID, type = TOPropertyType.TYPE_LONG)
    public void setThreadId(Long threadId)
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
