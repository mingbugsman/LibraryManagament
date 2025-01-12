package Library.UseCasesForLibrary.UseCases_ReturnBook;

import Library.Entities.Book.Book;
import Library.Entities.BookReturnDetail.BookReturnDetail;
import Library.Entities.Member.Member;
import Library.ErrorManagement.ReturnBookException;

import java.time.LocalDate;
import java.util.List;

public class ReturnBookManager {
    private final IReturnBookRepository _ReturnBookRepository;

    /**
     * Initializes the ReturnBookManager with the specified repository.
     *
     * @param returnBookRepository The repository that manages book return details.
     */
    public ReturnBookManager(IReturnBookRepository returnBookRepository) {
        _ReturnBookRepository = returnBookRepository;
    }

    /**
     * Adds the return details of a book for a member.
     *
     * @param member         The member who is returning the book.
     * @param book           The book being returned.
     * @param dueReturnDate  The due date when the book was supposed to be returned.
     * @throws ReturnBookException if the book return detail cannot be added.
     */
    public void addBookReturnDetail(Member member, Book book, LocalDate dueReturnDate) throws ReturnBookException {
        _ReturnBookRepository.addBookReturnDetail(member, book, dueReturnDate);
    }

    /**
     * Updates the book return details with new information.
     *
     * @param updateDetail The updated book return detail with return date and late fee.
     * @throws ReturnBookException if the book return detail cannot be updated.
     */
    public void updateBookReturnDetail(BookReturnDetail updateDetail) throws ReturnBookException {
        _ReturnBookRepository.updateBookReturnDetail(updateDetail);
    }

    /**
     * Updates the return day for a book based on memberID and BookID.
     *
     * @param ReturnDay    The actual date when the book was returned.
     * @param memberID     The ID of the member returning the book.
     * @param BookID       The ID of the book being returned.
     * @throws ReturnBookException if there is an issue with updating the return day.
     */
    public void updateReturnedDay(LocalDate ReturnDay, String memberID, String BookID) throws ReturnBookException {
        _ReturnBookRepository.updateReturnedDay(ReturnDay, memberID, BookID);
    }

    /**
     * Checks if a specific book has been returned by the member.
     *
     * @param memberID The ID of the member whose book return status is being checked.
     * @param bookID   The ID of the book whose return status is being checked.
     * @return true if the book has been returned, false otherwise.
     * @throws ReturnBookException if the book or member does not exist.
     */
    public boolean isBookReturned(String memberID, String bookID ) throws ReturnBookException {
        return _ReturnBookRepository.isBookReturned(memberID, bookID);
    }

    /**
     * Checks if a specific book return detail exists for a given member.
     *
     * @param ID_member The ID of the member who borrowed the book.
     * @param ID_book   The ID of the book being checked for return detail.
     * @return true if the book return detail exists, false otherwise.
     */
    public boolean isExistBookReturnDetail(String ID_member, String ID_book) {
        return _ReturnBookRepository.isExistBookReturnDetail(ID_member, ID_book);
    }

    /**
     * Retrieves the book return details for a specific member and book.
     *
     * @param memberId The ID of the member.
     * @param bookId   The ID of the book.
     * @return The return details of the specified book for the given member.
     */
    public BookReturnDetail getBookReturnDetail(String memberId, String bookId) {
        return _ReturnBookRepository.getBookReturnDetail(memberId, bookId);
    }

    /**
     * Retrieves all book return details.
     *
     * @return A list of all book return details.
     */
    public List<BookReturnDetail> getAllBookReturnDetails() {
        return _ReturnBookRepository.getAllBookReturnDetails();
    }

    /**
     * Filters book return details by the time period and return status.
     *
     * @param startDate  The start date of the filtering period.
     * @param endDate    The end date of the filtering period.
     * @param isReturned A boolean flag to filter by books that have or have not been returned.
     * @return A list of book return details within the specified time period and matching the return status.
     * @throws ReturnBookException if the date range is invalid or there is an issue with filtering.
     */
    public List<BookReturnDetail> filterBookReturnDetailsByTime(LocalDate startDate, LocalDate endDate, boolean isReturned) throws ReturnBookException {
        return _ReturnBookRepository.filterBookReturnDetailsByTime(startDate,endDate,isReturned);
    }

    /**
     * Retrieves all books that have been returned.
     *
     * @return A list of all returned books.
     */
    public List<BookReturnDetail> getAllReturnedBooks() {
        return _ReturnBookRepository.getAllReturnedBooks();
    }
}
