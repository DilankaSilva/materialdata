package com.example.materialdata.service;

import java.util.List;

import com.example.materialdata.dto.PcestdttDTO;
import com.example.materialdata.dto.SpestlabDTO;
import com.example.materialdata.entity.Pcestdtt;
import com.example.materialdata.entity.Spestlab;

public interface SpestlabService {
	
	void saveEntities(List<PcestdttDTO> pcestdttDTOs, List<SpestlabDTO> spestlabDTOs) throws Exception;
	List<Pcestdtt> getAllEntities();
	List<Spestlab> getAllLabourEntities();

}
