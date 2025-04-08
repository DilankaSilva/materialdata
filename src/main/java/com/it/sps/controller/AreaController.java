package com.it.sps.controller;

import com.it.sps.dto.AreaDto;
import com.it.sps.dto.DepotDto;
import com.it.sps.service.AreaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cscNo")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/areas")
    public List<AreaDto> getAreaDepartmentsAsDto() {
        return areaService.getAllAreaDepartmentsAsDto();
    }

//    @GetMapping("/depots")
//    public List<DepotDto> getDepotDepartments() {
//        return areaService.getDepotDepartmentsByAreaCode();
//    }

    @GetMapping("/depots")
    public List<DepotDto> getDepotDepartmentsByAreaCode(@RequestParam String deptId) {
        return areaService.getDepotDepartmentsByAreaCode(deptId);
    }


}