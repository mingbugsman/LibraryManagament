package Library.UseCasesForLibrary.UseCases_Member;

import Library.Entities.Member.Member;

import java.util.List;

public class ManagerMember {
    private final IMemberRepository _memberRepository;


    public ManagerMember(IMemberRepository iMemberRepository) {
        this._memberRepository = iMemberRepository;
    }

    public boolean isExistedMember(String ID_member) {
       return  _memberRepository.isExistedMember(ID_member);
    }

    public Member getMember(String Id_member) {
        return _memberRepository.getMember(Id_member);
    }

    public List<Member> getMembers() {
        return _memberRepository.getMembers();
    }

    public List<Member> searchMemberByName(String name) {return _memberRepository.searchingMemberByName(name);}


    public List<Member> getMembersByType(String typeMember) {return  _memberRepository.getMembersByType(typeMember);}


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
