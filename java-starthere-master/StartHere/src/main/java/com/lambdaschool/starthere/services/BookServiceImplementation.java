package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImplementation implements BookService{


    @Autowired
    private BookRepository bookRepos;



    @Override
    public ArrayList<Book> findAll(Pageable pageable) {
        ArrayList<Book> list = new ArrayList<>();
        bookRepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }
}
