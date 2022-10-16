package com.banking.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	@NotNull
	private double availableBalance;

	@Column
	@NotNull
	private String lastActivityDate;

	@Column
	@NotNull
	private String openDate;

	@Column
	@NotNull
	private double pendingBalance;

	@Column
	@NotNull
	private String status;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	@NotNull
	private Customer customer;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "branch_id")
	@NotNull
	private Branch branch;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "employee_id")
	@NotNull
	private Employee employee;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_id")
	@NotNull
	private Product product;

	@OneToMany(mappedBy = "account", cascade = CascadeType.MERGE)
	private List<Transaction> transactions;

	public Account() {
		super();
	}

	public Account(@NotNull double availableBalance, @NotNull String lastActivityDate, @NotNull String openDate,
			@NotNull double pendingBalance,
			String status, Customer customer, Branch branch, Employee employee, Product product) {
		super();
		this.availableBalance = availableBalance;
		this.lastActivityDate = lastActivityDate;
		this.openDate = openDate;
		this.pendingBalance = pendingBalance;
		this.status = status;
		this.customer = customer;
		this.branch = branch;
		this.employee = employee;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public @NotNull String getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(@NotNull String lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public @NotNull String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(@NotNull String openDate) {
		this.openDate = openDate;
	}

	public double getPendingBalance() {
		return pendingBalance;
	}

	public void setPendingBalance(double pendingBalance) {
		this.pendingBalance = pendingBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", availableBalance=" + availableBalance + ", lastActivityDate=" + lastActivityDate
				+ ", openDate=" + openDate + ", pendingBalance="
				+ pendingBalance + ", status=" + status + ", customer=" + customer + ", branch=" + branch
				+ ", employee=" + employee + ", product=" + product + "]";
	}

}
