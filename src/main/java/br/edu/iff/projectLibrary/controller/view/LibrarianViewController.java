package br.edu.iff.projectLibrary.controller.view;

import br.edu.iff.projectLibrary.model.Librarian;
import br.edu.iff.projectLibrary.service.LibrarianService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/librarians")
public class LibrarianViewController
{
    @Autowired
    private LibrarianService service;
    
    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("librarians", service.findAll());
        return "librarians";
    }
    
    @GetMapping(path = "/librarian")
    public String create(Model model)
    {
        model.addAttribute("librarian", new Librarian());
        return "formLibrarian";
    }
    
    @PostMapping(path = "/librarian")
    public String save(@Valid @ModelAttribute Librarian librarian, BindingResult result, @RequestParam("confirmPassword") String confirmPassword, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("errorMsg", result.getAllErrors());
            return "formLibrarian";
        }
        
        if(!librarian.getPassword().equals(confirmPassword))
        {
            model.addAttribute("errorMsg", new ObjectError("librarian", "Campo da Senha e Confirmar Senha devem ser iguais."));
            return "formLibrarian";
        }
        
        librarian.setId(null);
        
        try
        {
            service.save(librarian);
            model.addAttribute("successMsg", "Bibliotacário cadastrado com sucesso.");
            model.addAttribute("librarian", new Librarian());
            return "formLibrarian";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMsg", new ObjectError("librarian", e.getMessage()));
            return "formLibrarian";
        }
    }
    
    @GetMapping(path = "/librarian/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("librarian", service.findById(id));
        return "formLibrarian";
    }
    
    @PostMapping(path = "/librarian/{id}")
    public String update(@Valid @ModelAttribute Librarian librarian, BindingResult result,@PathVariable("id") Long id, Model model)
    {
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors())
        {
            if(!fe.getField().equals("password"))
            {
                list.add(fe);
            }
        }
        if(!list.isEmpty())
        {
            model.addAttribute("errorMsg", list);
            return "formLibrarian";
        }
        
        librarian.setId(id);
        
        try
        {
            service.update(librarian, "", "", "");
            model.addAttribute("successMsg", "Bibliotecário atualizado com sucesso.");
            model.addAttribute("librarian", librarian);
            return "formLibrarian";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMsg", new ObjectError("librarian", e.getMessage()));
            return "formLibrarian";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String delete(@PathVariable("id") Long id)
    {
        service.delete(id);
        return "redirect:/librarians";
    }
}
