/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.terra.twochsaver.twoch.db.jpa;

import ru.terra.twochsaver.twoch.dto.PostDTO;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;


/**
 * @author terranz
 */
@Entity
@Table(name = "message", catalog = "2ch", schema = "")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "TMessage.findAll", query = "SELECT t FROM TMessage t"),
        @NamedQuery(name = "TMessage.findByNum", query = "SELECT t FROM TMessage t WHERE t.num = :num"),
        @NamedQuery(name = "TMessage.findByLasthit", query = "SELECT t FROM TMessage t WHERE t.lasthit = :lasthit"),
        @NamedQuery(name = "TMessage.findByBanned", query = "SELECT t FROM TMessage t WHERE t.banned = :banned"),
        @NamedQuery(name = "TMessage.findByMsgdate", query = "SELECT t FROM TMessage t WHERE t.msgdate = :msgdate"),
        @NamedQuery(name = "TMessage.findByMsgsize", query = "SELECT t FROM TMessage t WHERE t.msgsize = :msgsize"),
        @NamedQuery(name = "TMessage.findByMsgtimestamp", query = "SELECT t FROM TMessage t WHERE t.msgtimestamp = :msgtimestamp"),
        @NamedQuery(name = "TMessage.findByClosed", query = "SELECT t FROM TMessage t WHERE t.closed = :closed"),
        @NamedQuery(name = "TMessage.findByThumbnail", query = "SELECT t FROM TMessage t WHERE t.thumbnail = :thumbnail"),
        @NamedQuery(name = "TMessage.findByParent", query = "SELECT t FROM TMessage t WHERE t.parent = :parent"),
        @NamedQuery(name = "TMessage.findByName", query = "SELECT t FROM TMessage t WHERE t.name = :name"),
        @NamedQuery(name = "TMessage.findByImage", query = "SELECT t FROM TMessage t WHERE t.image = :image"),
        @NamedQuery(name = "TMessage.findByOp", query = "SELECT t FROM TMessage t WHERE t.op = :op"),
        @NamedQuery(name = "TMessage.findByBannnned", query = "SELECT t FROM TMessage t WHERE t.bannnned = :bannnned"),
        @NamedQuery(name = "TMessage.findByHeight", query = "SELECT t FROM TMessage t WHERE t.height = :height"),
        @NamedQuery(name = "TMessage.findBySticky", query = "SELECT t FROM TMessage t WHERE t.sticky = :sticky"),
        @NamedQuery(name = "TMessage.findByTnHeight", query = "SELECT t FROM TMessage t WHERE t.tnHeight = :tnHeight"),
        @NamedQuery(name = "TMessage.findByTnWidth", query = "SELECT t FROM TMessage t WHERE t.tnWidth = :tnWidth"),
        @NamedQuery(name = "TMessage.findByWidth", query = "SELECT t FROM TMessage t WHERE t.width = :width")})
public class TMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long num;
    @Basic(optional = false)
    @Column(nullable = false)
    private long lasthit;
    private BigInteger banned;
    @Basic(optional = false)
    @Column(nullable = false, length = 60)
    private String msgdate;
    @Basic(optional = false)
    @Column(nullable = false)
    private long msgsize;
    @Basic(optional = false)
    @Column(nullable = false)
    private long msgtimestamp;
    @Basic(optional = false)
    @Column(nullable = false)
    private long closed;
    @Column(length = 150)
    private String thumbnail;
    @Basic(optional = false)
    @Column(nullable = false)
    private long parent;
    @Lob
    @Column(length = 65535)
    private String video;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String subject;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 150)
    private String image;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String comment;
    @Basic(optional = false)
    @Column(nullable = false)
    private long op;
    @Column(length = 255)
    private String bannnned;
    private BigInteger height;
    @Column(length = 255)
    private String sticky;
    @Column(name = "tn_height")
    private BigInteger tnHeight;
    @Column(name = "tn_width")
    private BigInteger tnWidth;
    private BigInteger width;

    public TMessage() {
    }

    public TMessage(Long num) {
        this.num = num;
    }

    public TMessage(PostDTO msg) {
        this.num = msg.num;
        this.lasthit = msg.lasthit;
        this.msgdate = msg.date;
        this.msgsize = msg.size;
        this.msgtimestamp = msg.timestamp;
        this.closed = Long.parseLong(msg.closed);
        this.parent = msg.parent;
        this.subject = msg.subject;
        this.name = msg.name;
        this.comment = msg.comment;
        this.op = msg.op;
        this.image = msg.image;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public long getLasthit() {
        return lasthit;
    }

    public void setLasthit(long lasthit) {
        this.lasthit = lasthit;
    }

    public BigInteger getBanned() {
        return banned;
    }

    public void setBanned(BigInteger banned) {
        this.banned = banned;
    }

    public String getMsgdate() {
        return msgdate;
    }

    public void setMsgdate(String msgdate) {
        this.msgdate = msgdate;
    }

    public long getMsgsize() {
        return msgsize;
    }

    public void setMsgsize(long msgsize) {
        this.msgsize = msgsize;
    }

    public long getMsgtimestamp() {
        return msgtimestamp;
    }

    public void setMsgtimestamp(long msgtimestamp) {
        this.msgtimestamp = msgtimestamp;
    }

    public long getClosed() {
        return closed;
    }

    public void setClosed(long closed) {
        this.closed = closed;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getOp() {
        return op;
    }

    public void setOp(long op) {
        this.op = op;
    }

    public String getBannnned() {
        return bannnned;
    }

    public void setBannnned(String bannnned) {
        this.bannnned = bannnned;
    }

    public BigInteger getHeight() {
        return height;
    }

    public void setHeight(BigInteger height) {
        this.height = height;
    }

    public String getSticky() {
        return sticky;
    }

    public void setSticky(String sticky) {
        this.sticky = sticky;
    }

    public BigInteger getTnHeight() {
        return tnHeight;
    }

    public void setTnHeight(BigInteger tnHeight) {
        this.tnHeight = tnHeight;
    }

    public BigInteger getTnWidth() {
        return tnWidth;
    }

    public void setTnWidth(BigInteger tnWidth) {
        this.tnWidth = tnWidth;
    }

    public BigInteger getWidth() {
        return width;
    }

    public void setWidth(BigInteger width) {
        this.width = width;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof TMessage)) {
            return false;
        }
        TMessage other = (TMessage) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.ru.terra.twochsaver.twoch.db.jpa.TMessage[ num=" + num + " ]";
    }

}
