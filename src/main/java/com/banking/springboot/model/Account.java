package com.banking.springboot.model;

import java.math.BigDecimal;
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
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotNull
	private BigDecimal availableBalance;

	@Column
	@NotNull
	private Date lastActivityDate;

	@Column
	@NotNull
	private Date openDate;

	@Column
	@NotNull
	private BigDecimal pendingBalance;

	@Column
	@NotNull
	private String status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@NotNull
	private Customer customer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "branch_id")
	@NotNull
	private Branch branch;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	@NotNull
	private Employee employee;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	@NotNull
	private Product product;

	public Account() {
		super();
	}

	public Account(@NotNull BigDecimal availableBalance, Date lastActivityDate, Date openDate,
			@NotNull BigDecimal pendingBalance,
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

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public BigDecimal getPendingBalance() {
		return pendingBalance;
	}

	public void setPendingBalance(BigDecimal pendingBalance) {
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", availableBalance=" + availableBalance + ", lastActivityDate=" + lastActivityDate
				+ ", openDate=" + openDate + ", pendingBalance="
				+ pendingBalance + ", status=" + status + ", customer=" + customer + ", branch=" + branch
				+ ", employee=" + employee + ", product=" + product + "]";
	}

}
