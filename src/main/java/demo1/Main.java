package demo1;

import demo1.config.SpringConfig;
import demo1.entity.BookEntity;
import demo1.repository.BookRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = (BookRepository) applicationContext.getBean("bookRepository");

    public static void main(String[] args) {
        updateBook();
    }

    private static void createNewBook() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF1219323");
        bookEntity.setNumberOfPage(234);
        bookEntity.setPrice(20.5);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));

        BookEntity result = bookRepository.save(bookEntity);

        if (result != null) {
            System.out.println("A new book saved succesfully, book ID =" + bookEntity.getId());
        }
    }

    private static void readBook() {
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
        System.out.println("Found " + bookList.size() + "book(s) in the table book");
        System.out.println("They are: ");
        for (BookEntity book: bookList) {
            System.out.println(book.toString());
        }
    }

    private static void readBook(int bookID) {
        Optional<BookEntity> bookEntity = bookRepository.findById(bookID);
        if (bookEntity != null) {
            System.out.println("Found a book with book ID = " + bookID);
            System.out.println(bookEntity.toString());
        }
        else {
            System.out.println("Not found any book with book ID = " + bookID);
        }
    }

    private static void updateBook() {
        Optional<BookEntity> bookEntity = bookRepository.findById(1);
        BookEntity bookEntity1 = bookEntity.get();
        System.out.println("Book data before updating");
        System.out.println(bookEntity1.toString());

        bookEntity1.setAuthor("Jame");
        bookEntity1.setNumberOfPage(199);
        bookEntity1.setPrice(201);
        bookRepository.save(bookEntity1);

        System.out.println("Book data after updating");
        System.out.println(bookEntity1.toString());
    }

    private static void deleteBook() {
        bookRepository.deleteById(1);

        Optional<BookEntity> bookEntity = bookRepository.findById(1);
        BookEntity bookEntity1 = bookEntity.get();
        if (bookEntity != null) {
            bookRepository.delete(bookEntity1);
        }

        bookRepository.deleteAll();
    }
}
