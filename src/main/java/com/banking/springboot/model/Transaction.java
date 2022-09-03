package com.banking.springboot.model;

import java.sql.Date;

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
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotNull
	private float amount;

	@Column
	@NotNull
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date fundsAvailableDate;

	@Column
	@NotNull
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date date;

	@Column
	@NotNull
	String type;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	@NotNull
	private Account account;

	public Transaction() {
		super();
	}

	public Transaction(float amount, Date fundsAvailableDate, Date date, String type, Account account) {
		super();
		this.amount = amount;
		this.fundsAvailableDate = fundsAvailableDate;
		this.date = date;
		this.type = type;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getFundsAvailableDate() {
		return fundsAvailableDate;
	}

	public void setFundsAvailableDate(Date fundsAvailableDate) {
		this.fundsAvailableDate = fundsAvailableDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", fundsAvailableDate=" + fundsAvailableDate + ", date="
				+ date + ", type=" + type + ", account=" + account + "]";
	}

}
