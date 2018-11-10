package com.ofertas.controllers;

import com.ofertas.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Autowired
    @Qualifier("reportService")
    private ReportService reportService;


    @GetMapping("/getReport1")
    public ResponseEntity<Object> getReport1() {
        ResponseEntity<Object> responseEntity = reportService.getReporte1();

        if (responseEntity.hasBody()) {
            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
        }
        return responseEntity;
    }


}
