package Library.Factory.MemberFactory;

import Library.Entities.Member.Member;
import Library.Entities.Member.PremiumMember;
import Library.Entities.Member.RegularMember;
import Library.Entities.Member.StudentMember;

public class TypeMemberFactory implements MemberFactory {

    @Override
    public Member createRegularMember(String nameMember, String Id_user, String Address, String phoneNumber) {
        return new RegularMember(nameMember, Id_user, Address, phoneNumber);
    }

    @Override
    public Member creeateStudentMember(String StudentID, String nameMember, String Id_user, String Address, String phoneNumber ) {
        return new StudentMember(StudentID, nameMember, Id_user, Address, phoneNumber);
    }

    @Override
    public Member createPremiumMember(String nameMember, String Id_user, String Address, String phoneNumber, String PremiumID ) {
        return new PremiumMember(nameMember, Id_user, Address, phoneNumber,PremiumID);
    }
}
