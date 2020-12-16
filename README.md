# 611FinalProject
Name: Lingyan Xie
Email: lingxie@bu.edu
BUID: U41725100

Name: Xinyi Zhao
Email: zxy1307@bu.edu
BUID: U76496954

Name: Tanner Braun
Email: tsbraun@bu.edu
BUID: U65930557

COMPILATION:
    To compile this program, navigate to the source folder and run the command "javac *.java"

EXECUTION:
    After compilation, in the same folder run the command "java Main"
    If you are looking to use the default admin account, the login credentials are:
    Username - msmith
    PW - smithword

Main.java:
    This class serves only as a starting point to launch
    the GUI and functionality for the banking program.

Account.java:
    The Account class is an abstract class that represents an account
    in a banking system. Accounts are a place to store money that can
    be deposited to or withdrawn from by their owner, and handle any
    fees that are taken due to certain actions. Since Accounts have a 
    balance, they extend the BalanceHandler class to help with the basic
    functions of adding and removing funds.

AccountType.java:
    An enum that helps keep track of the different types of accounts.

Admin.java: 
    Admin is an extension of the User class. The admin is an
    elavated user that has the ability to see other users' 
    information as well as approving or denying loans.

ATM.java: Abstract ATM class, Bank.java extends it. It shows the basic functions of an ATM. The main purpose of this class is for future extension.

BalanceHandler.java:
    BalanceHandler is an abstract class that represents any
    entity that holds/stores an amount of money. BalanceHandler
    handles making changes to the balance, transferring a balance,
    and the different currency conversions based off of interactions
    with objects that have other currencies.

Bank.java: 
    The Bank class is a concrete implementation of the ATM class. This
    class provides functionality for most of the expected functions of
    a Bank/ATM such as creating new User profiles/accounts, requesting loans
    interacting with your accounts, and so on.

Checking.java:
    The Checking class is an extension of the Account class. Checking
    accounts are generally more specialized for transferring money
    between you and another user, but also incur a fee for all of
    their transactions.

Currency.java:
    The Currency class represents the different types of currencies
    that can be used at a bank. It holds information for each currency
    type like name, description, symbol, and the exchange rate to 
    US Dollars. It handles converting an amount of money from a different
    type to this currency type.

IO.java:
    The IO class handles reading and writing important 
    bank information to csv files such as info on accounts,
    users, loans, and daily transactions.

Loan.java:
    The Loan class keeps track of an amount of money owed to an
    individual (usually a bank admin). Unlike an Account, when money
    is put into a loan, it decreases the balance of the loan and is
    sent to the person who is owed money. The Loan class is also an
    extension of BalanceHandler since it keeps track of the money
    that is still due to the loaner.

Savings.java:
    The Savings class is an extension of the Account class. Savings
    accounts can accrue interest if there is over a specified amount
    of currency inside of them. This happens once every month and
    interest is gained at a specified rate that is chosen when
    the account is created.

Transaction.java:
    The Transaction class is a record of a transaction made
    between two classes that implement BalanceHandler. This 
    could be a deposit from a user to an account, a user 
    paying off a loan, a transfer between accounts, etc.

Customer.java: The class represents all customers, and also is a subclass of User class. It has interaction with loan.

User.java: Abstract class, and the class represents all users, which is super class of Customer and Admin, and also extends BalanceHandler.

UserType.java: The class is a enum class, which represents user types

------GUI------

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


