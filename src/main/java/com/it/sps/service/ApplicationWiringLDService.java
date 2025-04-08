package com.it.sps.service;


import com.it.sps.dto.ApplicationDto;
import com.it.sps.dto.FormDataDto;
import com.it.sps.dto.WiringLandDetailDto;
import com.it.sps.dto.ApplicantDto;
import com.it.sps.entity.Applicant;
import com.it.sps.entity.Application;
import com.it.sps.entity.ApplicationPK;
import com.it.sps.entity.WiringLandDetail;
import com.it.sps.entity.WiringLandDetailPK;
import com.example.materialdata.repository.*;
import com.it.sps.repository.ApplicantRepository;
import com.it.sps.repository.ApplicationRepository;
import com.it.sps.repository.WiringLandDetailRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;

@Service
@Transactional
public class ApplicationWiringLDService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ApplicationRepository applicationRepository;
    private final WiringLandDetailRepository wiringLandDetailRepository;

//    @Autowired
//    private ApplicationReferenceRepository appRefRepository;

    private final ApplicantRepository applicantRepository;

    public ApplicationWiringLDService(ApplicationRepository applicationRepository, WiringLandDetailRepository wiringLandDetailRepository, ApplicantRepository applicantRepository) {
        this.applicationRepository = applicationRepository;
        this.wiringLandDetailRepository = wiringLandDetailRepository;
        this.applicantRepository = applicantRepository;
    }

    public void saveFullApplication(FormDataDto formData) {
        String applicationId = generateApplicationId(formData.getApplicationDto().getDeptId());

        formData.getApplicationDto().setApplicationId(applicationId);
        formData.getWiringLandDetailDto().setApplicationId(applicationId);
        formData.getWiringLandDetailDto().setDeptId(formData.getApplicationDto().getDeptId());

        saveContactPersonDetail(formData.getApplicationDto());
        saveWiringDetail(formData.getWiringLandDetailDto());
        createApplicant(formData.getApplicantDto());
    }

    @SuppressWarnings("unchecked")
    public String getNextAppId(String appIdPrefix, String webAppName) {
        //getEntityManager(webAppName);
        String sequence=null;
        String like=appIdPrefix+"%";
        String strQuery="select a.id.applicationId from Application a " +
                "where a.id.applicationId like :like ORDER BY 1 DESC";
        Query query=entityManager.createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
        query.setParameter("like", like);
        List<String> list=query.getResultList();
        if (list.size()!=0){
            sequence=query.getResultList().get(0).toString().trim();
            sequence=sequence.substring(14);//total 18 chars  year 12
            Integer i=Integer.parseInt(sequence)+1;
            sequence=i.toString();

        }else{
            sequence="0001";

        }
        if(sequence.length()==1)
            return "000"+sequence;
        else if (sequence.length()==2)
            return "00"+sequence;
        else if (sequence.length()==3)
            return "0"+sequence;
        else return sequence;
    }

    private String generateApplicationId(String deptId) {
        String sequence = getNextAppId(deptId,"app");
        String yearSuffix = String.valueOf(Year.now().getValue()).substring(2);
        return String.format("%s/ANC/%s/%04d", deptId, yearSuffix, Integer.parseInt(sequence));
    }

    public void saveContactPersonDetail(ApplicationDto applicationDto) {
        Application application = new Application();
        ApplicationPK applicationPK = new ApplicationPK();

        applicationPK.setApplicationId(applicationDto.getApplicationId());
        applicationPK.setDeptId(applicationDto.getDeptId());

        application.setId(applicationPK);
        application.setContactIdNo(applicationDto.getContactIdNo());
        application.setContactName(applicationDto.getContactName());
        application.setContactAddress(applicationDto.getContactAddress());
        application.setContactTelephone(applicationDto.getContactTelephone());
        application.setContactEmail(applicationDto.getContactEmail());
        application.setContactMobile(applicationDto.getContactMobile());

        applicationRepository.save(application);

    }

    public void saveWiringDetail(WiringLandDetailDto wiringLandDetailDto) {
        WiringLandDetail wiringLandDetail = new WiringLandDetail();
        WiringLandDetailPK wiringLandDetailPK = new WiringLandDetailPK();

        wiringLandDetailPK.setApplicationId(wiringLandDetailDto.getApplicationId());
        wiringLandDetailPK.setDeptId(wiringLandDetailDto.getDeptId());

        wiringLandDetail.setId(wiringLandDetailPK);
        wiringLandDetail.setServiceStreetAddress(wiringLandDetailDto.getServiceStreetAddress());
        wiringLandDetail.setServiceCity(wiringLandDetailDto.getServiceCity());
        wiringLandDetail.setServiceSuburb(wiringLandDetailDto.getServiceSuburb());
        wiringLandDetail.setServicePostalCode(wiringLandDetailDto.getServicePostalCode());
        wiringLandDetail.setAssessmentNo(wiringLandDetailDto.getAssessmentNo());
        wiringLandDetail.setNeighboursAccNo(wiringLandDetailDto.getNeighboursAccNo());
        wiringLandDetail.setOwnership(wiringLandDetailDto.getOwnership());

        wiringLandDetail.setMetalCrusher(wiringLandDetailDto.getMetalCrusher());
        wiringLandDetail.setSawMills(wiringLandDetailDto.getSawMills());
        wiringLandDetail.setWeldingPlant(wiringLandDetailDto.getWeldingPlant());
        wiringLandDetail.setPhase(wiringLandDetailDto.getPhase());
        wiringLandDetail.setCustomerCategory(wiringLandDetailDto.getCustomerCategory());
        wiringLandDetail.setTariffCatCode(wiringLandDetailDto.getTariffCatCode());
        wiringLandDetail.setTariffCode(wiringLandDetailDto.getTariffCode());
        wiringLandDetail.setConnectionType(wiringLandDetailDto.getConnectionType());
        wiringLandDetail.setCustomerType(wiringLandDetailDto.getCustomerType());

        wiringLandDetailRepository.save(wiringLandDetail);
    }

    private Applicant convertApplicantDTOtoEntity(ApplicantDto applicantDto) {

        Applicant applicant = new Applicant();
        applicant.setIdNo(applicantDto.getIdNo());
        applicant.setIdType(applicantDto.getIdType());
        applicant.setPersonalCorporate(applicantDto.getPersonalCorporate());
        applicant.setFirstName(applicantDto.getFirstName());
        applicant.setLastName(applicantDto.getLastName());
        applicant.setFullName(applicantDto.getFullName());
        applicant.setTelephoneNo(applicantDto.getTelephoneNo());
        applicant.setEmail(applicantDto.getEmail());
        applicant.setMobileNo(applicantDto.getMobileNo());
        applicant.setCity(applicantDto.getCity());
        applicant.setSuburb(applicantDto.getSuburb());
        applicant.setStreetAddress(applicantDto.getStreetAddress());
        applicant.setPostalCode(applicantDto.getPostalCode());
        applicant.setPreferredLanguage(applicantDto.getPreferredLanguage());

        return applicant;

    }
    public Applicant createApplicant(ApplicantDto applicantDto) {

        Applicant applicant = convertApplicantDTOtoEntity(applicantDto);
        return applicantRepository.save(applicant);

    }




}

