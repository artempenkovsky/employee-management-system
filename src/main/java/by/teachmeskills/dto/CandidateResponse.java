package by.teachmeskills.dto;

import by.teachmeskills.model.UserRole;
import lombok.Data;

import java.util.Set;
@Data
public class CandidateResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String resume;
    private Set<UserRole> roles;

}
