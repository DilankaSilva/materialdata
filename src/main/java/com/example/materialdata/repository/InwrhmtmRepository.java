package com.example.materialdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.materialdata.dto.AvailableMaterial;
import com.example.materialdata.entity.Inwrhmtm;
import com.example.materialdata.entity.InwrhmtmPK;

@Repository
public interface InwrhmtmRepository extends JpaRepository<Inwrhmtm, InwrhmtmPK> {

    @Query("SELECT DISTINCT new com.example.materialdata.dto.AvailableMaterial(m.matNm, m.unitPrice, w.qtyOnHand, w.uomCd, w.id.matCd ) " +
           "FROM Inwrhmtm w " +
           "JOIN Inmatm m ON w.id.matCd = m.matCd " +
           "WHERE w.status = 2 " +
           "AND (m.status = 2 OR m.status = 7) " +
           "AND w.qtyOnHand >= 1 "
           + "AND w.id.deptId = :deptId ")
    List<AvailableMaterial> findavailableMaterial(@Param("deptId") String deptId);
}