package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BankBalance extends JFrame implements ActionListener {
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton depositBttn, withdrawBttn, showBalBttn, exitBttn;
    private double balance = 0.0;
    public BankBalance() {
        setTitle("Bank Balance Application");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(6, 1));
        balanceLabel = new JLabel("Current Balance: $0.00", SwingConstants.CENTER);
        amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("Enter Amount"));
        depositBttn = new JButton("Deposit");
        withdrawBttn = new JButton("Withdraw");
        showBalBttn = new JButton("Show Balance");
        exitBttn = new JButton("Exit");
        depositBttn.addActionListener(this);
        withdrawBttn.addActionListener(this);
        showBalBttn.addActionListener(this);
        exitBttn.addActionListener(this);
        panel.add(balanceLabel);
        panel.add(amountField);
        panel.add(depositBttn);
        panel.add(withdrawBttn);
        panel.add(showBalBttn);
        panel.add(exitBttn);
        add(panel);
        setVisible(true);
    }
    private Double getAmount() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            return null;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == depositBttn) {
            Double amount = getAmount();
            if (amount != null) {
                balance += amount;
                JOptionPane.showMessageDialog(this, "Deposited: $" + amount);
            }
        }
        else if (e.getSource() == withdrawBttn) {
            Double amount = getAmount();
            if (amount != null) {
                if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient Funds!");
                } else {
                    balance -= amount;
                    JOptionPane.showMessageDialog(this, "Withdrawn: $" + amount);
                }
            }
        }

        else if (e.getSource() == showBalBttn) {
            balanceLabel.setText(String.format("Current Balance: $%.2f", balance));
        }

        else if (e.getSource() == exitBttn) {
            JOptionPane.showMessageDialog(this,
                    String.format("Final Balance: $%.2f", balance));
            System.exit(0);
        }

        amountField.setText("");
    }

    public static void main(String[] args) {
        new BankBalance();
    }
}