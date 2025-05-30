public class Account {
    private String accountNumber;
    private String pin;
    private String name;
    private double balance;
    private boolean isAdmin;

    public Account(String accountNumber, String pin, String name, double balance, boolean isAdmin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getPin() { return pin; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public boolean isAdmin() { return isAdmin; }

    public void setName(String name) { this.name = name; }
    public void setPin(String pin) { this.pin = pin; }
    public void setBalance(double balance) { this.balance = balance; }
}

