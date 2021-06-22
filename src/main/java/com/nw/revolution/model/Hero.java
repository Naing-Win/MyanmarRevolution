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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Hero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Enter revolution hero name.")
	@Column(name = "revolution_hero_name")
	private String revolutionHeroName;
	
	//@NotNull(message = "Enter age of revolution hero.")
	@Max(value = 70, message = "Age less than 70.")
	//@Max(value = 60)
	@Positive(message = "Age can not be negative.")
	@Digits(integer = 2, fraction = 0, message = "Age must be positive and only 2 digits number.")
	private int age;
	
	@Column(name = "date_of_dead")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Please select date of dead.")
	private Date dateOfDead;
	
	@NotBlank(message = "Enter state or city name of revolution hero name.")
	@Column(name = "state_name")
	private String stateName;
	
	@NotBlank(message = "Enter village or ward name of revolution hero name.")
	@Column(name = "village_or_ward_name")
	private String villageName;
	
	@NotNull(message = "Please select revolution hero division.")
	@Enumerated(EnumType.STRING)
	private Division division;
	
	@NotBlank(message = "Enter how to dead (eg.Head shoot).")
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
		/*
		 * if (age < 0) { throw new
		 * IllegalArgumentException("Age can not be negative."); }
		 */
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
