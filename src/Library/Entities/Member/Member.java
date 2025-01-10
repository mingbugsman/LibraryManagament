package Library.Entities.Member;

import Library.Entities.Book.Book;
import Library.Entities.Book.NormalBook;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Member {
    private String nameMember;
    private String Id_user;
    private String Address;
    private String PhoneNumber;
    private List<Book> BorrowedBook;
    public Member(String nameMember, String Id_user, String Address, String phoneNumber) {
        this.nameMember = nameMember;
        this.Address = Address;
        this.PhoneNumber = phoneNumber;
        this.Id_user = Id_user;
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

    public void setId_user(String Id_user) {
        this.Id_user = Id_user;
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

    // Getter and setter

    public List<Book> getBorrowedBook() {
        return BorrowedBook;
    }

    public void description() {
        System.out.println("Name member : " + getNameMember() + "\n" +
                "Phone number :" + getPhoneNumber() + "\n" +
                "Address : " + getAddress() + "\n" +
                "Type Member : " + getRole()
        );
    }

    public abstract String getRole();
}
