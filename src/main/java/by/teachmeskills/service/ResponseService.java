package by.teachmeskills.service;

import by.teachmeskills.dto.ResponseRequest;
import by.teachmeskills.model.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResponseService {
    Response createResponse(ResponseRequest request);
    List<Response> getResponseByVacancy(Long vacancyId);

    List<Response> getResponseByEmployer(Long employerId);

    Page<Response> getResponseByEmployerWithPage(Long employerId, Pageable pageable);
}
