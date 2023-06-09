package com.edusoft.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edusoft.entity.Proyecto;
import com.edusoft.models.service.IProyectoService;

@RestController
public class HighChartController {

    @Autowired
    private IProyectoService proyectoService;

    @GetMapping("/get-data")
    public ResponseEntity<?> getPieChart() {
        List<Proyecto> dataList = proyectoService.nombreDeTaller();

        Map<String, Double> graphData = new TreeMap<>();
        for (Proyecto data : dataList) {

            graphData.put(data.getNombreTaller(), data.getPrecio());

        }

        return new ResponseEntity<>(graphData, HttpStatus.OK);
    }

    @GetMapping("/get-data2")
    public ResponseEntity<?> getPieChart2() {

        List<Proyecto> dataList = proyectoService.nombreDeProyecto();

        Map<String, Double> graphData = new TreeMap<>();
        for (Proyecto data : dataList) {

            graphData.put(data.getNombre(), data.getPrecio());

        }
        return new ResponseEntity<>(graphData, HttpStatus.OK);
    }

}
