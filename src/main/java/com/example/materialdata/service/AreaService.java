package com.example.materialdata.service;

import com.example.materialdata.dto.AreaDto;
import com.example.materialdata.dto.DepotDto;
import com.example.materialdata.repository.GldeptinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    private final GldeptinRepository gldeptinRepository;

    public AreaService(GldeptinRepository gldeptinRepository) {
        this.gldeptinRepository = gldeptinRepository;
    }

    public List<AreaDto> getAllAreaDepartmentsAsDto() {
        return gldeptinRepository.findAreaDepartmentsAsDto();
    }

    public List<DepotDto> getDepotDepartmentsByAreaCode(String deptId) {
        return gldeptinRepository.findDepotDepartments(deptId);
    }

}
