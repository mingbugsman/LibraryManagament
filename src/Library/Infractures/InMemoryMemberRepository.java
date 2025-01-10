package Library.Infractures;

import Library.Entities.Member.Member;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMemberRepository extends InMemoryBookRepository {
    private final List<Member> members;
    public InMemoryMemberRepository() {
        members = new ArrayList<>();
    }

    public List<Member> getMembers() {
        return members;
    }
    public Member getMember(String Id_user) {
        Member foundMember = members.stream().filter(member -> member.getId_user().equals(Id_user)).findFirst().orElse(null);
        if (foundMember == null ) {
            throw new IllegalArgumentException("Book with ID " + Id_user + " not found");
        }
        return foundMember;
    }
    public void addMember(Member newMember) {
        String typeMember = newMember.getRole();
    }
    public void removeMember(Member member) {
        members.remove(member);
    }
    public Member updateMember(Member updateMember) {
        Member foundMember = getMember(updateMember.getId_user());
        if (foundMember != null) {
            foundMember.setNameMember(updateMember.getNameMember());
            foundMember.setAddress(updateMember.getAddress());
            foundMember.setPhoneNumber(updateMember.getPhoneNumber());
        }
        return foundMember;
    }
}
