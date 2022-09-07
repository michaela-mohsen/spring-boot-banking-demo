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
@Table(name = "businesses")
public class Business {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotNull
	private Date incorpDate;

	@Column
	@NotNull
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@NotNull
	private Customer customer;

	public Business() {
		super();
	}

	public Business(Date incorpDate, String name, Customer customer) {
		super();
		this.incorpDate = incorpDate;
		this.name = name;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getIncorpDate() {
		return incorpDate;
	}

	public void setIncorpDate(Date incorpDate) {
		this.incorpDate = incorpDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", incorpDate=" + incorpDate + ", name=" + name + ", customer=" + customer + "]";
	}

}
