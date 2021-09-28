package nl.demian.mannenweekend.services;

import lombok.RequiredArgsConstructor;
import nl.demian.mannenweekend.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventService {

    private final UserService userService;

    private final Map<String, SseEmitter> subscriptions = new HashMap<>();

    public SseEmitter subscribe() {
        final SseEmitter sseEmitter = new SseEmitter(-1L);
        final User currentUser = this.userService.getCurrentUser();
        sendEvent(sseEmitter, "subscribed", null);
        subscriptions.put(currentUser.getUsername(), sseEmitter);

        sseEmitter.onCompletion(() -> subscriptions.remove(currentUser.getUsername()));
        sseEmitter.onTimeout(() -> subscriptions.remove(currentUser.getUsername()));
        sseEmitter.onError((ex) -> subscriptions.remove(currentUser.getUsername()));

        return sseEmitter;
    }

    public void broadcastEvent(final String name) {
        broadcastEvent(name, null);
    }

    public void broadcastEvent(final String name, final Object data) {
        subscriptions.values().forEach(sseEmitter -> sendEvent(sseEmitter, name, data));
    }

    public void sendEventTo(final User user, final String name) {
        sendEventTo(user, name, null);
    }

    public void sendEventTo(final User user, final String name, final Object data) {
        sendEvent(subscriptions.get(user.getUsername()), name, data);
    }

    private void sendEvent(final SseEmitter sseEmitter, final String name, final Object data) {
        try {
            if (data != null) {
                sseEmitter.send(SseEmitter.event().name(name).data(data));
            } else {
                sseEmitter.send(SseEmitter.event().name(name));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
