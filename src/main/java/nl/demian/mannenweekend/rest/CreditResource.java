package nl.demian.mannenweekend.rest;

import lombok.RequiredArgsConstructor;
import nl.demian.mannenweekend.rest.dtos.CreditsDto;
import nl.demian.mannenweekend.services.CreditService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/credits")
public class CreditResource {

    final CreditService creditService;

    @PostMapping(path = "/add")
    public void addCredits(@Valid @RequestBody CreditsDto dto) {
        this.creditService.addCredits(dto.getAmount(), dto.getUsername());
    }

    @PostMapping(path = "/remove")
    public void removeCredits(@Valid @RequestBody CreditsDto dto) {
        this.creditService.removeCredits(dto.getAmount(), dto.getUsername());
    }
}
