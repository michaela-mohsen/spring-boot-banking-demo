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
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	@NotNull
	private double amount;

	@Column
	@NotNull
	private Date fundsAvailableDate;

	@Column
	@NotNull
	private Date date;

	@Column
	@NotNull
	String type;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "account_id")
	@NotNull
	private Account account;

	public Transaction() {
		super();
	}

	public Transaction(double amount, Date fundsAvailableDate, Date date, String type, Account account) {
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
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

	public void setAccount(Account existingAccount) {
		this.account = existingAccount;
		existingAccount.setId(account.getId());
		existingAccount.setAvailableBalance(account.getAvailableBalance());
		existingAccount.setLastActivityDate(account.getLastActivityDate());
		existingAccount.setPendingBalance(account.getPendingBalance());
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", fundsAvailableDate=" + fundsAvailableDate + ", date="
				+ date + ", type=" + type + ", account=" + account + "]";
	}

}
