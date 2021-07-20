package br.edu.iff.projectLibrary.controller.apirest;

import br.edu.iff.projectLibrary.model.Librarian;
import br.edu.iff.projectLibrary.service.LibrarianService;
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
@RequestMapping(path = "/apirest/librarians")
public class LibrarianController
{
    @Autowired
    private LibrarianService service;
    
    @GetMapping
    public ResponseEntity getAll
        (@RequestParam(name = "page", defaultValue = "0", required = false) int page,
         @RequestParam(name = "size", defaultValue = "10", required = false) int size)
    {
        return ResponseEntity.ok(service.findAll(page, size));
    }
        
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Librarian librarian)
    {
        librarian.setId(null);
        service.save(librarian);
        return ResponseEntity.status(HttpStatus.CREATED).body(librarian);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Librarian librarian)
    {
        librarian.setId(id);
        service.update(librarian, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/alterPass")
    public ResponseEntity alterPass
        (@PathVariable("id") Long id,
         @RequestParam(name = "currentPwd", defaultValue = "", required = true) String currentPwd,
         @RequestParam(name = "newPwd", defaultValue = "", required = true) String newPwd,
        @RequestParam(name = "confirmPwd", defaultValue = "", required = true) String confirmPwd)
    {
        Librarian l = service.findById(id);
        service.update(l, currentPwd, newPwd, confirmPwd);
        return ResponseEntity.ok().build();
    }
    
}
