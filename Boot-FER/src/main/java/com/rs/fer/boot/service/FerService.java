package com.rs.fer.boot.service;

import java.util.List;

import com.rs.fer.boot.bean.Expense;
import com.rs.fer.boot.bean.User;

public interface FerService {
	
	// registration
			public void registration(User user);

		//login
			public User login(String username, String password);

		//addExpense
			public void addExpense(Expense expense);

		//editExpense
			 public Expense editExpense(Expense expense);

		//deletExpense
			public boolean deleteExpense(int expenseId);

		//reset password
			 public boolean reset(int id, String currentPassword, String newPassword);

		//get expenses
			 List<Expense> getExpenses(int userid);

		//get expense
		// Expense findById(int id);

		//expense report
			List<Expense> expenseReport(int id, String expense_type, String fromDate, String toDate);

		//get user
			public User getUser(int id);

		//update user
			 public User updateUser(User user);

			

			Expense getExpense(int id);


	}

