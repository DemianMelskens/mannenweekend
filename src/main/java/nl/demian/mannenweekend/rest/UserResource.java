package nl.demian.mannenweekend.rest;

import lombok.RequiredArgsConstructor;
import nl.demian.mannenweekend.rest.dtos.UserDto;
import nl.demian.mannenweekend.rest.mappers.UserMapper;
import nl.demian.mannenweekend.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserResource {

    private final UserMapper userMapper;
    private final UserService userService;


    @GetMapping
    public List<UserDto> getAll() {
        return this.userMapper.toDtos(this.userService.getAll());
    }

    @GetMapping(path = "/current")
    public UserDto getCurrentUser() {
        return this.userMapper.toDto(this.userService.getCurrentUser());
    }
}
