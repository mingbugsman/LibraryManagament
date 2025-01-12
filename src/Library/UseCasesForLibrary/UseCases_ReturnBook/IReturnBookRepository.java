package Library.UseCasesForLibrary.UseCases_ReturnBook;

import Library.Entities.Book.Book;
import Library.Entities.BookReturnDetail.BookReturnDetail;
import Library.Entities.Member.Member;
import Library.ErrorManagement.ReturnBookException;

import java.time.LocalDate;
import java.util.List;

public interface IReturnBookRepository {
    void addBookReturnDetail(Member member, Book book, LocalDate dueReturnDate) throws ReturnBookException;
    void updateBookReturnDetail(BookReturnDetail updateDetail) throws ReturnBookException;
    void updateReturnedDay(LocalDate ReturnDay, String memberID, String BookID) throws ReturnBookException;
    boolean isBookReturned(String memberID, String bookID) throws ReturnBookException;
    boolean isExistBookReturnDetail(String ID_member, String ID_book);
    BookReturnDetail getBookReturnDetail(String memberId, String bookId);
    List<BookReturnDetail> getAllBookReturnDetails();
    List<BookReturnDetail> filterBookReturnDetailsByTime (LocalDate startDate, LocalDate endDate,boolean isReturned) throws ReturnBookException;
    List<BookReturnDetail> getAllReturnedBooks();
}
