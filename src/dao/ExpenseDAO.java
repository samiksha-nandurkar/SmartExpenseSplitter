package dao;

import db.DBConnection;
import model.Expense;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    public ExpenseDAO() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS expenses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                description TEXT,
                amount REAL,
                paidBy TEXT,
                groupName TEXT
            );
        """;
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveExpense(Expense e, String groupName) {
        String sql = "INSERT INTO expenses(description, amount, paidBy, groupName) VALUES (?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getDescription());
            ps.setDouble(2, e.getAmount());
            ps.setString(3, e.getPaidBy() != null ? e.getPaidBy().getName() : null);
            ps.setString(4, groupName);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Expense> loadExpenses(String groupName, List<User> members) {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT description, amount, paidBy FROM expenses WHERE groupName = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, groupName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String desc = rs.getString("description");
                    double amt = rs.getDouble("amount");
                    String paidByName = rs.getString("paidBy");
                    User paidBy = members.stream()
                                         .filter(u -> u.getName().equals(paidByName))
                                         .findFirst()
                                         .orElse(null);
                    list.add(new Expense(desc, amt, paidBy));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
