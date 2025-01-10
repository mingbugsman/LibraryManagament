package Library.Entities.Member;

public class PremiumMember extends Member{
    private final double membershipFee;

    public PremiumMember(String nameMember, String Id_user, String Address, String phoneNumber) {
        super(nameMember, Id_user, Address, phoneNumber);
        this.membershipFee = 59.0;
    }

    @Override
    public String getRole() {
        return "premium member";
    }

    public double getMembershipFee() {
        return membershipFee;
    }

}
