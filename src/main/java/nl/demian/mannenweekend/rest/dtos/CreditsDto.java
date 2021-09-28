package nl.demian.mannenweekend.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreditsDto {

    @Min(0L)
    @NotNull
    Long amount;

    @NotBlank
    String username;
}
