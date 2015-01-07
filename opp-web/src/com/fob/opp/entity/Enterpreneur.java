package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the enterpreneur database table.
 * 
 */
@Entity
@NamedQuery(name="Enterpreneur.findAll", query="SELECT e FROM Enterpreneur e")
public class Enterpreneur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String business;

	private String email;

	private String name;

	private String phone;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="enterpreneur")
	private List<Account> accounts;

	//bi-directional many-to-one association to Budget
	@OneToMany(mappedBy="enterpreneur")
	private List<Budget> budgets;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="enterpreneur")
	private List<Employee> employees;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="enterpreneur")
	private List<Loan> loans;

	public Enterpreneur() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setEnterpreneur(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setEnterpreneur(null);

		return account;
	}

	public List<Budget> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public Budget addBudget(Budget budget) {
		getBudgets().add(budget);
		budget.setEnterpreneur(this);

		return budget;
	}

	public Budget removeBudget(Budget budget) {
		getBudgets().remove(budget);
		budget.setEnterpreneur(null);

		return budget;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEnterpreneur(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEnterpreneur(null);

		return employee;
	}

	public List<Loan> getLoans() {
		return this.loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Loan addLoan(Loan loan) {
		getLoans().add(loan);
		loan.setEnterpreneur(this);

		return loan;
	}

	public Loan removeLoan(Loan loan) {
		getLoans().remove(loan);
		loan.setEnterpreneur(null);

		return loan;
	}

}