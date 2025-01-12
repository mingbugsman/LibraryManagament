package Library.Infractures;

import Library.Entities.Book.Book;
import Library.Entities.BookReturnDetail.BookReturnDetail;
import Library.Entities.BookTransaction.BookTransaction;
import Library.Entities.Member.Member;
import Library.ErrorManagement.ReturnBookException;
import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;
import Library.UseCasesForLibrary.UseCases_BorrowBookTransaction.BorrowTransactionManagement;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;
import Library.UseCasesForLibrary.UseCases_ReturnBook.IReturnBookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class InMemoryReturnBookRepository implements IReturnBookRepository {
    private final List<BookReturnDetail> bookReturnDetailList;

    public InMemoryReturnBookRepository(BorrowTransactionManagement borrowTransactionManagement, ManagerMember managerMember,ManagerBook managerBook) {
        bookReturnDetailList = new ArrayList<>();

    }
    @Override
    public void addBookReturnDetail(Member member, Book book, LocalDate dueReturnDate) throws ReturnBookException {
        String ID_member = member.getId_user();
        String ID_book = book.getIdBook();

        if (isExistBookReturnDetail(ID_member,ID_book)) {
            throw new ReturnBookException("Can't add new Book Return Detail because it has been existed");
        }
        bookReturnDetailList.add(
                new BookReturnDetail(ID_member,ID_book,dueReturnDate)
        );
    }

    @Override
    public void updateBookReturnDetail(BookReturnDetail updateDetail) throws ReturnBookException {
        String ID_member = updateDetail.getID_member();
        String ID_book = updateDetail.getID_book();
        if (!isExistBookReturnDetail(ID_member,ID_book)) {
            throw new ReturnBookException("Can't update Book Return Detail because it hasn't been existed");
        }
        BookReturnDetail foundDetail = getBookReturnDetail(ID_member, ID_book);
        foundDetail.setID_book(updateDetail.getID_book());
        foundDetail.setID_member(updateDetail.getID_member());
        foundDetail.setDueReturnDate(updateDetail.getDueReturnDate());
        foundDetail.setReturnDate(updateDetail.getReturnDate());
        foundDetail.setLateFee(updateDetail.getLateFee());
        System.out.println("Successfully update detail");
    }

    @Override
    public void updateReturnedDay(LocalDate ReturnDay, String memberID, String BookID) throws ReturnBookException {
        BookReturnDetail found = getBookReturnDetail(memberID,BookID);
        if (found == null) {
            throw new ReturnBookException("NOT FOUND RETURN DETAIL");
        }
        if (isBookReturned(found.getID_member(), found.getID_book())) {
            throw new ReturnBookException("This book has been returned");
        }
        if (checkingDate(ReturnDay,found.getDueReturnDate())) {
            throw new ReturnBookException("Error :: Start date must be before end date");
        }
        found.setReturnDate(ReturnDay);
        double lateFee = calculateOverdueDays(ReturnDay,found.getDueReturnDate());
        found.setLateFee(lateFee);
        System.out.println("Successfully update");
    }

    @Override
    public boolean isBookReturned(String memberID,String bookID) throws ReturnBookException {
        BookReturnDetail bookReturnDetail = getBookReturnDetail(memberID, bookID);
        if (bookReturnDetail == null) {
            throw new ReturnBookException("NOT FOUND BOOK");
        }

        // Kiểm tra nếu returnDate khác null, nghĩa là sách đã được trả
        return bookReturnDetail.getReturnDate() != null;
    }

    @Override
    public boolean isExistBookReturnDetail(String ID_member, String ID_book) {
        BookReturnDetail foundObj = getBookReturnDetail(ID_member, ID_book);
        return foundObj != null;
    }

    @Override
    public BookReturnDetail getBookReturnDetail(String memberId, String bookId) {
        return bookReturnDetailList.stream().filter(bookReturnDetail ->
                bookReturnDetail.getID_book().equals(memberId)
                        && bookReturnDetail.getID_member().equals(bookId)).findFirst().orElse(null);
    }

    @Override
    public List<BookReturnDetail> getAllBookReturnDetails() {
        return bookReturnDetailList;
    }

    @Override
    public List<BookReturnDetail> filterBookReturnDetailsByTime (LocalDate startDate, LocalDate endDate, boolean isReturned) throws ReturnBookException {
        if (!checkingDate(startDate,endDate)) {
            throw new ReturnBookException("Error :: Start date must be before end date");
        }

        if (isReturned ) {
            return bookReturnDetailList.stream().filter(bookReturnDetail
                    -> isWithinRange(bookReturnDetail.getReturnDate(),startDate,endDate))
                    .toList();
        }
        return bookReturnDetailList.stream().filter(bookReturnDetail ->
                isWithinRange(bookReturnDetail.getDueReturnDate(),startDate,endDate))
                .toList();
    }

    @Override
    public List<BookReturnDetail> getAllReturnedBooks() {
        return bookReturnDetailList.stream().filter(bookReturnDetail ->
                bookReturnDetail.getReturnDate() != null).toList();
    }

    // method to calculateOverDueDays
    private int calculateOverdueDays(LocalDate returnedDate, LocalDate dueDate) {
        if (returnedDate.isAfter(dueDate)) {
            return (int) ChronoUnit.DAYS.between(dueDate, returnedDate);
        } else if (returnedDate.isEqual(dueDate)) {
            return 0;
        }
        return -1;
    }
    private boolean checkingDate(LocalDate st, LocalDate ed) {
        return st.isBefore(ed) ;
    }
    private boolean isWithinRange(LocalDate date, LocalDate st, LocalDate ed) {
        return  ( (date.isEqual(st) || date.isAfter(st)) &&
                (date.isEqual(ed) || date.isBefore(ed)));
    }

}
