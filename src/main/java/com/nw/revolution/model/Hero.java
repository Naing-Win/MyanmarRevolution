package com.nw.revolution.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Hero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "revolution_hero_name")
	private String revolutionHeroName;
	private int age;
	
	@Column(name = "date_of_dead")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dateOfDead;
	
	@Column(name = "state_name")
	private String stateName;
	
	@Column(name = "village_or_ward_name")
	private String villageName;
	
	@Enumerated(EnumType.STRING)
	private Division division;
	
	@Column(name = "how_to_dead")
	private String howToDead;
	
	public Hero() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRevolutionHeroName() {
		return revolutionHeroName;
	}

	public void setRevolutionHeroName(String revolutionHeroName) {
		this.revolutionHeroName = revolutionHeroName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateOfDead() {
		return dateOfDead;
	}

	public void setDateOfDead(Date dateOfDead) {
		this.dateOfDead = dateOfDead;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String getHowToDead() {
		return howToDead;
	}

	public void setHowToDead(String howToDead) {
		this.howToDead = howToDead;
	}
	
}

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
