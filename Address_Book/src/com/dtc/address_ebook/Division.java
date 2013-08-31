package com.dtc.address_ebook;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Division
 *
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "Division.findAll", query = "select d from Division d order by d.name") })
public class Division implements Serializable {

	   
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "division")
	private List<Townships> townshiplist;
	

	public List<Townships> getTownshiplist() {
		return townshiplist;
	}
	public void setTownshiplist(List<Townships> townshiplist) {
		this.townshiplist = townshiplist;
	}
	public Division() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
   
}
