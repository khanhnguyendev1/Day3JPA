package com.ivt.spring.jpa.demo1;

import com.ivt.spring.jpa.demo1.config.SpringConfig;
import com.ivt.spring.jpa.demo1.entity.BookEntity;
import com.ivt.spring.jpa.demo1.repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = (BookRepository) applicationContext.getBean("bookRepository");

    public static void main(String[] args) {
        createNewBook();
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
}
