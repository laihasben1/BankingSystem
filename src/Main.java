import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DBConnection.connect();
        
        if (conn == null) {
            System.out.println("Failed to connect to the database. Exiting.");
            return;
        }

        BankSystem system = new BankSystem(conn);

        System.out.println("Welcome to the Bank System");
        System.out.println("Are you a:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.print("Enter choice: (just the number, ex. 1): ");
        int role = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account user = system.login(accNum, pin);
        if (user == null || (role == 1 && user.isAdmin()) || (role == 2 && !user.isAdmin())) {
            System.out.println("Invalid login.");
            return;
        }

        if (user.isAdmin()) {
            system.showAdminMenu(user, scanner);
        } else {
            system.showUserMenu(user, scanner);
        }

        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error closing connection.");
        }

        scanner.close();
    }
}
