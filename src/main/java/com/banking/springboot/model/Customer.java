package com.banking.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	@NotNull(message = "Birth date is required.")
	private Date birthDate;

	@Column
	@NotEmpty(message = "First name is required.")
	@Length(min = 2, max = 45, message = "First name must be between 2 and 45 characters long.")
	private String firstName;

	@Column
	@NotEmpty(message = "Last name is required.")
	@Length(min = 2, max = 45, message = "Last name must be between 2 and 45 characters long.")
	private String lastName;

	@Column
	@NotEmpty(message = "Address is required.")
	@Length(min = 5, max = 45, message = "Address must be between 5 and 45 characters long.")
	private String address;

	@Column
	@NotEmpty(message = "City is required.")
	@Length(min = 2, max = 45, message = "City must be between 2 and 45 characters long.")
	private String city;

	@Column
	@NotEmpty(message = "State is required.")
	@Length(min = 2, max = 2, message = "State must be abbreviated to 2 letters.")
	private String state;

	@Column
	@NotEmpty(message = "Zip code is required.")
	@Pattern(regexp = "^\\d{5}$", message = "Zip code must be 5 digits long.")
	private String zipCode;

}
