package by.teachmeskills.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacancyResponseRequest {
    private Long candidateId;
    private Long vacancyId;
    private String coverLetter;
}
