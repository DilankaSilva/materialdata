package com.example.materialdata.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.materialdata.dto.PcesthttDTO;
import com.example.materialdata.entity.Pcesthtt;
import com.example.materialdata.entity.PcesthttPK;
import com.example.materialdata.service.PcesthttService;

@RestController
@RequestMapping("/api")
public class PcesthttController {
	
	@Autowired
	private PcesthttService service;
	
	@PostMapping("/pcesthtt")
	public Pcesthtt createEntity(@RequestBody PcesthttDTO pcesthttDTO) {
		return service.saveEntity(pcesthttDTO);
	}
	
	@GetMapping("//pcesthtt/{estimateNo}/{deptId}/{revNo}")
	public Pcesthtt getEntity(String estimateNo, String deptId, int revNo) {
		PcesthttPK id = new PcesthttPK();
		id.setEstimateNo(estimateNo);
		id.setDeptId(deptId);
		id.setRevNo(revNo);
		
		
		
		return service.getEntity(id);	
	}

}
