package Library.Entities.Member;

public class PremiumMember extends Member{
    private final double membershipFee;
    private final double discountRentalRee;

    public PremiumMember(String nameMember, String Id_user, String Address, String phoneNumber ) {
        super(nameMember, Id_user, Address, phoneNumber);
        this.membershipFee = 59.0;
        this.discountRentalRee = 0.32;
        this.setReserveBook(true);
        this.setTimesBorrowed(8);
    }

    @Override
    public String getRole() {
        return "premium";
    }

    public double getMembershipFee() {
        return membershipFee;
    }

    public double getDiscountRentalRee() {
        return discountRentalRee;
    }
}
