package br.edu.iff.projectLibrary.service;

import br.edu.iff.projectLibrary.exception.NotFoundException;
import br.edu.iff.projectLibrary.model.Alert;
import br.edu.iff.projectLibrary.repository.AlertRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertService
{
    @Autowired
    private AlertRepository repo;
    
    public List<Alert> findAll()
    {
        return repo.findAll();
    }
    
    public List<Alert> findAll(int page, int size, Long libMemberId, Long bookId)
    {
        Pageable p = PageRequest.of(page, size);
        if(libMemberId!=0 && bookId!=0)
        {
            return repo.findByLibMemberIdAndBookId(libMemberId, bookId, p);
        }
        else if(libMemberId!=0)
        {
            return repo.findByLibMemberId(libMemberId, p);
        }
        else if(bookId!=0)
        {
            return repo.findByAlertByBook(bookId);
        }
        
        return repo.findAll(p).toList();
    }
    
    public Alert findById(Long id)
    {
        Optional<Alert> obj =  repo.findById(id);
        if(obj.isEmpty())
        {
            throw new NotFoundException("Alerta não encontrado.");
        }
        return obj.get();
    }
    
    public Alert save(Alert a)
    {
        verifyStartEndDate(a);
        verifyBookAmount(a);
        verifyLibMemberWithAlerts(a);
    
        try
        {
            return repo.save(a);
        }
        catch(Exception e)
        {
            Throwable t = e;
            while(t.getCause() != null)
            {
                if(t instanceof ConstraintViolationException)
                {
                  throw((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao salvar o alerta.");
        }
    }
    
    /*public Alert update(Alert a)
    {  
    }*/
    
    public void delete(Long id)
    {
        Alert obj = findById(id);
        
        try
        {
            repo.delete(obj);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Falha ao excluir o alerta.");
        }
    }
        
    private void verifyBookAmount(Alert a)
    {
        List<Alert> alerts = repo.findAlertBetweenDatesBookSpecific(a.getIssueDate(), a.getReturnDate(), a.getBook());
        
        if(alerts.size() >= a.getBook().getBookAmount())
        {
            throw new RuntimeException("Livros emprestados para o período chegou ao limite.");
        }
    }
    
    public void verifyLibMemberWithAlerts(Alert a)
    {
       List<Alert> alerts = repo.findAlertBetweenDatesLibMemberSpecific(a.getIssueDate(), a.getReturnDate(), a.getLibMember());
        
        if(alerts.size() >= 3)
        {
            throw new RuntimeException("Livros emprestados a este membro para o período chegou ao limite.");
        }
    }
    
    private void verifyStartEndDate(Alert a)
    {
        if(a.getIssueDate().compareTo(a.getReturnDate())>=0)
        {
            throw new RuntimeException("Data de Emissão deve ser anterior a Data de Retorno.");
        }
    }
}
