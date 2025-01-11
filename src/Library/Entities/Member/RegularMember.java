package Library.Entities.Member;

public class RegularMember extends Member {
    public RegularMember(String nameMember, String Id_user, String Address, String phoneNumber ) {
        super(nameMember, Id_user, Address, phoneNumber);
        this.setMaxBorrowLimit(3);
    }

    @Override
    public String geTypeMember() {
        return "regular";
    }
}
