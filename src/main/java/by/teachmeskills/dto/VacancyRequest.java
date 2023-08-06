package by.teachmeskills.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacancyRequest {
    private String title;
    private String description;
    private Long employerId;
}
