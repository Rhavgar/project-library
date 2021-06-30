package br.edu.iff.projectLibrary.repository;

import br.edu.iff.projectLibrary.model.Alert;
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
    @Query("SELECT DISTINCT(a) FROM Alert a JOIN a.book b WHERE b.bookId = :bookId")
    public List<Alert> findByAlertByBook(@Param("bookId") Long bookId);
    
    public List<Alert> findByLibMemberId(Long libMemberId, Pageable page);
    
    public List<Alert> findByLibMemberIdAndBookId(Long libMemberId, Long bookId, Pageable page);
    
    @Query("SELECT DISTINCT(a) FROM Alert a WHERE (a.start BETWEEN :start AND :end) OR (a.end BETWEEN :start AND :end)")
    public List<Alert> findAlertBetweenDates(Calendar start, Calendar end);
}
