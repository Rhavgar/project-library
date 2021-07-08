package br.edu.iff.projectLibrary.service;

import br.edu.iff.projectLibrary.exception.NotFoundException;
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
            throw new NotFoundException("Livro não encontrado.");
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
    
    public Book update(Book b)
    {
        Book obj = findById(b.getId());
        
        try
        {
        b.setBookName(b.getBookName());
        b.setBookAuthor(b.getBookAuthor());
        b.setLang(b.getLang());
        return repo.save(b);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao atualizar o livro.");
        }
    }
    
    public void delete(Long id)
    {
        int x = repo.countAlertByBookId(id);
        
        if(x >= 0)
        {
            throw new RuntimeException("Há exemplares emprestados.");
        }
        try
        {
            repo.deleteById(id);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao deletar o livro.");
        }
    }
    
}
