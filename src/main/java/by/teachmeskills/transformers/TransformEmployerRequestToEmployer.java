package by.teachmeskills.transformers;

import by.teachmeskills.dto.EmployerRequest;
import by.teachmeskills.model.Employer;
import org.springframework.stereotype.Component;

@Component
public class TransformEmployerRequestToEmployer {
    public Employer transform(EmployerRequest employerRequest){
        Employer employer = new Employer();
        employer.setEmail(employerRequest.getEmail());
        employer.setName(employerRequest.getName());
        employer.setWebsite(employerRequest.getWebsite());
        return employer;
    }
}
