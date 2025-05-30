# 🏦 Banking System with Database (Java + MySQL)

## 📌 Project Overview

This project simulates a basic **ATM/Bank Teller interface** using **Java** and **MySQL**. It supports both **customers** and **bank administrators**, allowing them to manage bank accounts via a command-line interface.

---

## 🔑 Features

### 👤 Customer Options:
- Login using account number and PIN
- Check balance
- Deposit money
- Withdraw money

### 🛠️ Administrator Options:
- Create new accounts
- Modify existing accounts (name, PIN, etc.)
- Close accounts

---

## 💻 Technologies Used

- Java (OOP + CLI)
- MySQL (for persistent data storage)
- JDBC (for database connectivity)
- VS Code or Eclipse (IDE)
- MySQL Connector/J (JDBC driver)

---

## 🗂️ Project Structure



Use this data when testing or populating your database:

Account Number	PIN	      Name	      Balance
123456	        1234	    John Doe	  1000.00
654321        	4321	    Jane Smith	2500.50
admin1	        adminpin	Admin User	0.00
The third entry is for admin login and functionality testing.


When running the project: 
javac -cp ".:./lib/mysql-connector-j-9.3.0.jar" -d bin src/*.java
Then:
java -cp "bin:./lib/mysql-connector-j-9.3.0.jar" Main

