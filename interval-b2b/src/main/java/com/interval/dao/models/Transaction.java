package com.interval.dao.models;

// Generated Aug 14, 2015 6:24:11 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Transaction generated by hbm2java
 */
public class Transaction implements java.io.Serializable {

	private Integer id;
	private TransactionType transactionType;
	private Date createdTime;
	private Date updatedTime;

	public Transaction() {
	}

	public Transaction(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Transaction(TransactionType transactionType, Date createdTime,
			Date updatedTime) {
		this.transactionType = transactionType;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
