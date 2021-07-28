package br.edu.iff.projectLibrary.repository;

import br.edu.iff.projectLibrary.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>
{
   public Person findByEmail(String email);
}
