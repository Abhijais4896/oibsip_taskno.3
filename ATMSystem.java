import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMSystem {
    private JFrame frame;
    private JLabel cardNumberLabel;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton balanceButton;
    private JLabel resultLabel;
    private double accountBalance;
    private String correctPin = "1234";

    public ATMSystem() {
        // Create the main frame
        frame = new JFrame("ATM System");

        // Initialize account balance
        accountBalance = 1000.00;

        // Create the components
        cardNumberLabel = new JLabel("Enter PIN:");
        pinField = new JPasswordField(10);
        loginButton = new JButton("Login");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        balanceButton = new JButton("Balance Inquiry");
        resultLabel = new JLabel("");

        // Set up the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(cardNumberLabel);
        panel.add(pinField);
        panel.add(loginButton);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(resultLabel);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPin = new String(pinField.getPassword());

                // Check the entered PIN
                if (enteredPin.equals(correctPin)) {
                    enableTransactions();
                    resultLabel.setText("Login successful!");
                } else {
                    disableTransactions();
                    resultLabel.setText("Invalid PIN. Please try again.");
                }

                // Clear the PIN field
                pinField.setText("");
            }
        });

        // Add action listener to the withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                double amount = Double.parseDouble(amountString);

                // Perform withdrawal
                if (amount > 0 && amount <= accountBalance) {
                    accountBalance -= amount;
                    resultLabel.setText("Withdrawal successful. Current balance: $" + accountBalance);
                } else {
                    resultLabel.setText("Invalid withdrawal amount or insufficient balance.");
                }
            }
        });

        // Add action listener to the deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                double amount = Double.parseDouble(amountString);

                // Perform deposit
                if (amount > 0) {
                    accountBalance += amount;
                    resultLabel.setText("Deposit successful. Current balance: $" + accountBalance);
                } else {
                    resultLabel.setText("Invalid deposit amount.");
                }
            }
        });

        // Add action listener to the balance inquiry button
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("Current balance: $" + accountBalance);
            }
        });

        // Disable transaction buttons initially
        disableTransactions();

        // Set up the main frame
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void enableTransactions() {
        withdrawButton.setEnabled(true);
        depositButton.setEnabled(true);
        balanceButton.setEnabled(true);
    }

    private void disableTransactions() {
        withdrawButton.setEnabled(false);
        depositButton.setEnabled(false);
        balanceButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMSystem();
            }
        });
    }
}
