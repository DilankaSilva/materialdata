package com.example.materialdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.materialdata.dto.MaterialDTO;
import com.example.materialdata.entity.Inmatm;
import com.example.materialdata.service.MaterialService;

@RestController
@RequestMapping("/api")
public class MaterialController {
	
	private final MaterialService inmatmService;

    @Autowired
    public MaterialController(MaterialService inmatmService) {
        this.inmatmService = inmatmService;
    }

    @GetMapping("/materials")
    public List<Inmatm> getMaterialsByName(@RequestParam String name) {
        return inmatmService.getMaterialsByName(name);
    }
    
    @GetMapping(value ="/getDetails", produces = "application/json")
    public List<MaterialDTO> getMaterials(

            @RequestParam String deptId,
            @RequestParam long connectionType,
            @RequestParam String wiringType,
            @RequestParam long phase) {
        return inmatmService.getMaterials(deptId, connectionType, wiringType, phase);

    }

}
