package com.chb.metier;

import com.chb.entities.Client;
import com.chb.entities.Coach;
import com.chb.entities.Point;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPointMetier {
    public Client consulterClient(String prenomClient);
    public Client findClientByCodeClient(Long codeClient);
    public List<Client> listClientDuCoach(String codeCoach);
    public Coach consulterCoach(String nomCoach);
    public List<Point> listPoints(String nomCoach);
    public List<Client> ClienteFormule(Long codeFormule, String codeCoach);
    public List<Client> findClFormCoach(Long codeFormule, String codeCoach);
//    public List<Client> findAllClFormCoach(Long codeFormule);
    public Point consulterPoint(Long codePoint);
    public List<Client> findAllClForm(Long codeFormule);
}
