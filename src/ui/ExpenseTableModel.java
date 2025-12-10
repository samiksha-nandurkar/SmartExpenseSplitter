package ui;

import model.Expense;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ExpenseTableModel extends AbstractTableModel {

    private final String[] columns = {"Description", "Amount", "Paid By"};
    private List<Expense> expenses;

    public ExpenseTableModel(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public int getRowCount() {
        return expenses.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Expense e = expenses.get(rowIndex);
        switch (columnIndex) {
            case 0: return e.getDescription();
            case 1: return e.getAmount();
            case 2: return e.getPaidBy().getName();
            default: return "";
        }
    }
}

