package com.rs.fer.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rs.fer.boot.bean.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

	
	@Query("select e from Expense e where e.expense_type=:expense_type and e.date between :fromDate and :toDate")
	List<Expense> fetchExpenses(@Param("expense_type") String expense_type,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
	
	List<Expense> findByDateBetween(String fromDate, String toDate);

}
