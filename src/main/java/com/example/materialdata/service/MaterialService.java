package com.example.materialdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.materialdata.dto.MaterialDTO;
import com.example.materialdata.entity.Inmatm;
import com.example.materialdata.repository.InmatmRepository;
import com.example.materialdata.repository.SpestmtmRepository;

@Service
public class MaterialService {
	
	@Autowired
	private InmatmRepository inmatmRepository;
	
	@Autowired
    private SpestmtmRepository spestmtmRepository;


    public List<MaterialDTO> getMaterials(String deptId, long connectionType, String wiringType, long phase) {
        return spestmtmRepository.findMaterialsByCriteria(deptId, connectionType, wiringType, phase);

    }
	
	public MaterialService(InmatmRepository inmatmRepository) {
		this.inmatmRepository = inmatmRepository;
	}
	
	public List<Inmatm> getMaterialsByName(String name) {
        return inmatmRepository.findByMatNm(name);
        
    }
		

}
