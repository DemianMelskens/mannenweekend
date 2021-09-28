package nl.demian.mannenweekend.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private boolean rememberMe;
}
