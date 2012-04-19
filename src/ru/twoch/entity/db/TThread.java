package ru.twoch.entity.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author terranz
 */
@Entity
@Table(name = "thread", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "start_message"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Thread.findAll", query = "SELECT t FROM Thread t"),
    @NamedQuery(name = "Thread.findById", query = "SELECT t FROM Thread t WHERE t.id = :id"),
    @NamedQuery(name = "Thread.findByStartMessage", query = "SELECT t FROM Thread t WHERE t.startMessage = :startMessage")
})
public class TThread implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "start_message", nullable = false)
    private Long startMessage;
    @Column(name = "board", nullable = false)
    private String board;

    public TThread()
    {
    }

    public TThread(Long id)
    {
        this.id = id;
    }

    public TThread(Long id, long startMessage, String board)
    {
        this.id = id;
        this.startMessage = startMessage;
        this.board = board;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getStartMessage()
    {
        return startMessage;
    }

    public void setStartMessage(Long startMessage)
    {
        this.startMessage = startMessage;
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
        if (!(object instanceof TThread))
        {
            return false;
        }
        TThread other = (TThread) object;
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

    public String getBoard()
    {
        return board;
    }

    public void setBoard(String board)
    {
        this.board = board;
    }
}
