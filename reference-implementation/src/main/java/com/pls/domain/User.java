package com.pls.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Users table.
 * 
 * @author User
 * 
 */
@Cacheable
@Entity
@Table(name = "Users")
@NamedQuery(query = "select u from User u where upper(u.userId) like :userId order by u.userId", 
	name = "User.searchByUserId")
@SequenceGenerator(name = "USERS_SEQ", sequenceName = "USR_SEQ")
public class User implements Serializable {
	private static final long serialVersionUID = -2092570781115897267L;

	private static final int MAX_USERNAME_LENGTH = 25;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = MAX_USERNAME_LENGTH, message = "1-25 letters and spaces")
	@Pattern(regexp = "[A-Za-z ]*", message = "Only letters and spaces")
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = MAX_USERNAME_LENGTH, message = "1-25 letters and spaces")
	@Pattern(regexp = "[A-Za-z ]*", message = "Only letters and spaces")
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@NotEmpty
	private String userId;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = MAX_USERNAME_LENGTH, message = "1-25 letters and spaces")
	@Pattern(regexp = "[A-Za-z0-9]*", message = "Only letters and digits")
	private String password;

	@NotNull
	@NotEmpty
	@Email(message = "Invalid format")
	@Column(name = "email_address")
	private String emailAddress;

	private String status = "A";

	@Column(name = "date_created")
	private Date dateCreated = new Date();

	@Column(name = "date_modified")
	private Date dateModified = new Date();

	@Column(name = "created_By")
	private Long createdBy = 0L;

	@Column(name = "modified_by")
	private Long modifiedBy = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
	@Column(name = "person_id")
	private Long personId;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the dateModified
	 */
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public Long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
