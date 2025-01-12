package Library.Infractures;

import Library.Entities.Book.Book;
import Library.Entities.BookTransaction.BookTransaction;
import Library.Entities.Member.Member;
import Library.UseCasesForLibrary.UseCases_Book.IBookRepository;
import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;
import Library.UseCasesForLibrary.UseCases_BorrowBookTransaction.IBookTransactionRepository;
import Library.UseCasesForLibrary.UseCases_Member.IMemberRepository;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryBookTransactionRepository implements IBookTransactionRepository {
    private final List<BookTransaction> BookTransactions;
    private final ManagerBook managerBook;
    private final ManagerMember managerMember;
    public InMemoryBookTransactionRepository(ManagerMember managerMember, ManagerBook managerBook) {
        this.BookTransactions = new ArrayList<>();
        this.managerBook = managerBook;
        this.managerMember = managerMember;
    }
    
    // does borrow book duplicate ?
    public boolean isExistedBookTransaction(String ID_book, String ID_member) {
        if (ID_book.trim().isEmpty()) {
            throw new IllegalArgumentException("NOT FOUND ID BOOK");
        }
        if (ID_member.trim().isEmpty()) {
            throw new IllegalArgumentException("NOT FOUND ID MEMBER");
        }
        BookTransaction foundBB = BookTransactions.stream().filter(BookTransaction -> BookTransaction.getID_Book().equals(ID_book)
                && BookTransaction.getID_member().equals(ID_member))
                .findFirst()
                .orElse(null);
        return foundBB != null;
    }
    // does member exist in list member

    // does book exist in list book


    // use 3 method above for checking to add
    private boolean checkingForAdd(String ID_Member, String ID_Book) {
        if (!managerBook.isExistedBook(ID_Book)) {
            throw new IllegalArgumentException("NOT FOUND ID BOOK IN LIST BOOKS");
        }
        if (!managerMember.isExistedMember(ID_Member)) {
            throw new IllegalArgumentException("NOT FOUND ID MEMBER IN LIST MEMBERS");
        }
        if (isExistedBookTransaction(ID_Book,ID_Member)) {
            throw new IllegalArgumentException("THIS BOOK HAS BEEN BORROWED !");
        }
        return true;
    }
    // use 3 method above for checking to remove
    private boolean checkingForRemove(String ID_Member, String ID_Book) {
        if (!managerBook.isExistedBook(ID_Book)) {
            throw new IllegalArgumentException("NOT FOUND ID BOOK IN LIST BOOKS");
        }
        if (!managerMember.isExistedMember(ID_Member)) {
            throw new IllegalArgumentException("NOT FOUND ID MEMBER IN LIST MEMBERS");
        }
        if (!isExistedBookTransaction(ID_Book,ID_Member)) {
            throw new IllegalArgumentException("THIS BOOK HASN'T BEEN BORROWED !");
        }
        return true;
    }

    // Check if the borrowing date is within the specified time range
    private boolean isWithinRange(LocalDate borrowDate, LocalDate startDate, LocalDate endDate) {

        return (borrowDate.isEqual(startDate) || borrowDate.isAfter(startDate)) &&
                (borrowDate.isEqual(endDate) || borrowDate.isBefore(endDate));
    }

    @Override
    public void Add(BookTransaction addObj) {
        String ID_Book = addObj.getID_Book();
        String ID_Member = addObj.getID_member();
        if(checkingForAdd(ID_Member, ID_Book)) {
            BookTransactions.add(addObj);
        }
    }

    @Override
    public void Remove(BookTransaction removeObj) {
        String ID_Book = removeObj.getID_Book();
        String ID_Member = removeObj.getID_member();
        if(checkingForRemove(ID_Member, ID_Book)) {
            BookTransactions.remove(removeObj);
        }
    }

    @Override
    public BookTransaction Update(BookTransaction updateObj) {
        BookTransaction foundBookTransaction = getBookTransaction(updateObj.getID_member(), updateObj.getID_Book());
        if (foundBookTransaction == null) {
            throw new IllegalArgumentException("NOT FOUND ID MEMBER IN LIST BookTransaction");
        }
        foundBookTransaction.setID_Book(updateObj.getID_Book());
        foundBookTransaction.setID_member(updateObj.getID_member());
        foundBookTransaction.setBorrowDate(updateObj.getBorrowDate());
        foundBookTransaction.setDueDate(updateObj.getBorrowDate());
        return foundBookTransaction;
    }

    @Override
    public List<BookTransaction> getAll() {
        return BookTransactions;
    }

    @Override
    public BookTransaction getBookTransaction(String ID_member,String ID_book) {
        return BookTransactions.stream().filter(BookTransaction ->
                BookTransaction.getID_member().equals(ID_member)
                && BookTransaction.getID_Book().equals(ID_book))
                .findFirst()
                .orElse(null);
    }



    @Override
    public List<BookTransaction> getByDuringTime(LocalDate startDate, LocalDate endDate) {
        return BookTransactions.stream()
                .filter(bookTransaction ->
                        isWithinRange(bookTransaction.getBorrowDate(), startDate, endDate))
                .collect(Collectors.toList());
    }
}
