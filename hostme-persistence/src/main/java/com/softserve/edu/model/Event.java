package com.softserve.edu.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import com.softserve.edu.model.routes.Place;
import com.google.common.base.Objects;

/**
 * 
 * @author Ronan Class for events, which contain all fields related to
 *         information about event
 *
 */
@Entity
@Table(name = "EVENTS", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@PrimaryKeyJoinColumn(name = "id")
public class Event extends Place{

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	public Event() {
		super();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), startDate, endDate);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Event) {
			if (!super.equals(object))
				return false;
			Event that = (Event) object;
			return Objects.equal(this.startDate, that.startDate)
					&& Objects.equal(this.endDate, that.endDate);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Event [startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}