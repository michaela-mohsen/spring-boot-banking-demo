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

import com.banking.springboot.auth.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	@NotNull
	private String firstName;

	@Column
	@NotNull
	private String lastName;

	@Column
	@NotNull
	private Date startDate;

	@Column
	@NotNull
	private String title;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "branch_id")
	@NotNull
	private Branch branch;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "department_id")
	@NotNull
	private Department department;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

}
