package br.edu.iff.projectLibrary.repository;

import br.edu.iff.projectLibrary.model.Alert;
import br.edu.iff.projectLibrary.model.Book;
import br.edu.iff.projectLibrary.model.LibMember;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>
{
    @Query("SELECT DISTINCT(a) FROM Alert a JOIN a.book b WHERE b.Id = :bookId")
    public List<Alert> findByAlertByBook(@Param("bookId") Long bookId);
    
    public List<Alert> findByLibMemberId(Long libMemberId, Pageable page);
    
    public List<Alert> findByLibMemberIdAndBookId(Long libMemberId, Long bookId, Pageable page);
    
    @Query("SELECT DISTINCT(a) FROM Alert a WHERE (a.issueDate BETWEEN :issueDate AND :returnDate) OR (a.returnDate BETWEEN :issueDate AND :returnDate)")
    public List<Alert> findAlertBetweenDates(Calendar issueDate, Calendar returnDate);
    
    @Query("SELECT DISTINCT(a) FROM Alert a WHERE ((a.issueDate BETWEEN :issueDate AND :returnDate) OR (a.returnDate BETWEEN :issueDate AND :returnDate)) AND a.book = :book")
    public List<Alert> findAlertBetweenDatesBookSpecific(Calendar issueDate, Calendar returnDate, Book book);
    
    @Query("SELECT DISTINCT(a) FROM Alert a WHERE ((a.issueDate BETWEEN :issueDate AND :returnDate) OR (a.returnDate BETWEEN :issueDate AND :returnDate)) AND a.libMember = :libMember")
    public List<Alert> findAlertBetweenDatesLibMemberSpecific(Calendar issueDate, Calendar returnDate, LibMember libMember);
}
