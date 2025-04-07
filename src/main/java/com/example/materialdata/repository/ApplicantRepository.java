package com.example.materialdata.repository;

import com.example.materialdata.dto.ApplicantDto;
import com.example.materialdata.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicantRepository extends JpaRepository<Applicant, String> {

    @Query("SELECT new com.example.materialdata.dto.ApplicantDto(a.idNo, a.idType, a.personalCorporate, a.fullName, a.firstName, " +
            "a.lastName, a.telephoneNo, a.email, a.mobileNo, a.city, a.suburb, a.streetAddress, a.postalCode," +
            "a.preferredLanguage) FROM Applicant a WHERE a.idNo = ?1")
    ApplicantDto findApplicantsByIdNo(String applicantId);
}