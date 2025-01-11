package Library.Entities.Member;

public class StudentMember extends Member {
    private String StudentID;

    public StudentMember(String StudentID,String nameMember, String Id_user, String Address, String phoneNumber) {
        super(nameMember, Id_user, Address, phoneNumber);
        this.StudentID = StudentID;

        this.setMaxBorrowLimit(5);
    }
    public String getStudentId() {
        return StudentID;
    }
    public void setStudentId(String StudentId) {
         this.StudentID = StudentId;
    }

    @Override
    public String geTypeMember() {
        return "student";
    }
}
