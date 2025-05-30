import java.sql.*;
import java.util.*;

public class BankSystem {
    private Connection conn;

    public BankSystem(Connection conn) {
        this.conn = conn;
    }

    public Account login(String accNum, String pin) {
        try {
            String sql = "SELECT * FROM accounts WHERE account_number = ? AND pin = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, accNum);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                double balance = rs.getDouble("balance");
                boolean isAdmin = rs.getBoolean("is_admin");
                return new Account(accNum, pin, name, balance, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showUserMenu(Account account, Scanner scanner) {
        while (true) {
            System.out.println("\nWelcome, " + account.getName());
            System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, account.getAccountNumber());
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            System.out.println("Balance: $" + rs.getDouble("balance"));
                        }
                    } catch (SQLException e) {
                        System.out.println("Error checking balance.");
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double deposit = scanner.nextDouble();
                    try {
                        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setDouble(1, deposit);
                        stmt.setString(2, account.getAccountNumber());
                        stmt.executeUpdate();
                        System.out.println("Deposit successful.");
                    } catch (SQLException e) {
                        System.out.println("Error during deposit.");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = scanner.nextDouble();
                    try {
                        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                        checkStmt.setString(1, account.getAccountNumber());
                        ResultSet rs = checkStmt.executeQuery();
                        if (rs.next() && rs.getDouble("balance") >= withdraw) {
                            String sql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setDouble(1, withdraw);
                            stmt.setString(2, account.getAccountNumber());
                            stmt.executeUpdate();
                            System.out.println("Withdrawal successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error during withdrawal.");
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void showAdminMenu(Account admin, Scanner scanner) {
        while (true) {
            System.out.println("\nWelcome, Admin");
            System.out.println("1. Open Account\n2. Close Account\n3. Modify Account\n4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter new account number: ");
                        String newAcc = scanner.nextLine();
                        System.out.print("Enter PIN: ");
                        String newPin = scanner.nextLine();
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        String insertSQL = "INSERT INTO accounts (account_number, pin, name, balance, is_admin) VALUES (?, ?, ?, 0.0, false)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
                        insertStmt.setString(1, newAcc);
                        insertStmt.setString(2, newPin);
                        insertStmt.setString(3, name);
                        insertStmt.executeUpdate();
                        System.out.println("Account created.");
                        break;

                    case 2:
                        System.out.print("Enter account number to remove: ");
                        String accToRemove = scanner.nextLine();
                        String deleteSQL = "DELETE FROM accounts WHERE account_number = ?";
                        PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
                        deleteStmt.setString(1, accToRemove);
                        deleteStmt.executeUpdate();
                        System.out.println("Account removed.");
                        break;

                    case 3:
                        System.out.print("Enter account number to modify: ");
                        String accToEdit = scanner.nextLine();
                        System.out.print("New name: ");
                        String newName = scanner.nextLine();
                        System.out.print("New PIN: ");
                        String newPinEdit = scanner.nextLine();
                        String updateSQL = "UPDATE accounts SET name = ?, pin = ? WHERE account_number = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
                        updateStmt.setString(1, newName);
                        updateStmt.setString(2, newPinEdit);
                        updateStmt.setString(3, accToEdit);
                        updateStmt.executeUpdate();
                        System.out.println("Account updated.");
                        break;

                    case 4:
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (SQLException e) {
                System.out.println("Admin action failed.");
                e.printStackTrace();
            }
        }
    }
}
