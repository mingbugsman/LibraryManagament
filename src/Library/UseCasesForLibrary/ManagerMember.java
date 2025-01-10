package Library.UseCasesForLibrary;

import Library.Entities.Member.Member;

import java.util.List;

public class ManagerMember {
    private final IMemoryMemberRepository _memberRepository;
    public ManagerMember(IMemoryMemberRepository iMemberRepository) {
        this._memberRepository = iMemberRepository;
    }
    public List<Member> getMembers() {
        return _memberRepository.getMembers();
    }
    public Member getMember(String Id_member) {
        return _memberRepository.getMember(Id_member);
    }
    public void addMember(Member newMember) {
        _memberRepository.addMember(newMember);
    }
    public void removeMember(Member member) {
        _memberRepository.removeMember(member);
    }
    public Member updateMember(Member updateMember) {
        return _memberRepository.updateMember(updateMember);
    }
}
