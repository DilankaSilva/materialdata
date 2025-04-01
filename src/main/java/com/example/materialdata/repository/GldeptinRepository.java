package com.example.materialdata.repository;

import com.example.materialdata.dto.AreaDto;
import com.example.materialdata.dto.DepotDto;
import com.example.materialdata.entity.Gldeptin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GldeptinRepository extends JpaRepository<Gldeptin, String> {

    @Query("SELECT new com.example.materialdata.dto.AreaDto(d.deptId, d.deptArea) FROM Gldeptin d WHERE d.deptType = 'AREA' ORDER BY d.deptArea")
    List<AreaDto> findAreaDepartmentsAsDto();

    @Query("SELECT NEW com.example.materialdata.dto.DepotDto(d.deptId, d.deptFullName) " +
            "FROM Gldeptin d " +
            "WHERE d.deptType = 'DEPOT' " +
            "AND SUBSTRING(d.deptId, 1, 3) = SUBSTRING(:prefix,1,3) " +
            "ORDER BY d.deptFullName")
    List<DepotDto> findDepotDepartments(@Param("prefix") String deptId);


}
