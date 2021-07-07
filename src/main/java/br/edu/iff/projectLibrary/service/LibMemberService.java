package br.edu.iff.projectLibrary.service;

import br.edu.iff.projectLibrary.model.LibMember;
import br.edu.iff.projectLibrary.model.Person;
import br.edu.iff.projectLibrary.repository.LibMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibMemberService
{
    @Autowired
    private LibMemberRepository repo;
    
    public List<LibMember> findAll(int page, int size)
    {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<LibMember> findAll()
    {
        return repo.findAll();
    }
    
    public LibMember findById(Long id)
    {
        Optional<LibMember> result = repo.findById(id);
        if(result.isEmpty())
        {
            throw new RuntimeException("Membro não encontrado.");
        }
        return result.get();
    }
    
    public LibMember save(LibMember lm)
    {
        verifyCpfOrEmail(lm.getCpf(), lm.getEmail());
        
        try
        {
            return repo.save(lm);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao salvar o membro.");
        }
    }
    
    public LibMember update(LibMember lm, String currentPwd, String newPwd, String confirmPwd)
    {
        LibMember obj = findById(lm.getId());
        alterPwd(obj, currentPwd, newPwd, confirmPwd);
        
        try
        {
        lm.setCpf(obj.getCpf());
        lm.setBirthday(obj.getBirthday());
        lm.setPassword(obj.getPassword());
        return repo.save(lm);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao atualizar o membro.");
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
    
    private void alterPwd(LibMember obj, String currentPwd, String newPwd, String confirmPwd)
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
