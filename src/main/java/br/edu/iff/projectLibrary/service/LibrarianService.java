package br.edu.iff.projectLibrary.service;

import br.edu.iff.projectLibrary.model.Librarian;
import br.edu.iff.projectLibrary.model.Person;
import br.edu.iff.projectLibrary.repository.LibrarianRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService
{
    @Autowired
    private LibrarianRepository repo;
    
    public List<Librarian> findAll(int page, int size)
    {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Librarian> findAll()
    {
        return repo.findAll();
    }
    
    public Librarian findById(Long id)
    {
        Optional<Librarian> result = repo.findById(id);
        if(result.isEmpty())
        {
            throw new RuntimeException("Funcionario não encontrado.");
        }
        return result.get();
    }
    
    public Librarian save(Librarian l)
    {
        verifyCpfOrEmail(l.getCpf(), l.getEmail());
        
        try
        {
            return repo.save(l);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao salvar o funcionario.");
        }
    }
    
    public Librarian update(Librarian l, String currentPwd, String newPwd, String confirmPwd)
    {
        Librarian obj = findById(l.getId());
        alterPwd(obj, currentPwd, newPwd, confirmPwd);
        
        try
        {
        l.setCpf(obj.getCpf());
        return repo.save(l);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao atualizar o funcionario.");
        }
    }
    
    public void delete(Long id)
    {
        Librarian obj = findById(id);
        
        try
        {
            repo.delete(obj);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao excluir o funcionario.");
        }
    }
    
    private void verifyCpfOrEmail(String cpf, String email)
    {
        List<Person> result = repo.findByCpfOrEmail(cpf, email);
        if(!result.isEmpty())
        {
            throw new RuntimeException("CPF ou Email já cadastrado.");
        }
    }
    
    private void alterPwd(Librarian obj, String currentPwd, String newPwd, String confirmPwd)
    {
        if(!currentPwd.isBlank() && !newPwd.isBlank() && !confirmPwd.isBlank())
        {
            if(!currentPwd.equals(obj.getPassword()))
            {
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if(!newPwd.equals(confirmPwd))
            {
                throw new RuntimeException("Nova senha e Confirmar senha não conferem.");
            }
            obj.setPassword(newPwd);
        }
    }
}
