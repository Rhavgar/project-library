package br.edu.iff.projectLibrary.controller.view;

import br.edu.iff.projectLibrary.model.Book;
import br.edu.iff.projectLibrary.model.Language;
import br.edu.iff.projectLibrary.service.BookService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/books")
public class BookViewController
{
    @Autowired
    private BookService service;
    
    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("books", service.findAll());
        return "books";
    }
    
    @GetMapping(path = "/book")
    public String create(Model model)
    {
        model.addAttribute("book", new Book());
        model.addAttribute("lang", Language.values());
        return "formBook";
    }
    
    @PostMapping(path = "/book")
    public String save(@Valid @ModelAttribute Book book, BindingResult result, Model model)
    {
        model.addAttribute("lang", Language.values());
        
        if(result.hasErrors())
        {
            model.addAttribute("errorMsg", result.getAllErrors());
            return "formBook";
        }
        
        book.setId(null);
        
        try
        {
            service.save(book);
            model.addAttribute("successMsg", "Livro cadastrado com sucesso.");
            model.addAttribute("book", new Book());
            return "formBook";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMsg", new ObjectError("book", e.getMessage()));
            return "formBook";
        }
    }
    
    @GetMapping(path = "/book/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("book", service.findById(id));
        model.addAttribute("lang", Language.values());
        return "formBook";
    }
    
    @PostMapping(path = "/book/{id}")
    public String update(@Valid @ModelAttribute Book book, BindingResult result, @PathVariable("id") Long id, Model model)
    {
        model.addAttribute("lang", Language.values());
        
        if(result.hasErrors())
        {
            model.addAttribute("errorMsg", result.getAllErrors());
            return "formBook";
        }
        
        book.setId(id);
        
        try
        {
            service.update(book);
            model.addAttribute("successMsg", "Livro atualizado com sucesso.");
            model.addAttribute("book", book);
            return "formBook";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMsg", new ObjectError("book", e.getMessage()));
            return "formBook";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String delete(@PathVariable("id") Long id)
    {
        service.delete(id);
        return "redirect:/books";
    }
}
