package Library.Factory.MemberFactory;

import Library.Entities.Member.Member;

/* role member :
regular
premium member
student
 */



public class MemberFactoryProducer {
    public static enum ListRole {
        regular,
        premiumMember,
        student
    }

    public static Member createNewMember(ListRole role ,String nameMember, String Id_user, String Address, String phoneNumber, String StudentID) {
        return switch (role) {
            case regular -> new TypeMemberFactory().createRegularMember(nameMember, Id_user, Address, phoneNumber);
            case premiumMember -> new TypeMemberFactory().createPremiumMember(nameMember, Id_user, Address, phoneNumber);
            case student -> new TypeMemberFactory().creeateStudentMember(StudentID, nameMember, Id_user, Address, phoneNumber);
            default -> throw new IllegalArgumentException("Not Found Role Member");
        };
    }
}
