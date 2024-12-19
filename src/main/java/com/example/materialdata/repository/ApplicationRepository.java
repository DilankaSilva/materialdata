package com.example.materialdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.materialdata.entity.Application;
import com.example.materialdata.entity.ApplicationPK;

public interface ApplicationRepository extends JpaRepository<Application,ApplicationPK>{
	
		 @Query(" SELECT a FROM Application a WHERE a.applicationNo=:applicationNo ")
		 Application findAllWithApplicant(@Param("applicationNo")String applicationNo);
	 
		 
		  

}
