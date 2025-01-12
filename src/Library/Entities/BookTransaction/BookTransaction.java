package Library.Entities.BookTransaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookTransaction {
    private String ID_member;
    private String ID_book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    // constructor
    public BookTransaction(String ID_member , String ID_book, LocalDate borrowDate, LocalDate dueDate) {
        this.ID_member = ID_member;
        this.ID_book = ID_book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // Getter and Setter for borrowDate and dueDate
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Getter and setter for member
    public String getID_member() {
        return ID_member;
    }
    public void setID_member(String ID_member) {
        this.ID_member = ID_member;
    }

    // Getter ans setter for book
    public String getID_Book() {
        return ID_book;
    }

    public void setID_Book(String ID_book) {
        this.ID_book = ID_book;
    }

}
