package com.chb;

import com.chb.dao.ClientRepository;
import com.chb.dao.CoachRepository;
import com.chb.dao.FormuleRepository;
import com.chb.dao.PointRepository;
import com.chb.entities.*;
import com.chb.metier.IPointMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PointcoachingApplication implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private IPointMetier pointMetier;
    @Autowired
    private FormuleRepository formuleRepository;
    @Autowired
    private PointRepository pointRepository;
    public static void main(String[] args) {

        SpringApplication.run(PointcoachingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        coachRepository.save(new Coach("mor", "Mor"));
    }


}
