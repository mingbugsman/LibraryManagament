package Library.Ulti.IntializeListEntity;

import Library.Entities.Book.Book;
import Library.Factory.BookFactory.BookFactoryProducer;

import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;

import java.util.List;

public class InitializeListBook {

    public static void Initialize(ManagerBook managerBook) {


        // 20 books
        List<Book> books = List.of(
                BookFactoryProducer.createBook("limited", "B001", "The Limitless Sky", "Author A", 300, 15.0),
                BookFactoryProducer.createBook("limited", "B002", "The Art of War", "Author B", 200, 18.5),
                BookFactoryProducer.createBook("limited", "B003", "God Of Shattering", "Tran Tuan Minh", 152, 12.2),
                BookFactoryProducer.createBook("limited", "B004", "Limitless Thinking", "Author C", 1, 20.0),

                BookFactoryProducer.createBook("digital", "D001", "Digital Transformation", "Author D", 250, 10.0),
                BookFactoryProducer.createBook("digital", "D002", "AI Revolution", "Author E", 400, 12.5),
                BookFactoryProducer.createBook("digital", "D003", "The World of Code", "Author F", 150, 8.0),
                BookFactoryProducer.createBook("digital", "D004", "Cloud Computing Basics", "Author G", 220, 11.0),
                BookFactoryProducer.createBook("digital", "D005", "Introduction to Algorithms", "Author H", 500, 25.0),

                BookFactoryProducer.createBook("regular", "R001", "Regular World", "Author I", 300,0),
                BookFactoryProducer.createBook("regular", "R002", "Peaceful Life", "Author J", 180, 0),
                BookFactoryProducer.createBook("regular", "R003", "The Basics of Life", "Author K", 100, 0),
                BookFactoryProducer.createBook("regular", "R004", "Learning to Fly", "Author L", 250, 7.5),
                BookFactoryProducer.createBook("regular", "R005", "Green Forest Adventures", "Author M", 400, 0),
                BookFactoryProducer.createBook("regular", "R006", "Magical Creatures", "Author N", 320, 0),
                BookFactoryProducer.createBook("regular", "R007", "Journey to the Unknown", "Author O", 280, 8),
                BookFactoryProducer.createBook("regular", "R008", "The Stars Above", "Author P", 500, 0),
                BookFactoryProducer.createBook("regular", "R009", "Moments of Serenity", "Author Q", 210, 0),
                BookFactoryProducer.createBook("regular", "R010", "The History of Time", "Author R", 450, 0)
        );

        // Thêm sách vào repository
        books.forEach(managerBook::AddNewBook);

        System.out.println("Successfully initialized 19 books into the repository!");
    }
}
