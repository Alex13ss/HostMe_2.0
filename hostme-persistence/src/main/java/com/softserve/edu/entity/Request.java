package com.softserve.edu.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.CalendarDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * This class represents request for <code>hosting</code> and is created by
 * <code>author</code>.
 * 
 * @author Lv-117
 */
@Entity
@Table(name = "REQUEST", uniqueConstraints = { @UniqueConstraint(columnNames = { "request_id" }) })
public class Request  implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "request_id", unique = true, nullable = false)
	private Integer requestId;
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_date", nullable = false)
	private Calendar beginDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", nullable = false)
	private Calendar endDate;
	@Column(name = "notes", length = 800)
	private String notes;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hosting_id", nullable = false)
	private Hosting hosting;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id", nullable = false)
	private User author;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver_id", nullable = false)
	private User receiver;

	public Request() {
	}

	public Request(Status status, Calendar beginDate, Calendar endDate,
			String notes) {
		super();
		this.status = status;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.notes = notes;
	}

	public Integer getRequestId() {
		return requestId;
	}
	

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Calendar getBeginDate() {
		return beginDate;
	}
	@JsonDeserialize(using=CalendarDeserializer.class)
	public void setBeginDate(Calendar beginDate) {
		this.beginDate = beginDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}
	@JsonDeserialize(using=CalendarDeserializer.class)
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Hosting getHosting() {
		return hosting;
	}

	public void setHosting(Hosting hosting) {
		if (this.hosting != hosting)
			this.hosting = hosting;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		if (this.author != author)
			this.author = author;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", status=" + status
				+ ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", notes=" + notes + "]";
	}


}
