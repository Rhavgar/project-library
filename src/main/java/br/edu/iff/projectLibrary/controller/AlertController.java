package br.edu.iff.projectLibrary.controller;

import br.edu.iff.projectLibrary.model.Alert;
import br.edu.iff.projectLibrary.service.AlertService;
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
@RequestMapping("/apirest/alerts")
public class AlertController
{
    @Autowired
    private AlertService service;
    
    @GetMapping
    public ResponseEntity getAll
        (@RequestParam(name = "page", defaultValue = "0", required = false) int page,
         @RequestParam(name = "page", defaultValue = "10", required = false) int size,
         @RequestParam(name = "libMemberId", defaultValue = "0", required = false) Long libMemberId,
         @RequestParam(name = "bookId", defaultValue = "0", required = false) Long bookId)
    {
        return ResponseEntity.ok(service.findAll(page, size, libMemberId, bookId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(service.findById(id));
    }
    
    /*@PostMapping
    public ResponseEntity save(@RequestBody Alert alert)
    {
        alert.setId(null);
        service.save(alert);
        return ResponseEntity.status(HttpStatus.CREATED).body(alert);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Alert alert)
    {
        alert.setId(id);
        service.update(alert);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        service.delete(id);
        return ResponseEntity.ok().build();
    }*/
    
}
