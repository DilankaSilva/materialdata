package com.example.materialdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.materialdata.dto.AvailableLabourDTO;
import com.example.materialdata.entity.Splabrat;
import com.example.materialdata.entity.SplabratPK;
@Repository
public interface AvailablelabourRepository extends JpaRepository<Splabrat,SplabratPK>{
	
	@Query("SELECT new com.example.materialdata.dto.AvailableLabourDTO(a.id.labourCode, a.labourName, a.unitPrice, a.labourHours) "
	        + "FROM Splabrat a "
	        + "WHERE a.id.deptId = :deptId "
	        + "AND a.id.year= '2013'")
	List<AvailableLabourDTO> findAvailableLabour(@Param("deptId") String deptId);

}
