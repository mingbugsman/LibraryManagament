package Library.UseCasesForLibrary.UseCases_Member;

import Library.Entities.Member.Member;

import java.util.List;

public interface IMemberRepository {
    boolean isExistedMember(String ID_member);
    List<Member> searchingMemberByName(String Name);
    List<Member> getMembers();
    Member getMember(String Id_member);
    List<Member> getMembersByType(String typeMember);
    void addMember(Member newMember);
    void removeMember(Member Member);
    Member updateMember(Member updateMember);

}
