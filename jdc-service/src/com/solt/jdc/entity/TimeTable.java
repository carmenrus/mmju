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
}