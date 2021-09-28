package nl.demian.mannenweekend.repositories;

import nl.demian.mannenweekend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
