package br.edu.iff.projectLibrary.repository;

import br.edu.iff.projectLibrary.model.Librarian;
import br.edu.iff.projectLibrary.model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long>
{
    @Query("SELECT p FROM Person p WHERE p.cpf = :cpf OR p.email = :email")
    public List<Person> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);
    
    public Librarian findByEmail(String email);
}
