package Library.Ulti.IntializeListEntity;

import Library.Entities.Member.Member;
import Library.Factory.MemberFactory.MemberFactoryProducer;
import Library.Infractures.InMemoryMemberRepository;
import Library.UseCasesForLibrary.UseCases_Member.IMemberRepository;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;

import java.util.List;

public class InitializeListMember {

    public static void Initialize(ManagerMember managerMember) {



        // Danh sách thành viên
        List<Member> members = List.of(
                MemberFactoryProducer.createNewMember("regular", "Nguyen Van First", "1210", "Nguyen Thi Rang Trung Lap Ha", "0389773126", ""),
                MemberFactoryProducer.createNewMember("regular", "Tran Thi B", "1212", "Vo Van Tan, District 1", "0909456781", ""),
                MemberFactoryProducer.createNewMember("regular", "Le Hoang C", "1213", "Tran Hung Dao, District 5", "0355678945", ""),

                MemberFactoryProducer.createNewMember("premium", "Pham Minh D", "1301", "Le Van Sy, Tan Binh", "0378934561", "PR1201"),
                MemberFactoryProducer.createNewMember("premium", "Hoang An E", "1302", "Nguyen Thi Minh Khai, District 3", "0345986723", "PR0131"),
                MemberFactoryProducer.createNewMember("premium", "Nguyen Hai F", "1303", "Ly Chinh Thang, District 10", "0387234567", "PR0132"),

                MemberFactoryProducer.createNewMember("student", "Tran Van G", "1401", "Tan Phu District", "0339876543", "HS0140"),
                MemberFactoryProducer.createNewMember("student", "Le Thi H", "1402", "Go Vap District", "0375648231", "HS0141"),
                MemberFactoryProducer.createNewMember("student", "Hoang Van I", "1403", "Binh Thanh District", "0391234567", "HS0142")
        );

        // Thêm thành viên vào repository
        members.forEach(managerMember::addMember);

        System.out.println("Successfully initialized 9 members into the repository!");
    }
}
