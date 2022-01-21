package com.chb.metier;

import com.chb.dao.ClientRepository;
import com.chb.dao.CoachRepository;
import com.chb.dao.PointRepository;
import com.chb.entities.Client;
import com.chb.entities.Coach;
import com.chb.entities.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PointMetierImpl implements IPointMetier{
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private PointRepository pointRepository;

    @Override
    public Client consulterClient(String prenomClient) {
        Client cl = clientRepository.consulterClientDao(prenomClient);
        if(cl==null) throw new RuntimeException("Le client est introuvable");
        return cl;
    }

    @Override
    public Client findClientByCodeClient(Long codeClient) {
        Client cl = clientRepository.findClientByCodeClient(codeClient);
        return cl;
    }

    @Override
    public List<Client> listClientDuCoach(String codeCoach) {
        return clientRepository.listClientsDuCoach(codeCoach);
    }

    @Override
    public Coach consulterCoach(String nomCoach) {
            Coach coach = coachRepository.consulterCoach(nomCoach);
            if(coach==null)
                throw new RuntimeException("Le coach est introuvable");
        return coach;
    }

    @Override
    public List<Point> listPoints(String nomCoach) {
        return pointRepository.listPoint(nomCoach);
    }

    @Override
    public List<Client> ClienteFormule(Long codeFormule, String codeCoach) {
        if(codeFormule == 1)
            return clientRepository.findClienteFormule(codeFormule,codeCoach);
        else if(codeFormule == 2)
            return clientRepository.findClienteFormule(codeFormule,codeCoach);
        else
            return clientRepository.findClienteFormule(codeFormule, codeCoach);
    }

    @Override
    public List<Client> findClFormCoach(Long codeFormule, String codeCoach) {
        if(codeFormule == 1)
            return clientRepository.findClFormCoach(codeFormule, codeCoach);
        else if(codeFormule == 2)
            return clientRepository.findClFormCoach(codeFormule, codeCoach);
        else
            return clientRepository.findClFormCoach(codeFormule, codeCoach);
    }

/*
    @Override
    public List<Client> findAllClFormCoach(Long codeFormule) {
        if(codeFormule == 1)
            return clientRepository.findAllClienteFormule(codeFormule);
        else if(codeFormule == 2)
            return clientRepository.findAllClienteFormule(codeFormule);
        else
            return clientRepository.findAllClienteFormule(codeFormule);
    }
*/

    @Override
    public Point consulterPoint(Long codePoint) {
        Point point = pointRepository.consulterPointCode(codePoint);
        if(codePoint==null)
            throw new RuntimeException("Le point est introuvable");
        return point;
    }

    @Override
    public List<Client> findAllClForm(Long codeFormule) {
        if(codeFormule == 1)
            return clientRepository.findAllClFormDao(codeFormule);
        else if(codeFormule == 2)
            return clientRepository.findAllClFormDao(codeFormule);
        else
            return clientRepository.findAllClFormDao(codeFormule);

    }

}
