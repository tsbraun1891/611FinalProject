﻿# 611FinalProject
Name: Lingyan Xie
Email: lingxie@bu.edu
BUID: U41725100

Name: Xinyi Zhao
Email: zxy1307@bu.edu
BUID: U76496954

The GUI design for this program is straight forward. Basically, one GUI class contains one JFrame, and each JFrame represents one window that user will see each time. Each GUI class has a background picture and a back button that transfer user back to previous page, and a quit button that will transfer to GUIQuit which prints out a "goodbye" message and calls Bank.save to make sure unsaved data is not lost.

GUIHome.java: This class represents the welcome page window. Click start button will transfer user to "chooseUserType" page.

GUIChooseUserType.java: This class represents the window that let user picks userType. It gives two login options: CustomerLogin, AdminLogin.

GUILogin.java: This class represents the window that allows both userTypes: customer, admin to log in. 

GUIQuit.java: This class represents the quit window. When user quits, it will call Bank.getInstance().save() to make sure any unsaved data will not be lost.

------For Customer------

GUIRegisterUserAccount.java: This class represents the window that allows the customer to sign up for new user account. It will ask the customer for unique username, password, first name, last name, initial currency type, and initial fund. Currently, the program only supports customer account registration.

After successfully logged in with userType customer, the customer will be transfer to Customer Home Page.

GUICustomerHome.java: This class represents the Customer Home Page window. Each customer can view account/deposit/withdrawal/transfer/request loan/pay off loan/open new account/cash in/cash out.

GUIViewAcct.java: This class represents the window that allows customer to view accounts in details. Customer can choose a Balancehandler object(their existing wallet, accounts, loans) and view its balance, view its transaction history, and convert its currency type. If customer clicks the convert currency type button, customer will be transfer to convertCurrency page, and if customer clicks the view transaction, it will call GUIViewTransactionHistory(BalanceHandler bh).
GUIConvertCurrency.java: This class represents the window that allows customer to change currency type for a specific balance handler.Our bank support three currencies: USD, EURO, YEN

GUICashIn.java: This class represents the window that asks the customer to insert cash. After successfully inserted cash, the money will go to customer's wallet (balance).
GUICashOut.java:This class represents the window that allows customer to take some amount money from ATM. After successfully cashed out, the money will be subtracted from customer's wallet(balance).

GUIDeposit.java: This class represents the window that allows customer to deposit from ATM to a specific balance handler. If successfully deposit, the money will be subtracted from customer's wallet(balance) to their chosen account.
GUIWithdrawal.java: This class represents the window that allows customer to withdraw money from account. If successfully deposit, the money will be subtracted from customer's chosen account to their wallet(balance). 

GUITransfer.java:This class represents the Transfer home page. Customer can choose to send money to other user, or transfer between accounts.
GUITransferTo: This class represents the window that allows customer to send money to other customers. Customer will send money from a specific account and enter recipient's username. If successfully transferred money to the recipient, the money will goes to recipient's wallet(balance).
GUITransferBetween: This class represents the window that allows customer to transfer between accounts. Customer can choose to transfer from their wallet or any account to their other account or loan.

GUIRequestLoan.java: This class represents the window that allows customer to request loan. After successfully submitted, the loan request will wait for Admin to approve/deny. If approved, the loan will be shown in View Acccount Home page and customer will be able to choose this loan and perform given actions.
GUIPayOffLoan.java: This class represents the window that allows customer to pay off loan. Customer can choose from their wallet or any account to pay off a valid amount to an existing open loan.

GUIOpenNewAccount.java:This class represents the window that allows customer to open new account: saving/checking, and choose the initial currency type that the account will be in.

------For Admin------

After successfully logged in, the admin will be transfer to Admin Home Page.

GUIAdminHome.java: This class represents the Admin Home Page window.  Admin can choose to get a daily report/ check up on a specific customer/ deal with loan request/press advance one month button(to predict interest gains for saving accounts/loans). If Admin clicks the get daily report button, a daily report in Jtable will prompt. 

GUICheckCustomer.java: This class represents the window that let the Admin check up on specific customer given username.If username is valid, it will call GUIViewTransactionHistory(user,true). Two Jtables will prompt: 1. Jtable for customer's accounts detail, 2. Jtable for customer's loans detail.

GUILoanRequest.java: This class represents the window that admin to deal with loan requests. It will show the amount of fund that the admin has, and when admin chooses a specific loan request, its requested amount, currency type, and the collateral will be shown. Admin can approve or deny the loan request depending on these info.



User.java: Abstract class, and the class represents all users, which is super class of Customer and Admin, and also extends BalanceHandler.

UserType.java: The class is a enum class, which represents user types

Customer.java: The class represents all customers, and also is a subclass of User class. It has interaction with loan.

Admin.java: The class represents admin, and also is a subclass of User class. It also has interaction with loan.

ATM.java: Abstract ATM class, Bank.java extends it. It shows the basic functions of an ATM. The main purpose of this class is for future extension.

Bank.java: The class represents bank, and has basic functions of bank, which interactes with accounts, users, transactions, currency, and IO. It also extends ATM class.

BalanceHandler.java: Abstract class, and the class handles all actions with balance, also is a super class of User, Account, and Loan class
