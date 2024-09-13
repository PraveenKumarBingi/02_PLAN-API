package com.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.entity.EligibilityDetails;
import com.app.repository.EligibilityDetailsRepo;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private EligibilityDetailsRepo repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        EligibilityDetails entity1 = new EligibilityDetails();
        entity1.setEligId(1);
        entity1.setName("Shiva");
        entity1.setMobile(8794739917L);
        entity1.setEmail("shiva.java@gmail.com");
        entity1.setGender('M');
        entity1.setSsn(8027414285L);
        entity1.setPlanName("SAP");
        entity1.setPlanStatus("Approved");
        repo.save(entity1);

        EligibilityDetails entity2 = new EligibilityDetails();
        entity2.setEligId(2);
        entity2.setName("Vishnu");
        entity2.setMobile(9704882702L);
        entity2.setEmail("vishnu.java@gmail.com");
        entity2.setGender('M');
        entity2.setSsn(972835782L);
        entity2.setPlanName("CCAP");
        entity2.setPlanStatus("Denied");
        repo.save(entity2);

        EligibilityDetails entity3 = new EligibilityDetails();
        entity3.setEligId(3);
        entity3.setName("Mahesh");
        entity3.setMobile(9126497391L);
        entity3.setEmail("mahesh.java@gmail.com");
        entity3.setGender('M');
        entity3.setSsn(90143979274L);
        entity3.setPlanName("SAP");
        entity3.setPlanStatus("Pending");
        repo.save(entity3);

        EligibilityDetails entity4 = new EligibilityDetails();
        entity4.setEligId(4);
        entity4.setName("Sitara");
        entity4.setMobile(92749273932L);
        entity4.setEmail("sitara.java@gmail.com");
        entity4.setGender('F');
        entity4.setSsn(6729840280L);
        entity4.setPlanName("MEDICAL");
        entity4.setPlanStatus("Approved");
        repo.save(entity4);
    }
}
