package com.rs.fer.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rs.fer.boot.bean.Expense;
import com.rs.fer.boot.bean.User;
import com.rs.fer.boot.repository.ExpenseRepository;
import com.rs.fer.boot.repository.UserRepository;

@Service

public class FerServiceImp implements FerService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExpenseRepository expenseRepository;

	@Transactional
	@Override
	public void addExpense(Expense expense) {
		expenseRepository.save(expense);
	}

	@Transactional
	@Override
	public Expense editExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	@Transactional
	@Override
	public boolean deleteExpense(int expenseId) {
		expenseRepository.delete(expenseId);
		return true;
	}

	@Override
	public Expense getExpense(int id) {
		Expense expense = expenseRepository.findOne(id);
		return expense;
	}

	@Override
	public User getUser(int id) {
		return userRepository.findOne(id);

	}

	@Transactional
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<Expense> getExpenses(int userid) {
		return (List<Expense>) expenseRepository.findAll();
	}

	@Override
	public List<Expense> expenseReport(int id, String expense_type, String fromDate, String toDate) {
		//return null;
		return (List<Expense>) expenseRepository.findByDateBetween(fromDate, toDate);
	}

	@Transactional
	@Override
	public void registration(User user) {
		userRepository.save(user);

	}

	@Override
	public User login(String username, String password) {
		List<User> users = userRepository.findByUsernameAndPassword(username, password);
		return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}

	@Override
	public boolean reset(int id, String currentPassword, String newPassword) {
		List<User> users = userRepository.findByIdAndPassword(id, currentPassword);
		
		if(users != null && !users.isEmpty()) {
			User user = users.get(0);
			user.setPassword(newPassword);
			
			userRepository.save(user);
			
			return true;
		}
		
		return false;
	}

}
