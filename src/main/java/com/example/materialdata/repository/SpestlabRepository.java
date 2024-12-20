package com.example.materialdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.materialdata.entity.Spestlab;
import com.example.materialdata.entity.SpestlabPK;

public interface SpestlabRepository extends JpaRepository<Spestlab, SpestlabPK> {

}

