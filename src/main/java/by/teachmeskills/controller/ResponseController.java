package by.teachmeskills.controller;

import by.teachmeskills.dto.ResponseRequest;
import by.teachmeskills.model.Response;
import by.teachmeskills.service.impl.ResponseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/response")
public class ResponseController {
    private final ResponseServiceImpl responseService;

    public ResponseController(ResponseServiceImpl responseService) {
        this.responseService = responseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createResponse(@RequestBody ResponseRequest request) {
        Response response = responseService.createResponse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
