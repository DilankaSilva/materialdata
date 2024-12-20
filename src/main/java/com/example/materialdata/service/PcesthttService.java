package com.example.materialdata.service;

import java.util.List;

import com.example.materialdata.dto.PcesthttDTO;
import com.example.materialdata.entity.Pcesthtt;
import com.example.materialdata.entity.PcesthttPK;

public interface PcesthttService {
	Pcesthtt saveEntity(PcesthttDTO pcesthttDTO);
	List<Pcesthtt> getAllEntities();
	Pcesthtt getEntity(PcesthttPK Id);

}
