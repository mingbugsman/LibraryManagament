package Library.UseCasesForLibrary;

import Library.Entities.Member.Member;

import java.util.List;

public interface IMemoryMemberRepository {
    List<Member> searchingMemberByName(String Name);
    List<Member> getMembers();
    Member getMember(String Id_member);
    List<Member> getMembersByType(String typeMember);
    void addMember(Member newMember);
    void removeMember(Member Member);
    Member updateMember(Member updateMember);

}
