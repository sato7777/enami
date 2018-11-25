package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "calc")
public class Calc {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String searchid;
	private String searchname;
	private String basedate;
	private String calcdate;
	private String calcvalue;
	private String calctarget;
	
	@Transient
	private String calctargetname;

	@Override
	public String toString() {
		return "Calc [id=" + id + ", searchid=" + searchid + ", searchname=" + searchname + ", basedate=" + basedate
				+ ", calcdate=" + calcdate + ", calcvalue=" + calcvalue + ", calctarget=" + calctarget + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSearchid() {
		return searchid;
	}

	public void setSearchid(String searchid) {
		this.searchid = searchid;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	public String getBasedate() {
		return basedate;
	}

	public void setBasedate(String basedate) {
		this.basedate = basedate;
	}

	public String getCalcdate() {
		return calcdate;
	}

	public void setCalcdate(String calcdate) {
		this.calcdate = calcdate;
	}

	public String getCalcvalue() {
		return calcvalue;
	}

	public void setCalcvalue(String calcvalue) {
		this.calcvalue = calcvalue;
	}

	public String getCalctarget() {
		return calctarget;
	}

	public void setCalctarget(String calctarget) {
		this.calctarget = calctarget;
	}

	public String getCalctargetname() {
		return calctargetname;
	}

	public void setCalctargetname(String calctargetname) {
		this.calctargetname = calctargetname;
	}

}
