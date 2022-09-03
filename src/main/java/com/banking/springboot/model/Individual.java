package com.banking.springboot.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "individuals")
public class Individual {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotNull
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date birthDate;

	@Column
	@NotNull
	private String firstName;

	@Column
	@NotNull
	String lastName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@NotNull
	private Customer customer;

	public Individual() {
		super();
	}

	public Individual(Date birthDate, String firstName, String lastName, Customer customer) {
		super();
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Individual [id=" + id + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName
				+ ", customer=" + customer + "]";
	}

}
