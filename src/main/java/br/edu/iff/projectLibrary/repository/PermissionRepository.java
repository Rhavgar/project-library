package br.edu.iff.projectLibrary.repository;

import br.edu.iff.projectLibrary.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>
{
    
}
