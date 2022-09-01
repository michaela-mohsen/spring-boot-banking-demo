package com.banking.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	private String address;
	
	@Column
	@NotNull
	private String city;
	
	@Column
	@NotNull
	private String state;
	
	@Column
	@NotNull
	private String zipCode;
	
	@Column
	@NotNull
	private char customerType;
	
	@Column
	@NotNull
	private String fedId;

	public Customer() {
		super();
	}

	public Customer(String address, String city, String state, String zipCode, char customerType, String fedId) {
		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.customerType = customerType;
		this.fedId = fedId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public char getCustomerType() {
		return customerType;
	}

	public void setCustomerType(char customerType) {
		this.customerType = customerType;
	}

	public String getFedId() {
		return fedId;
	}

	public void setFedId(String fedId) {
		this.fedId = fedId;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", customerType=" + customerType + ", fedId=" + fedId + "]";
	}

}
