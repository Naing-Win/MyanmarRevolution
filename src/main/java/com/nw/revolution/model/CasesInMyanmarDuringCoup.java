package com.nw.revolution.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cases_in_myanmar_during_coup")
public class CasesInMyanmarDuringCoup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "case_title")
	private String caseTitle;
	private String description;
	
	@Column(name = "date_of_case")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfCase;
	
	@Enumerated(EnumType.STRING)
	private Division division;
	
	@Column(name = "city_or_state")
	private String cityOrState;
	
	@Column(name = "village_or_ward")
	private String villageOrWard;
	
	@Column(name = "image_for_case")
	private String imageForCase;
	
	public CasesInMyanmarDuringCoup() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOfCase() {
		return dateOfCase;
	}

	public void setDateOfCase(Date dateOfCase) {
		this.dateOfCase = dateOfCase;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String getCityOrState() {
		return cityOrState;
	}

	public void setCityOrState(String cityOrState) {
		this.cityOrState = cityOrState;
	}

	public String getVillageOrWard() {
		return villageOrWard;
	}

	public void setVillageOrWard(String villageOrWard) {
		this.villageOrWard = villageOrWard;
	}

	public String getImageForCase() {
		return imageForCase;
	}

	public void setImageForCase(String imageForCase) {
		this.imageForCase = imageForCase;
	}
	
	public String getPhotosPath() {
		if (imageForCase == null || id == null) {
			return null;
		}
		return "/images/case/" + id + "/" + imageForCase;
	}
	
}

/*
enum Division {
	Magwe, 
	Yangon, 
	Mandalay, 
	Thaninthayi, 
	Ayeyarwaddy, 
	Bago, 
	Sagaing, 
	Naypyitaw, 
	Mon, 
	Rakhine, 
	Kayin, 
	Kayah, 
	Shan, 
	Chin, 
	Kachin
}
*/
