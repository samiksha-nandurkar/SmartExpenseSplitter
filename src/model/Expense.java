package model;

public class Expense {
    private String description;
    private double amount;
    private User paidBy;

    public Expense(String description, double amount, User paidBy) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
    }

    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public User getPaidBy() { return paidBy; }
}
