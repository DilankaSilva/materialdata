package com.example.materialdata.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.materialdata.dto.ApplicationMaterialDto;
import com.example.materialdata.dto.AvailableLabourDTO;
import com.example.materialdata.dto.AvailableMaterial;
import com.example.materialdata.dto.MaterialDTO;
import com.example.materialdata.entity.Applicant;
import com.example.materialdata.entity.Application;
import com.example.materialdata.entity.Inmatm;
import com.example.materialdata.repository.ApplicationRepository;
import com.example.materialdata.repository.AvailablelabourRepository;
import com.example.materialdata.repository.InwrhmtmRepository;
import com.example.materialdata.service.MaterialService;

@RestController
@RequestMapping("/api")
public class MaterialController {
	
	private final MaterialService inmatmService;
	private final InwrhmtmRepository inwrhmtmRepository;
	private final ApplicationRepository applicationRepository;
	private final AvailablelabourRepository availablelabourRepository; 
    @Autowired
    public MaterialController(MaterialService inmatmService, InwrhmtmRepository inwrhmtmRepository,ApplicationRepository applicationRepository , AvailablelabourRepository availablelabourRepository) {
        this.inmatmService = inmatmService;
		this.inwrhmtmRepository = inwrhmtmRepository;
		this.applicationRepository = applicationRepository;
		this.availablelabourRepository = availablelabourRepository;
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
    
    @GetMapping("/available")
    public List <AvailableMaterial> findavailableMaterial (@RequestParam String deptId){
    	return inwrhmtmRepository.findavailableMaterial(deptId);
    }
    
    @GetMapping(value ="/getAvailableDetails", produces = "application/json")
    public List<AvailableMaterial> getAvailableMaterials(

            @RequestParam String deptId,
            @RequestParam long connectionType,
            @RequestParam String wiringType,
            @RequestParam long phase) {
    	
    	List<MaterialDTO> defaultMaterials =  inmatmService.getMaterials(deptId, connectionType, wiringType, phase);
    	
        List<AvailableMaterial> availableMaterials = inwrhmtmRepository.findavailableMaterial(deptId);
        
        // Create a set of unique identifiers for default materials (e.g., IDs or names)
        Set<String> defaultMaterialNames = defaultMaterials.stream()
                .map(MaterialDTO::getMaterialName) // Replace 'getName' with the actual method to get the identifier
                .collect(Collectors.toSet());

        // Filter out the default materials from the available materials
        return availableMaterials.stream()
                .filter(material -> !defaultMaterialNames.contains(material.getMaterialName())) // Replace 'getName' with appropriate method
                .collect(Collectors.toList());

    }
    
    @GetMapping("/by-applicationNo")
    public ApplicationMaterialDto getApplication(@RequestParam String applicationNo) {
    	ApplicationMaterialDto applicationDTO = new ApplicationMaterialDto();
    	
   	try {
   		if(applicationNo==null ||applicationNo.trim().isEmpty()) {
   	 throw new IllegalArgumentException("Application number cannot be empty");
   		}
   		if(applicationNo.length()>50) {
   			throw new IllegalArgumentException("Application number cannot exceed 10");
   		}
   	 Application application=  applicationRepository.findAllWithApplicant(applicationNo);
         if(application==null) {
       	  throw new IllegalArgumentException("No applications found");
         }
         Applicant applicant= application.getApplicant();
   
         applicationDTO.setDeptId(applicant.getDeptId());
         applicationDTO.setConnectionType(60);
         applicationDTO.setPhase(3);
         applicationDTO.setWiringType("OH");


   	
   	}catch (IllegalArgumentException e) {
   		e.printStackTrace();
   		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
   				"An unexpected error occured while processing the application",e);
   	}
         	          return applicationDTO;
   	
   }
    
 
    		@GetMapping("/labours")
    		public List<AvailableLabourDTO>getAvailablelab(@RequestParam String deptId){
    			List<AvailableLabourDTO> availableLabourDTO=availablelabourRepository.findAvailableLabour(deptId);
    			return availableLabourDTO;
    		}
    	     

}
