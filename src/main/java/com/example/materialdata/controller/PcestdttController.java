package com.example.materialdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.materialdata.dto.PcestdttDTO;
import com.example.materialdata.entity.Pcestdtt;
import com.example.materialdata.service.PcestdttService;

@RestController
@RequestMapping("/api")
public class PcestdttController {
	
	@Autowired
	public PcestdttService service;
	
	@PostMapping("/pcestdtt")
	public Pcestdtt createEntity(@RequestBody PcestdttDTO pcestdttDTO) {
		
		try {
			return service.saveEntity(pcestdttDTO);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save entity", e);
		}
		
	}
	
	@GetMapping("/pcestdtt")
	public List<Pcestdtt> getAllEntities(){
		return service.getAllEntities();
	}

}
