package br.edu.iff.projectLibrary.controller.view;

import br.edu.iff.projectLibrary.model.LibMember;
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
@RequestMapping(path = "/libmembers")
public class LibMemberViewController
{
    @Autowired
    private LibMemberService service;
    
    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("libmembers", service.findAll());
        return "libmembers";
    }
    
    @GetMapping(path = "/libmember")
    public String create(Model model)
    {
        model.addAttribute("libmembers", new LibMember());
        return "formLibMember";
    }
    
    @PostMapping(path = "/libmember")
    public String save(@Valid @ModelAttribute LibMember libmember, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("errorMsg", result.getAllErrors());
            return "formLibMember";
        }
        
        libmember.setId(null);
        
        try
        {
            service.save(libmember);
            model.addAttribute("successMsg", "Membro cadastrado com sucesso.");
            model.addAttribute("libmember", new LibMember());
            return "formLibMember";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMsg", new ObjectError("libmember", e.getMessage()));
            return "formLibMember";
        }
    }
    
    @GetMapping(path = "/libmember/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("libmembers", service.findById(id));
        return "formLibMember";
    }
    
    @PostMapping(path = "/libmember/{id}")
    public String update(@Valid @ModelAttribute LibMember libmember, BindingResult result,@PathVariable("id") Long id, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("errorMsg", result.getAllErrors());
            return "formLibMember";
        }
        
        libmember.setId(id);
        
        try
        {
            service.update(libmember, null, null, null);
            model.addAttribute("successMsg", "Membro atualizado com sucesso.");
            model.addAttribute("libmember", libmember);
            return "formLibMember";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMsg", new ObjectError("libmember", e.getMessage()));
            return "formLibMember";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String delete(@PathVariable("id") Long id)
    {
        service.delete(id);
        return "redirect:/libmembers";
    }
}
