package com.banking.springboot.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	@NotNull(message = "Available balance is required.")
	@Positive(message = "Available balance must be positive.")
	@Max(value = 1000000, message = "Available balance must be less than or equal to $1,000,000.")
	@Min(value = 1, message = "Available balance must be at least $1.")
	private Double availableBalance;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastActivityDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date openDate;

	@Column
	@NotNull(message = "Pending balance is required.")
	@Positive(message = "Pending balance must be positive.")
	@Max(value = 1000000, message = "Pending balance must be less than or equal to $1,000,000.")
	@Min(value = 1, message = "Pending balance must be at least $1.")
	private Double pendingBalance;

	@Column
	private String status;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "branch_id")
	private Branch branch;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_id")
	private Product product;

	@OneToMany(mappedBy = "account", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<Transaction> transactions;

}
