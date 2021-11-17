package apap.tutorial.pergipergi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tutorial.pergipergi.model.UserModel;

@Repository
public interface UserDb extends JpaRepository<UserModel, String> {
    UserModel findByUsername(String username);
}
