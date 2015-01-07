package com.fob.opp.view.adm;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fob.opp.bo.Dao;
import com.fob.opp.entity.FeeAgreement;

@Named
@SessionScoped
public class AdminBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private FeeAgreement aggreement;
	private List<FeeAgreement> list;
	
	@Inject
	private Dao<FeeAgreement> dao;
	
	public FeeAgreement getAggreement() {
		return aggreement;
	}
	public void setAggreement(FeeAgreement aggreement) {
		this.aggreement = aggreement;
	}
	public List<FeeAgreement> getList() {
		return list;
	}
	public void setList(List<FeeAgreement> list) {
		this.list = list;
	}
	
	@PostConstruct
	public void init() {
		list = dao.findAll(FeeAgreement.class);
	}
	
	

}
