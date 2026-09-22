# ATM Simulator

ATM Simulator is a Java console-based application that simulates basic ATM operations. The application allows users to interact with an account through a simple and interactive menu-driven system.

## Features

- Account creation with automatically generated account numbers and PINs
- Login using an account number and PIN with limited attempts
- Balance inquiry, deposit, and withdrawal operations
- Transaction history with date, time, and updated balance details
- PIN change with current PIN verification and confirmation
- Support for multiple accounts during a program session
- Menu-driven navigation with logout functionality

## Requirements

- Java Development Kit (JDK) 17

## How to Run

1. Clone or download the repository.
2. Open a terminal or command prompt in the project folder.

Compile the program:

~~~text
javac -d bin src/com/atm/*.java
~~~

Run the program:

~~~text
java -cp bin com.atm.Main
~~~

## Sample Session

~~~text
========== ATM SIMULATOR ==========

1. Login
2. Create Account
3. Exit

Choose option: 2
Account created successfully!

Account Number: 100001
PIN: 6469
Balance: ₹0

========== ATM SIMULATOR ==========

1. Login
2. Create Account
3. Exit

Choose option: 1
Enter Account Number: 100001
Enter PIN: 6469
Login successful!

========== ATM MENU ==========

1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Change PIN
6. Logout

Choose option: 1
Current Balance: ₹0

========== ATM MENU ==========

1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Change PIN
6. Logout

Choose option: 2
Enter deposit amount: ₹5000
₹5000 deposited successfully.
Current Balance: ₹5000

========== ATM MENU ==========

1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Change PIN
6. Logout

Choose option: 3
Enter withdrawal amount: ₹3000
₹3000 withdrawn successfully.
Current Balance: ₹2000

========== ATM MENU ==========

1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Change PIN
6. Logout

Choose option: 4

========== TRANSACTION HISTORY ==========
1. Deposited ₹5000 | Balance: ₹5000 | 12-09-2026 18:45
2. Withdrawn ₹3000 | Balance: ₹2000 | 12-09-2026 18:45

========== ATM MENU ==========

1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Change PIN
6. Logout

Choose option: 5
Enter current PIN: 6469
Enter new PIN: 1796
Confirm new PIN: 1796
PIN changed successfully.

========== ATM MENU ==========

1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Change PIN
6. Logout

Choose option: 6
Logged out successfully.
~~~

## Technologies Used

- Java 17
- Java Standard Library
  - `java.util.ArrayList`
  - `java.util.List`
  - `java.util.Collections`
  - `java.util.Random`
  - `java.util.Scanner`
  - `java.time.LocalDateTime`
  - `java.time.format.DateTimeFormatter`
- Git and GitHub

## Project Structure

~~~text
ATMSimulator/
├── src/
│   └── com/
│       └── atm/
│           ├── Account.java
│           └── Main.java
├── .gitignore
├── LICENSE
└── README.md
~~~

## Limitations

- Account and transaction data are stored only while the program is running.
- Data is lost when the program closes because no database or permanent storage is used.
- PIN authentication is simplified for this console project and does not include advanced security measures used in real ATM systems.
- The login process allows a maximum of 3 incorrect PIN attempts. This limit resets when the program is restarted.

## Learning Outcomes

- **Object-oriented programming** — organizing account data and related behavior using classes and objects.
- **Encapsulation** — keeping account data private and managing changes through methods such as deposit, withdrawal, and PIN change.
- **Input handling and validation** — handling user input and checking values and transaction conditions before updating account data.
- **Java Collections** — managing accounts and transaction records using collections and protecting transaction history from direct modification.
- **Date and time handling** — recording and formatting transaction timestamps using `LocalDateTime` and `DateTimeFormatter`.
- **Incremental development** — building and testing the project feature by feature.
