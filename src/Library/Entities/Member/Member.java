package Library.Entities.Member;

import Library.Entities.Book.Book;


import java.util.ArrayList;
import java.util.List;

public abstract class Member {
    
    private final String Id_user;
    private String nameMember;
    private String Address;
    private String PhoneNumber;
    private final List<Book> BorrowedBooks;
    private boolean reserveBook;
    private int MaxBorrowLimit;

    // constructor
    public Member(String nameMember, String Id_user, String Address, String phoneNumber) {
        this.nameMember = nameMember;
        this.Address = Address;
        this.PhoneNumber = phoneNumber;
        this.Id_user = Id_user;
        this.reserveBook = false;
        BorrowedBooks = new ArrayList<>();
    }

    // Traditional getter and setter for nameMember
    public String getNameMember() {
        return nameMember;
    }
    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    // Getter and setter for Id_user
    public String getId_user() {
        return Id_user;
    }

    
    // Getter and setter for Address
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }

    
    // Getter and setter for PhoneNumber
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    // Getter canReverseBook
    public boolean getReserveBook() {
        return reserveBook;
    }
    public void setReserveBook(boolean reserveBook) {
        this.reserveBook = reserveBook;
    }


    // getter setter for MaxTimesBorrowed
    public int getMaxBorrowLimit() {
        return MaxBorrowLimit;
    }
    public void setMaxBorrowLimit (int maxTimesBorrowed ) {
        MaxBorrowLimit = maxTimesBorrowed;
    }


    // getter setter for list borrowed books
    public List<Book> getBorrowedBook() {
        return BorrowedBooks;
    }
    public void setBorrowedBooks(Book borrowedBooks) {
        this.BorrowedBooks.add(borrowedBooks);
    }

    // Implement Interface

    // get information member
    public void getDescription() {
        System.out.println("Name member : " + getNameMember() + "\n" +
                "Phone number :" + getPhoneNumber() + "\n" +
                "Address : " + getAddress() + "\n" +
                "Type Member : " + geTypeMember() + "member"
        );
    }

    // abstract
    public abstract String geTypeMember();
}
