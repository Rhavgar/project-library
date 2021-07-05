package br.edu.iff.projectLibrary.service;

import br.edu.iff.projectLibrary.model.Book;
import br.edu.iff.projectLibrary.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    @Autowired
    private BookRepository repo;
    
    public List<Book> findAll(int page, int size)
    {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Book> findAll()
    {
        return repo.findAll();
    }
    
    public Book findById(Long id)
    {
        Optional<Book> result = repo.findById(id);
        if(result.isEmpty())
        {
            throw new RuntimeException("Livro não encontrado.");
        }
        return result.get();
    }
    
    public Book save(Book b)
    {
        try
        {
        return repo.save(b);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao salvar o livro.");
        }
    }
    
}
