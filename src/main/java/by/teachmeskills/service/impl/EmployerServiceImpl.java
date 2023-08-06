package by.teachmeskills.service.impl;

import by.teachmeskills.dto.EmployerRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.repository.EmployerRepository;
import by.teachmeskills.service.EmployerService;
import by.teachmeskills.transformers.TransformEmployerRequestToEmployer;
import org.springframework.stereotype.Service;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    private final TransformEmployerRequestToEmployer transformer;

    public EmployerServiceImpl(EmployerRepository employerRepository, TransformEmployerRequestToEmployer transformer) {
        this.employerRepository = employerRepository;
        this.transformer = transformer;
    }

    public Employer createEmployer(EmployerRequest employerRequest) {
        return employerRepository.save(transformer.transform(employerRequest));
    }
}
