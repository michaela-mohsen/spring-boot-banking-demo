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

@Entity
@Table(name = "individuals")
public class Individual {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotNull
	private Date birthDate;

	@Column
	@NotNull
	private String firstName;

	@Column
	@NotNull String lastName;

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

	public Date getIncorpDate() {
		return birthDate;
	}

	public void setIncorpDate(Date incorpDate) {
		this.birthDate = incorpDate;
	}

	public String getName() {
		return firstName;
	}

	public void setName(String name) {
		this.firstName = name;
	}

	public String getState() {
		return lastName;
	}

	public void setState(String state) {
		this.lastName = state;
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
