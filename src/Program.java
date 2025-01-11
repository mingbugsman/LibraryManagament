import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowedException.BorrowException;
import Library.Factory.BookFactory.BookFactoryProducer;
import Library.Factory.MemberFactory.MemberFactoryProducer;
import Library.Infractures.InMemoryBookRepository;
import Library.Infractures.InMemoryBookTransactionRepository;
import Library.Infractures.InMemoryMemberRepository;
import Library.LibraryManager.BorrowManager.BorrowManager;
import Library.LibraryManager.BorrowManager.BorrowsStrategy;
import Library.LibraryManager.BorrowManager.IBorrowStrategy;
import Library.Ulti.IntializeListEntity.InitializeListBook;
import Library.Ulti.IntializeListEntity.InitializeListMember;
import Library.UseCasesForLibrary.UseCases_Book.IBookRepository;
import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;
import Library.UseCasesForLibrary.UseCases_BorrowBookTransaction.BorrowTransactionManagement;
import Library.UseCasesForLibrary.UseCases_BorrowBookTransaction.IBookTransactionRepository;
import Library.UseCasesForLibrary.UseCases_Member.IMemberRepository;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        Book book = BookFactoryProducer.createBook("limited",
                "123KS","God Of Shattering", "Tran Tuan Minh",
                152,12.2);

        Member memberA = MemberFactoryProducer.createNewMember("regular", "NGguyen Van A", "1211", "Nguyne Thi Rang Trung Lap Ha", "0389773126", "");
        memberA.getDescription();

        Member memberB = MemberFactoryProducer.createNewMember("regular", "NGguyen Van 182", "11923", "Nguyne Thi Rang Trung Lap Ha", "0389773126", "");


        IBookRepository bookRepository = new InMemoryBookRepository();
        IMemberRepository memberRepository = new InMemoryMemberRepository();

        ManagerBook managerBook = new ManagerBook(bookRepository);
        managerBook.AddNewBook(book);
        ManagerMember managerMember = new ManagerMember(memberRepository);
        managerMember.addMember(memberA);
        managerMember.addMember(memberB);

        IBookTransactionRepository bookTransactionRepository = new InMemoryBookTransactionRepository(managerMember, managerBook);
        BorrowTransactionManagement borrowTransactionManagement = new BorrowTransactionManagement(bookTransactionRepository);

        IBorrowStrategy borrowStrategy = BorrowsStrategy.createBorrowsStrategy(borrowTransactionManagement,managerBook, managerMember);
        BorrowManager borrowManager = new BorrowManager();



        // Initialize
        InitializeListBook.Initialize(managerBook);
        InitializeListMember.Initialize(managerMember);
        List<Book> temp= managerBook.getListBooks();
        System.out.println(temp.get(4).getQuantity());

        System.out.println();
        System.out.println();
        // testing
        try {
            borrowManager.borrowBook(borrowStrategy,memberA,book,200);
            borrowManager.borrowBook(borrowStrategy,memberA,temp.get(5),200);
            borrowManager.borrowBook(borrowStrategy,memberA,temp.get(4),200);


            borrowManager.borrowBook(borrowStrategy,memberB,temp.get(4),101);

        } catch (BorrowException e) {
            System.out.println(e.getMessage());
        }


    }
}
