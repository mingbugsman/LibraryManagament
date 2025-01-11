package Library.Entities.Member;

public class PremiumMember extends Member{
    private final double membershipFee;
    private final double discountRentalRee;
    private final String PremiumID;
    public PremiumMember(String nameMember, String Id_user, String Address, String phoneNumber, String premiumID) {
        super(nameMember, Id_user, Address, phoneNumber);
        PremiumID = premiumID;
        this.membershipFee = 59.0;
        this.discountRentalRee = 0.32;
        this.setReserveBook(true);
        this.setMaxBorrowLimit(8);
    }

    @Override
    public String geTypeMember() {
        return "premium";
    }

    public double getMembershipFee() {
        return membershipFee;
    }

    public double getDiscountRentalRee() {
        return discountRentalRee;
    }

    public String getPremiumID() {
        return PremiumID;
    }
}
