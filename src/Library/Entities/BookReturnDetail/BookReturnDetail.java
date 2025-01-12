package Library.Entities.BookReturnDetail;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookReturnDetail {
    private String ID_book;
    private String ID_member;
    private LocalDate returnDate;
    private LocalDate dueReturnDate;
    private double lateFee;

    public BookReturnDetail(String ID_book, String ID_member, LocalDate dueReturnDate) {
        this.ID_book = ID_book;
        this.ID_member = ID_member;
        this.returnDate = null;
        this.dueReturnDate = dueReturnDate;
        this.lateFee = -1;
    }
    // getter amd setter for ID book

    public String getID_book() {
        return ID_book;
    }

    public void setID_book(String ID_book) {
        this.ID_book = ID_book;
    }

    // getter and setter for ID member


    public String getID_member() {
        return ID_member;
    }

    public void setID_member(String ID_member) {
        this.ID_member = ID_member;
    }

    // getter and setter return book date


    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // getter and setter due book


    public LocalDate getDueReturnDate() {
        return dueReturnDate;
    }

    public void setDueReturnDate(LocalDate dueReturnDate) {
        this.dueReturnDate = dueReturnDate;
    }

    // getter and setter Late fee


    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }


    public long calculateOverdueDays() {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(dueReturnDate)) {
            return ChronoUnit.DAYS.between(dueReturnDate, currentDate);
        }
        return 0;
    }
}
