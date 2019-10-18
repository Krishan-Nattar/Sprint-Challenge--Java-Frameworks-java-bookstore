package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.AuthorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    //    GET /authors/authors - returns a JSON object list of all the books, thier sections, and their authors.
    @ApiOperation(value="Return all authors using paging and sorting", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve(0..n)"),//these are all just text fields printed. They can be anything
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " +
                    "Default sort order is ascending. " +
                    "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0,size=3) Pageable pageable, HttpServletRequest request)
    {
        ArrayList<Author> myAuthors = authorService.findAll(pageable);
        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }

}
