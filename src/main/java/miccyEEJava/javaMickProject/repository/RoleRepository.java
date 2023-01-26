package miccyEEJava.javaMickProject.repository;

import miccyEEJava.javaMickProject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(String role);
}