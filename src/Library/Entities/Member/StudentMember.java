package Library.Entities.Member;

public class StudentMember extends Member {
    private String StudentID;
    private final double discountRentalFee; // discount when student rent digital or limited book !
    public StudentMember(String StudentID,String nameMember, String Id_user, String Address, String phoneNumber) {
        super(nameMember, Id_user, Address, phoneNumber);
        this.StudentID = StudentID;
        this.discountRentalFee = 0.35;
    }

    public String getStudentId() {
        return StudentID;
    }
    public void setStudentId(String StudentId) {
         this.StudentID = StudentId;
    }

    public double getDiscountRentalFee() {
        return discountRentalFee;
    }

    @Override
    public String getRole() {
        return "student";
    }
}
