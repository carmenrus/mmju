package com.solt.jdc.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the jdc_class database table.
 * 
 */
@Entity
@Table(name="jdc_class")
@NamedQuery(name="JdcClass.findAll", query="SELECT j FROM JdcClass j")
public class JdcClass implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum Status {ON, OFF}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date start;
	@Enumerated
	private Status status;

	//uni-directional many-to-one association to Course
	@ManyToOne
	private Course course;

	//uni-directional many-to-one association to TimeTable
	@ManyToOne
	@JoinColumn(name="time_table_id")
	private TimeTable timeTable;

	public JdcClass() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart() {
		return this.start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TimeTable getTimeTable() {
		return this.timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

	@Override
	public String toString() {
		return this.course.getName() + " : " + this.timeTable.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + id;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((timeTable == null) ? 0 : timeTable.hashCode());
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
		JdcClass other = (JdcClass) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (id != other.id)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (status != other.status)
			return false;
		if (timeTable == null) {
			if (other.timeTable != null)
				return false;
		} else if (!timeTable.equals(other.timeTable))
			return false;
		return true;
	}
	
	
}