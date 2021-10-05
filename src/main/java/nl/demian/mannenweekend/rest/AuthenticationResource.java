package nl.demian.mannenweekend.rest;

import lombok.RequiredArgsConstructor;
import nl.demian.mannenweekend.rest.dtos.JwtDto;
import nl.demian.mannenweekend.rest.dtos.LoginDto;
import nl.demian.mannenweekend.rest.dtos.RegisterDto;
import nl.demian.mannenweekend.rest.mappers.UserMapper;
import nl.demian.mannenweekend.security.jwt.JwtFilter;
import nl.demian.mannenweekend.security.jwt.TokenProvider;
import nl.demian.mannenweekend.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginDto loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginDTO.isRememberMe());

        final HttpHeaders headers = new HttpHeaders();
        headers.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtDto(jwt), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public void register(@Valid @RequestBody RegisterDto registerDTO) {
        this.userService.register(this.userMapper.toEntity(registerDTO));
    }
}
