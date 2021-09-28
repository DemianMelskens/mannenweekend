package nl.demian.mannenweekend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @Min(0)
    private Long credits;

    @NotBlank
    private String password;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public void addCredits(final Long amount) {
        if (amount > 0) {
            this.credits += amount;
        }
    }

    public void removeCredits(final Long amount) {
        if (amount > 0) {
            this.credits -= amount;
        }
    }
}

