package Library.UseCasesForLibrary.UseCases_BorrowBookTransaction;

import Library.Entities.BookTransaction.BookTransaction;

import java.time.LocalDate;
import java.util.List;

public class BorrowTransactionManagement {
    private final IBookTransactionRepository _bookTransactionRepository;

    /**
     * Initializes the BorrowTransactionManagement with the specified repository.
     *
     * @param bookTransactionRepository The repository that manages book transaction details.
     */
    public BorrowTransactionManagement(IBookTransactionRepository bookTransactionRepository) {
        _bookTransactionRepository = bookTransactionRepository;
    }

    /**
     * Adds a new book transaction to the repository.
     *
     * @param addObj The book transaction to be added.
     */
    public void Add(BookTransaction addObj) {
        _bookTransactionRepository.Add(addObj);
    }

    /**
     * Removes an existing book transaction from the repository.
     *
     * @param removeObj The book transaction to be removed.
     */
    public void Remove(BookTransaction removeObj) {
        _bookTransactionRepository.Remove(removeObj);
    }

    /**
     * Updates an existing book transaction in the repository.
     *
     * @param updateObj The book transaction to be updated.
     * @return The updated book transaction.
     */
    public BookTransaction Update(BookTransaction updateObj) {
        return _bookTransactionRepository.Update(updateObj);
    }

    /**
     * Checks if a specific book transaction exists for a given book and member.
     *
     * @param ID_book   The ID of the book in the transaction.
     * @param ID_member The ID of the member involved in the transaction.
     * @return true if the transaction exists, false otherwise.
     */
    public boolean isExistedBookTransaction(String ID_book, String ID_member) {
        return _bookTransactionRepository.isExistedBookTransaction(ID_book, ID_member);
    }

    /**
     * Retrieves all book transactions from the repository.
     *
     * @return A list of all book transactions.
     */
    List<BookTransaction> getAll() {
        return _bookTransactionRepository.getAll();
    }

    /**
     * Retrieves the book transaction for a specific member and book.
     *
     * @param ID_member The ID of the member.
     * @param ID_book   The ID of the book.
     * @return The book transaction for the specified member and book.
     */
    public BookTransaction getBookTransaction(String ID_member, String ID_book) {
        return _bookTransactionRepository.getBookTransaction(ID_member, ID_book);
    }

    /**
     * Retrieves a list of book transactions that occurred during a specific time period.
     *
     * @param startDate The start date of the filtering period.
     * @param endDate   The end date of the filtering period.
     * @return A list of book transactions within the specified time period.
     */
    public List<BookTransaction> getByDuringTime(LocalDate startDate, LocalDate endDate) {
        return _bookTransactionRepository.getByDuringTime(startDate, endDate);
    }

}
