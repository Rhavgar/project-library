package br.edu.iff.projectLibrary.repository;

import br.edu.iff.projectLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
    @Query("SELECT COUNT (a) FROM Alert a WHERE a.book.id = :id")
    public int countAlertByBookId(@Param("id") Long id);
}
