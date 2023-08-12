package by.teachmeskills.dto;

import by.teachmeskills.model.UserRole;
import lombok.Data;

import java.util.Set;
@Data
public class EmployerResponse {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String website;
    private Set<UserRole> roles;
}
