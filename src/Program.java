import Library.Entities.Book.Book;
import Library.Entities.Book.NormalBook;
import Library.Entities.Member.Member;
import Library.Factory.BookFactory.BookFactoryProducer;
import Library.Factory.MemberFactory.MemberFactoryProducer;

public class Program {
    public static void main(String[] args) {
        Member memberA = MemberFactoryProducer.createNewMember("regular", "NGguyen Van A", "1211", "Nguyne Thi Rang Trung Lap Ha", "0389773126", "HS0120");
        memberA.getDescription();
    }
}
