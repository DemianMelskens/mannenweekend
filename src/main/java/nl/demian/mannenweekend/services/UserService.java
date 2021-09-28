package nl.demian.mannenweekend.services;

import lombok.RequiredArgsConstructor;
import nl.demian.mannenweekend.domain.User;
import nl.demian.mannenweekend.exceptions.UsernameAlreadyInUseException;
import nl.demian.mannenweekend.repositories.UserRepository;
import nl.demian.mannenweekend.security.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityContext securityContext;

    @Transactional
    public void register(final User user) {
        if (this.userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyInUseException(String.format("Username: %s already in use!", user.getUsername()));
        }

        this.userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getByUsername(final String username) {
        return this.userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        final Long userId = securityContext.getCurrentUserId().orElseThrow(EntityNotFoundException::new);
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
