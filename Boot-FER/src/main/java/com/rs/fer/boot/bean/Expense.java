package com.rs.fer.boot.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="expense")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Expense implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="expenseid", nullable = false)
	@GeneratedValue
	private int id;
	@Column
	private String expense_type;
	@Column
	private String date;
	@Column
	private float price;
	@Column
	private int no_of_items;
	@Column
	private float total;
	@Column
	private String by_whom;
	@Column
	private int userid;


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpense_type() {
		return this.expense_type;
	}

	public void setExpense_type(String expense_type) {
		this.expense_type = expense_type;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNo_of_items() {
		return this.no_of_items;
	}

	public void setNo_of_items(int no_of_items) {
		this.no_of_items = no_of_items;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getBy_whom() {
		return this.by_whom;
	}

	public void setBy_whom(String by_whom) {
		this.by_whom = by_whom;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
