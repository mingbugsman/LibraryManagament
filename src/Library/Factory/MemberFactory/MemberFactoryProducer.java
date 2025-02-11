package Library.Factory.MemberFactory;

import Library.Entities.Member.Member;

/* type member :
regular
premium member
student
 */



public class MemberFactoryProducer {


    public static Member createNewMember(String typeMember ,String nameMember, String Id_user, String Address, String phoneNumber, String SpecialID) {
        return switch (typeMember) {
            case "regular" -> new TypeMemberFactory().createRegularMember(nameMember, Id_user, Address, phoneNumber);
            case "premium" -> new TypeMemberFactory().createPremiumMember(nameMember, Id_user, Address, phoneNumber,SpecialID);
            case "student" -> new TypeMemberFactory().creeateStudentMember(SpecialID, nameMember, Id_user, Address, phoneNumber);
            default -> throw new IllegalArgumentException("Not Found Role Member");
        };
    }
}
