package com.solt.jdc.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the time_table database table.
 * 
 */
@Entity
@Table(name="time_table")
@NamedQuery(name="TimeTable.findAll", query="SELECT t FROM TimeTable t")
public class TimeTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String days;

	private String description;

	@Column(name="time_from")
	private String timeFrom;

	@Column(name="time_to")
	private String timeTo;

	public TimeTable() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeFrom() {
		return this.timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return this.timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	@Override
	public String toString() {
		return this.days + " [" + this.timeFrom + "-" + this.timeTo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((timeFrom == null) ? 0 : timeFrom.hashCode());
		result = prime * result + ((timeTo == null) ? 0 : timeTo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeTable other = (TimeTable) obj;
		if (days == null) {
			if (other.days != null)
				return false;
		} else if (!days.equals(other.days))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (timeFrom == null) {
			if (other.timeFrom != null)
				return false;
		} else if (!timeFrom.equals(other.timeFrom))
			return false;
		if (timeTo == null) {
			if (other.timeTo != null)
				return false;
		} else if (!timeTo.equals(other.timeTo))
			return false;
		return true;
	}
	
	
}