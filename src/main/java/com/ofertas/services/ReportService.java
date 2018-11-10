package com.ofertas.services;


import com.ofertas.entities.ReportEntity;
import com.ofertas.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportService")
public class ReportService {

	@Autowired
	@Qualifier("reportRepository")
	private ReportRepository reportRepository;


	public ResponseEntity<Object> getReporte1 () {
		try {
			List<ReportEntity> result = reportRepository.getReport1();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED.INTERNAL_SERVER_ERROR);
		}
	}

	
}
