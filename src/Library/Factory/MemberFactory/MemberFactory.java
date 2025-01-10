package Library.Factory.MemberFactory;

import Library.Entities.Member.Member;

public interface MemberFactory {
    Member createRegularMember(String nameMember, String Id_user, String Address, String phoneNumber);
    Member creeateStudentMember(String StudentID,String nameMember, String Id_user, String Address, String phoneNumber);
    Member createPremiumMember(String nameMember, String Id_user, String Address, String phoneNumber);
}
