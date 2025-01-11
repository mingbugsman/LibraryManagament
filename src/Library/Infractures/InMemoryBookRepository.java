package Library.Infractures;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.UseCasesForLibrary.UseCases_Book.IBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements IBookRepository {
    private final List<Book> Books;

    public InMemoryBookRepository() {
        this.Books = new ArrayList<>();
    }


    @Override
    public boolean isExistedBook(String ID_book) {
        Book foundBook = GetBookByID(ID_book);
        return foundBook != null;
    }
    @Override
    public Book GetBookByID(String IdBook) {
        return  Books.stream().filter(book -> book.getIdBook().equals(IdBook)).findFirst().orElse(null);
    }

    @Override
    public List<Book> GetAllBooks() {
        return this.Books;
    }
    @Override
    public void Add(Book book) {

        if (!isExistedBook(book.getIdBook())) {

            Books.add(book);
        } else
            throw new IllegalArgumentException("Book has ID : " + book.getIdBook() + " is existed");

    }

    @Override
    public void Remove(Book book) {
        Books.remove(book);
    }

    @Override
    public Book updateBook(Book updateBook) {
        Book foundBook = Books.stream().filter(book -> book.getIdBook().equals(updateBook.getIdBook())).findFirst().orElse(null);
        if (foundBook == null) {
                throw new IllegalArgumentException("Book with ID " + updateBook.getIdBook() + " not found for update");

        }
        foundBook.setAuthor(updateBook.getAuthor());
        foundBook.setQuantity(updateBook.getQuantity());
        foundBook.setTitle(updateBook.getTitle());
        return foundBook;
    }




    // searching book by Title
    @Override
    public List<Book> searchBookByTitle(String title) {
        if (Books.isEmpty() || title == null || title.trim().isEmpty()) {
            return List.of(); //return empty list book;
        }
        return Books.stream().filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
    }

    // searching book by Author
    @Override
    public List<Book> searchBookByAuthor(String Author) {
        if (Books.isEmpty() || Author == null || Author.trim().isEmpty()) {
            return List.of(); //return empty list book;
        }
        return Books.stream().filter(book -> book.getAuthor().toLowerCase().contains(Author.toLowerCase())).collect(Collectors.toList());
    }
}
