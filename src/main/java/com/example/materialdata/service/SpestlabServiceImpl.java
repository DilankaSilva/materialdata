package com.example.materialdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.materialdata.dto.PcestdttDTO;
import com.example.materialdata.dto.SpestlabDTO;
import com.example.materialdata.entity.Pcestdtt;
import com.example.materialdata.entity.PcestdttPK;
import com.example.materialdata.entity.Spestlab;
import com.example.materialdata.entity.SpestlabPK;
import com.example.materialdata.repository.PcestdttRepository;
import com.example.materialdata.repository.SpestlabRepository;

@Service
public class SpestlabServiceImpl implements SpestlabService{
	
	@Autowired
	private PcestdttRepository pcestdttRepository; 
	
	@Autowired 
	private SpestlabRepository spestlabRepository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveEntities(List<PcestdttDTO> pcestdttDTOs, List<SpestlabDTO> spestlabDTOs) throws Exception {
		
		try {
			for(PcestdttDTO pcestdttDTO: pcestdttDTOs) {
				Pcestdtt pcestdtt = new Pcestdtt();
				PcestdttPK id = new PcestdttPK();
				id.setEstimateNo(pcestdttDTO.getEstimateNo());
				id.setRevNo(1);
//				id.setRevNo(pcestdttDTO.getRevNo());
				id.setDeptId(pcestdttDTO.getDeptId());
				id.setResCd(pcestdttDTO.getResCd());
				pcestdtt.setId(id);
				pcestdtt.setEstimateQty(pcestdttDTO.getEstimateQty());
				pcestdtt.setEstimateCost(pcestdttDTO.getEstimateCost());
				pcestdttRepository.save(pcestdtt);
			}
			
			for(SpestlabDTO spestlabDTO : spestlabDTOs) {
				Spestlab spestlab = new Spestlab();
				SpestlabPK id = new SpestlabPK();
				id.setEstimateNo(spestlabDTO.getEstimateNo());
				id.setLabourCode(spestlabDTO.getLabourCode());
				id.setDeptId(spestlabDTO.getDeptId());
				spestlab.setId(id);
				spestlab.setActivityDescription(spestlabDTO.getActivityDescription());
				spestlab.setCebLabourCost(spestlabDTO.getLabourCost());
				spestlabRepository.save(spestlab);
			}
		}catch(Exception e) {
			throw new Exception("Error saving entities: " + e.getMessage(), e);
		}
	}
	
	@Override
	public List<Pcestdtt> getAllEntities(){
		return pcestdttRepository.findAll();
	}
	
	@Override
	public List<Spestlab> getAllLabourEntities(){
		return spestlabRepository.findAll();
	}

}
