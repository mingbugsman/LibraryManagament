package Library.UseCasesForLibrary;

import Library.Entities.Member.Member;

import java.util.List;

public interface IMemoryMemberRepository {
    List<Member> getMembers();
    Member getMember(String Id_member);
    void addMember(Member newMember);
    void removeMember(Member Member);
    Member updateMember(Member updateMember);

}
