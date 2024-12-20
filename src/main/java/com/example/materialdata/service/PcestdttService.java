package com.example.materialdata.service;

import java.util.List;

import com.example.materialdata.dto.PcestdttDTO;
import com.example.materialdata.entity.Pcestdtt;
import com.example.materialdata.entity.PcestdttPK;

public interface PcestdttService {
	
	Pcestdtt saveEntity(PcestdttDTO pcestdttDTO) throws Exception;
	List<Pcestdtt> getAllEntities();
	Pcestdtt getEntity(PcestdttPK id) throws Exception;

}
