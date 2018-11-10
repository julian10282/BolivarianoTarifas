package com.ofertas.repository;

import com.ofertas.entities.ReportEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("reportRepository")
public class ReportRepository  {


    public List<ReportEntity> getReport1() {

        List<ReportEntity> result = new ArrayList<>();

        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setMes("Marzo");
        reportEntity.setNumOferta(38);

        result.add(reportEntity);

        ReportEntity reportEntity1 = new ReportEntity();
        reportEntity1.setMes("Abril");
        reportEntity1.setNumOferta(56);

        result.add(reportEntity1);

        ReportEntity reportEntity2 = new ReportEntity();
        reportEntity2.setMes("Mayo");
        reportEntity2.setNumOferta(94);

        result.add(reportEntity2);

        ReportEntity reportEntity3 = new ReportEntity();
        reportEntity3.setMes("Junio");
        reportEntity3.setNumOferta(72);

        result.add(reportEntity3);

        ReportEntity reportEntity4 = new ReportEntity();
        reportEntity4.setMes("Julio");
        reportEntity4.setNumOferta(68);

        result.add(reportEntity4);

        ReportEntity reportEntity5 = new ReportEntity();
        reportEntity5.setMes("Agosto");
        reportEntity5.setNumOferta(87);

        result.add(reportEntity5);

        ReportEntity reportEntity6 = new ReportEntity();
        reportEntity6.setMes("Septimbre");
        reportEntity6.setNumOferta(96);

        result.add(reportEntity6);

        ReportEntity reportEntity7 = new ReportEntity();
        reportEntity7.setMes("Octubre");
        reportEntity7.setNumOferta(27);

        result.add(reportEntity7);

        return result;

    }


}
