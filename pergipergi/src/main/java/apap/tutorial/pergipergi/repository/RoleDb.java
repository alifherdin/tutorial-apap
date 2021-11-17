package apap.tutorial.pergipergi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tutorial.pergipergi.model.RoleModel;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
}
