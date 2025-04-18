package com.it.sps.controller;

import com.it.sps.dto.FormDataDto;
import com.it.sps.service.ApplicationWiringLDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

//    private final ApplicationWiringLDService applicationService;
//
//    public ApplicationController(ApplicationWiringLDService applicationService) {
//        this.applicationService = applicationService;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> submitApplication(@RequestBody FormDataDto formData) {
//        try{
//            if(!formData.getApplicationDto().getDeptId().matches("^\\d{3}\\.\\d{2}$")){
//                throw new IllegalArgumentException("Invalid Cost center number format");
//            }
//            applicationService.saveFullApplication(formData);
//            return ResponseEntity.ok().build();
//
//        }catch(Exception e){
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }

    @Autowired
    private ApplicationWiringLDService applicationService;

    @PostMapping
    public ResponseEntity<?> submitApplication(@RequestBody FormDataDto formData) {
        try{
            if(!formData.getApplicationDto().getDeptId().matches("^\\d{3}\\.\\d{2}$")){
                throw new IllegalArgumentException("Invalid Cost center number format");
            }
            String applicationNo = applicationService.saveFullApplication(formData);
            return ResponseEntity.ok().body(
                    Map.of(
                            "status", "success",
                            "applicationNo", applicationNo,
                            "messsage", "Application Submitted Successfully"
                    )
            );

        }catch(Exception e){
            return ResponseEntity.badRequest().body(Map.of("status","error", "message", e.getMessage()));
        }
    }

}