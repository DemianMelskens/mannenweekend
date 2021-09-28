package nl.demian.mannenweekend.rest.mappers;

import nl.demian.mannenweekend.domain.User;
import nl.demian.mannenweekend.rest.dtos.RegisterDto;
import nl.demian.mannenweekend.rest.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private BCryptPasswordEncoder encoder;

    public abstract UserDto toDto(User user);

    public abstract List<UserDto> toDtos(List<User> user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "role", ignore = true)
    public abstract User toEntity(RegisterDto dto);

    @Named("encodePassword")
    String encodePassword(final String password) {
        return encoder.encode(password);
    }
}
