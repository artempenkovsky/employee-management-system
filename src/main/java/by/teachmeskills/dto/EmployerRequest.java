package by.teachmeskills.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerRequest {
    private String email;
    private String name;
    private String website;
    private String username;
    private String password;
}

