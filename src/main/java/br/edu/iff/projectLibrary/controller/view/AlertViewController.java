package br.edu.iff.projectLibrary.controller.view;

import br.edu.iff.projectLibrary.model.Alert;
import br.edu.iff.projectLibrary.service.AlertService;
import br.edu.iff.projectLibrary.service.BookService;
import br.edu.iff.projectLibrary.service.LibMemberService;
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
@RequestMapping(path = "/alerts")
public class AlertViewController
{
    @Autowired
    private AlertService aservice;
    @Autowired
    private LibMemberService lmservice;
    @Autowired
    private BookService bservice;
    
    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("alerts", aservice.findAll());
        return "alerts";
    }
    
    @GetMapping(path = "/alert")
    public String create(Model model)
    {
        model.addAttribute("alert", new Alert());
        model.addAttribute("libmembers", lmservice.findAll());
        model.addAttribute("books", bservice.findAll());
        return "formAlert";
    }
    
    @PostMapping(path = "/alert")
    public String save(@Valid @ModelAttribute Alert alert, BindingResult result, Model model)
    {
        model.addAttribute("alert", new Alert());
        model.addAttribute("libmembers", lmservice.findAll());
        model.addAttribute("books", bservice.findAll());
        
        if(result.hasErrors())
        {
            model.addAttribute("errorMsg", result.getAllErrors());
            return "formAlert";
        }
        
        alert.setId(null);
        try
        {
            aservice.save(alert);
            model.addAttribute("successMsg", "Alerta registrado com sucesso.");
            model.addAttribute("alert", new Alert());
            return "formAlert";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMsg", new ObjectError("alert", e.getMessage()));
            return "formAlert";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String delete(@PathVariable("id") Long id)
    {
        aservice.delete(id);
        return "redirect:/alerts";
    }
}
