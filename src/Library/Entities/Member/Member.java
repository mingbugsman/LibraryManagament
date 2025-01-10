package Library.Entities.Member;

import Library.Entities.Book.Book;
import Library.UseCasesForLibrary.BorrowStrategy.BorrowStrategy;

import java.util.List;

public abstract class Member {
    
    private final String Id_user;
    private String nameMember;
    private String Address;
    private String PhoneNumber;
    private List<Book> BorrowedBooks;
    private boolean reserveBook;
    private int TimesBorrowed;

    // constructor
    public Member(String nameMember, String Id_user, String Address, String phoneNumber) {
        this.nameMember = nameMember;
        this.Address = Address;
        this.PhoneNumber = phoneNumber;
        this.Id_user = Id_user;
        this.reserveBook = false;
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


    // getters setters for MaxTimesBorrowed

    public int getTimesBorrowed() {
        return TimesBorrowed;
    }
    public void setTimesBorrowed(int maxTimesBorrowed) {
        TimesBorrowed = maxTimesBorrowed;
    }


    //
    public List<Book> getBorrowedBook() {
        return BorrowedBooks;
    }

    public void setBorrowedBooks(Book borrowedBooks) {
        this.BorrowedBooks.add(borrowedBooks);
        int currentTimesBorrowed = getTimesBorrowed();
        setTimesBorrowed(currentTimesBorrowed--);
    }

    // get Description
    public void getDescription() {
        System.out.println("Name member : " + getNameMember() + "\n" +
                "Phone number :" + getPhoneNumber() + "\n" +
                "Address : " + getAddress() + "\n" +
                "Type Member : " + getRole() + "member"
        );
    }

    public abstract String getRole();
}
