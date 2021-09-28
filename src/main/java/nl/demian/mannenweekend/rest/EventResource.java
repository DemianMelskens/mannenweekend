package nl.demian.mannenweekend.rest;

import lombok.RequiredArgsConstructor;
import nl.demian.mannenweekend.services.EventService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventResource {

    private final EventService eventService;

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe() {
        return eventService.subscribe();
    }


}
