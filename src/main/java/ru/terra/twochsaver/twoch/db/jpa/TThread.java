/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.terra.twochsaver.twoch.db.jpa;

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
@Table(name = "thread", catalog = "2ch", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = { "start_message" }) })
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "TThread.findAll", query = "SELECT t FROM TThread t"),
		@NamedQuery(name = "TThread.findById", query = "SELECT t FROM TThread t WHERE t.id = :id"),
		@NamedQuery(name = "TThread.findByStartMessage", query = "SELECT t FROM TThread t WHERE t.startMessage = :startMessage"),
		@NamedQuery(name = "TThread.findByBoard", query = "SELECT t FROM TThread t WHERE t.board = :board") })
public class TThread implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Long id;
	@Basic(optional = false)
	@Column(name = "start_message", nullable = false)
	private long startMessage;
	@Basic(optional = false)
	@Column(nullable = false, length = 10)
	private String board;

	public TThread() {
	}

	public TThread(Long id) {
		this.id = id;
	}

	public TThread(Long id, long startMessage, String board) {
		this.id = id;
		this.startMessage = startMessage;
		this.board = board;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getStartMessage() {
		return startMessage;
	}

	public void setStartMessage(long startMessage) {
		this.startMessage = startMessage;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TThread)) {
			return false;
		}
		TThread other = (TThread) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "main.java.ru.terra.twochsaver.twoch.db.jpa.TThread[ id=" + id + " ]";
	}

}
