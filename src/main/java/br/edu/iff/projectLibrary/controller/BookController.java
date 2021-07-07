package br.edu.iff.projectLibrary.controller;

import br.edu.iff.projectLibrary.model.Book;
import br.edu.iff.projectLibrary.service.BookService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/books")
public class BookController
{
    @Autowired
    private BookService service;
    
    @GetMapping
    public ResponseEntity getAll
        (@RequestParam(name = "page", defaultValue = "0", required = false) int page,
         @RequestParam(name = "size", defaultValue = "10", required = false) int size)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Book book)
    {
        book.setId(null);
        service.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Book book)
    {
        book.setId(id);
        service.update(book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
