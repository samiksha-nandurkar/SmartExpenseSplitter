package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private Group group;
    private ExpenseTableModel tableModel;
    private JTable table;

    private JTextField txtDescription;
    private JTextField txtAmount;
    private JComboBox<User> comboPaidBy;

    public MainFrame() {
        setTitle("Smart Expense Splitter");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initData();
        initUI();
    }

    private void initData() {
        group = new Group("Goa Trip 2025");
        User u1 = new User("Samiksha");
        User u2 = new User("Amit");
        User u3 = new User("Pooja");

        group.addMember(u1);
        group.addMember(u2);
        group.addMember(u3);

        group.addExpense(new Expense("Hotel", 6000, u1));
        group.addExpense(new Expense("Dinner", 1500, u2));
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Top panel
        JLabel lblTitle = new JLabel("Group: " + group.getName(), SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        // Table center
        tableModel = new ExpenseTableModel(group.getExpenses());
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom form panel
        JPanel bottom = new JPanel(new GridLayout(2, 1));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtDescription = new JTextField(10);
        txtAmount = new JTextField(6);
        comboPaidBy = new JComboBox<>(group.getMembers().toArray(new User[0]));

        form.add(new JLabel("Description:"));
        form.add(txtDescription);
        form.add(new JLabel("Amount:"));
        form.add(txtAmount);
        form.add(new JLabel("Paid By:"));
        form.add(comboPaidBy);

        JButton btnAdd = new JButton("Add Expense");
        btnAdd.addActionListener(e -> addExpense());
        form.add(btnAdd);

        bottom.add(form);

        JButton btnSettlement = new JButton("Show Simple Settlement");
        btnSettlement.addActionListener(e -> showSettlement());
        JPanel settlementPanel = new JPanel();
        settlementPanel.add(btnSettlement);

        bottom.add(settlementPanel);
        add(bottom, BorderLayout.SOUTH);
    }

    private void addExpense() {
        try {
            String desc = txtDescription.getText();
            double amt = Double.parseDouble(txtAmount.getText());
            User paidBy = (User) comboPaidBy.getSelectedItem();

            group.addExpense(new Expense(desc, amt, paidBy));
            tableModel.fireTableDataChanged();
            txtDescription.setText("");
            txtAmount.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid amount.");
        }
    }

    private void showSettlement() {
        double avg = group.getExpenses().stream().mapToDouble(Expense::getAmount).sum()
                / group.getMembers().size();

        StringBuilder sb = new StringBuilder("Simple Settlement (Total/Persons): " + avg + "\n\n");
        for (User u : group.getMembers()) {
            double paid = group.getExpenses().stream()
                    .filter(e -> e.getPaidBy().equals(u))
                    .mapToDouble(Expense::getAmount)
                    .sum();
            double diff = paid - avg;
            sb.append(u.getName())
              .append(" : ")
              .append(String.format("%.2f", diff))
              .append(diff >= 0 ? " (should receive)\n" : " (should pay)\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
