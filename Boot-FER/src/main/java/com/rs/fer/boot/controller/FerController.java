package com.rs.fer.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rs.fer.boot.bean.Expense;
import com.rs.fer.boot.bean.User;
import com.rs.fer.boot.service.FerService;

@RestController
@RequestMapping(value = { "/user" })
public class FerController {
	@Autowired
	FerService ferService;

	// get expense
	@GetMapping(value = "/{id}")
	public ResponseEntity<Expense> getExpense(@PathVariable("id") int expenseid) {
		System.out.println("Fetching User with id " + expenseid);
		Expense expense = ferService.getExpense(expenseid);
		if (expense == null) {
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	// login
	@PostMapping(value = "/login")
	public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {

		User user = ferService.login(username, password);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// reset
	@PutMapping(value = "/reset/{id}")
	public ResponseEntity<Boolean> reset(@PathVariable ("id") int id,  String currentPassword, String newPassword) {
		boolean isReset = ferService.reset(id, currentPassword, newPassword);
		return new ResponseEntity<Boolean>(isReset ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
	}

//get user

	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		//System.out.println("Fetching User with id " + id);
		User user = ferService.getUser(id);
		//if (user == null) {
			//return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		//}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

//AddExpense
	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ResponseEntity<Void> addExpense(@RequestBody Expense expense, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Expense by: " + expense.getBy_whom());
		ferService.addExpense(expense);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/expense/{id}").buildAndExpand(expense.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

//Registration
	@PostMapping(value = "/registration", headers = "Accept=application/json")
	public ResponseEntity<Void> registation(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User: " + user.getUsername());
		ferService.registration(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/*
	 * @GetMapping(value = "/get", headers = "Accept=application/json") public
	 * ResponseEntity<Expense> getExpenses(@RequestBody Integer id) {
	 * System.out.println("Fetching User with id " + id); Expense expense =
	 * ferService.findById(id); if (expense == null) { return new
	 * ResponseEntity<Expense>(HttpStatus.NOT_FOUND); } return new
	 * ResponseEntity<Expense>(expense, HttpStatus.OK); }
	 */

//Edit Expense
	@PutMapping(value = "/edit", headers = "Accept=application/json")
	public ResponseEntity<String> editExpense(@RequestBody Expense expense) {
		System.out.println("sd");
		Expense expense1 = ferService.editExpense(expense);
		if (expense1 == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

//Delete Expense
	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Integer> deleteExpense(@PathVariable int id) {
		boolean isDelete = ferService.deleteExpense(id);
		if (!isDelete) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
	}

	/*
	 * @DeleteMapping(value = "/delete", headers = "Accept=application/json") public
	 * ResponseEntity<User> deleteUserQP(@RequestParam(required=false) long id) {
	 * User user = userService.findById(id); if (user == null) { return new
	 * ResponseEntity<User>(HttpStatus.NOT_FOUND); } userService.deleteUserById(id);
	 * return new ResponseEntity<User>(HttpStatus.NO_CONTENT); }
	 */
	/*
	 * @PatchMapping(value = "/{id}", headers = "Accept=application/json") public
	 * ResponseEntity<User> updateUserPartially(@PathVariable("id") long
	 * id, @RequestBody User currentUser) { User user = userService.findById(id); if
	 * (user == null) { return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * User usr = userService.updatePartially(currentUser, id); return new
	 * ResponseEntity<User>(usr, HttpStatus.OK); }
	 */

	// Update User
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		System.out.println("sd");
		User user1 = ferService.updateUser(user);
		if (user1 == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		// ferService.updateUser(user);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// expense report
	@GetMapping(value = "/expenseReport/{id}")
	public ResponseEntity<Iterable<Expense>> expenseReport(@PathVariable("id") int id, 
			String expense_type, String fromDate,
			String toDate) {
		Iterable<Expense> expense = ferService.expenseReport(id, expense_type, fromDate, toDate);
		if (expense == null) {
			return new ResponseEntity<Iterable<Expense>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Iterable<Expense>>(expense, HttpStatus.OK);
	}

	// get expenses
	@GetMapping(value = "/getExpenses/{userid}")
	public ResponseEntity<Iterable<Expense>> getExpenses(@PathVariable("userid") Integer userid) {
		System.out.println("Expenses of user are: ");
		List<Expense> expenses = ferService.getExpenses(userid);
		if (expenses == null) {
			return new ResponseEntity<Iterable<Expense>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Iterable<Expense>>(expenses, HttpStatus.OK);

	}
}
