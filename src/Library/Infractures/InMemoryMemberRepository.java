package Library.Infractures;
import Library.Entities.Member.Member;
import Library.Factory.MemberFactory.TypeMemberFactory;
import Library.UseCasesForLibrary.IMemoryMemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryMemberRepository implements IMemoryMemberRepository {
    private final List<Member> members;
    public InMemoryMemberRepository() {
        members = new ArrayList<>();
    }

    // searching member by name
    public List<Member> searchingMemberByName(String Name) {
        if (members.isEmpty() || Name.trim().isEmpty()) {
            return List.of();
        }
        return
                members.stream().filter(member -> member.getNameMember().toLowerCase().contains(Name.toLowerCase())).collect(Collectors.toList());
    }

    // get all members
    public List<Member> getMembers() {
        return members;
    }


    // get member by id
    public Member getMember(String Id_user) {
        Member foundMember = members.stream().filter(member -> member.getId_user().equals(Id_user)).findFirst().orElse(null);
        if (foundMember == null ) {
            throw new IllegalArgumentException("Book with ID " + Id_user + " not found");
        }
        return foundMember;
    }


    // get member by type { student, regular or premium member }
    public List<Member> getMembersByType(String typeMember) {
        if (typeMember == null || typeMember.isBlank()) {
                throw new IllegalArgumentException("Type of Member cannot be null or empty");
        }
        return members.stream().filter(member -> member.getRole().equals(typeMember)).toList();
    }

    // add member
    public void addMember(Member newMember) {
        String typeMember = newMember.getRole();
    }

    // remove member
    public void removeMember(Member member) {
        members.remove(member);
    }

    // update member
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
