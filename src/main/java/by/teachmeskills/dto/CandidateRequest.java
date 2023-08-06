package by.teachmeskills.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String resume;
}
