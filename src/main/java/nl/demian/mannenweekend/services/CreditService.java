package nl.demian.mannenweekend.services;

import lombok.RequiredArgsConstructor;
import nl.demian.mannenweekend.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    final UserService userService;
    final EventService eventService;

    public void addCredits(final Long amount, final String username) {
        final User user = userService.getByUsername(username);
        user.addCredits(amount);
        eventService.sendEventTo(user, "receivedCredits");
    }

    public void removeCredits(final Long amount, final String username) {
        final User user = userService.getByUsername(username);
        user.removeCredits(amount);
        eventService.sendEventTo(user, "payedCredits");
    }
}
