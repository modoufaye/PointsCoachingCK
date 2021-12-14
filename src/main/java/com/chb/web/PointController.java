package com.chb.web;

import com.chb.dao.ClientRepository;
import com.chb.dao.CoachRepository;
import com.chb.dao.PointRepository;
import com.chb.entities.*;
import com.chb.metier.IPointMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@WebServlet("/PointController")
@Controller
public class PointController extends HttpServlet{
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private IPointMetier pointMetier;

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request){
        if(request.isUserInRole("SUPERADMIN"))
            return "redirect:/tabClient";
        else if(request.isUserInRole("USER"))
            return "redirect:/listClientsDuCoach";
        else
            return "redirect:/login";
    }

    @GetMapping(value = "/tabClient")
    public String index2(Model model) {
        try {
            List<Client> pageClients = clientRepository.findAll();
            model.addAttribute("tabClient", pageClients);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "tabClient";
    }

    @RequestMapping("/consulterClient")
    public String consulter(Model model, String prenomClient) {
        try {
           Client cl = clientRepository.consulterClientDao(prenomClient);
           String formule = cl.getFormule().getClass().getSimpleName();
           String coach = cl.getCoach().getNomCoach();
           model.addAttribute("coachClient", coach);
           model.addAttribute("formule", formule);
           model.addAttribute("client", cl);
           List<Point>  pagePoints = (List<Point>) cl.getPoints();
           model.addAttribute("listPoint", pagePoints);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "profilClient";
    }
//    ******************************* L'ajout d'une cliente ************************
    @RequestMapping(value = "/ajoutClient", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("client", new Client());
        return "ajoutClient";
    }

    @RequestMapping(value = "/saveClient", method = RequestMethod.POST)
    public String save(@Validated Client client, BindingResult bindingResult) {
        Coach coach = new Coach();
        Formule formule;
        if(bindingResult.hasErrors()){
            return "ajoutClient";
        }
        client.setPoidsActuel(client.getPoidsClient());
        clientRepository.save(client);
        client.setCoach(coach);
            if(client.getFormule().getCodeFormule() == 1){
                formule = new Basic();
            }
            else if(client.getFormule().getCodeFormule() == 2){
                formule = new Silver();
            }
            else
                formule = new Gold();
        client.setFormule(formule);
        return "redirect:/listClientsDuCoach";
    }
//    ************************** Mise à jour d'une cliente ************************
    @RequestMapping(value = "/editClient", method = RequestMethod.GET)
    public String editCli(Long codeClient, Model model) {
        Client cl = clientRepository.findClientByCodeClient(codeClient);
        model.addAttribute("client", cl);
        return "editClient";
    }
    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    public String updateCli(Client client) {
        Coach coach = new Coach();
        Formule formule;
        clientRepository.save(client);
        client.setCoach(coach);
        client.setCodeClient(client.getCodeClient());
        if(client.getFormule().getCodeFormule() == 1){
            formule = new Basic();
        }
        else if(client.getFormule().getCodeFormule() == 2){
            formule = new Silver();
        }
        else
            formule = new Gold();
        client.setFormule(formule);
        return "redirect:/listClientsDuCoach";
    }

    @RequestMapping(value = "/deleteClient")
    public String deleteClient(Long codeClient){
        Client cl = clientRepository.findClientByCodeClient(codeClient);
        clientRepository.delete(cl);
        return "redirect:/listClientsDuCoach";
    }

    @GetMapping(value = "/listClientsDuCoach")
    public String clientCoach(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        if(!(username.isEmpty())){
                List<Client> pageClients = pointMetier.listClientDuCoach(username);
                model.addAttribute("listClients", pageClients);
                return "listClientsDuCoach";

        } else
            return "403";
    }
    // ************************** Formulaire d'ajout pour les points ***************************************
    @RequestMapping(value = "/ajoutPoint", method = RequestMethod.GET)
    public String formPoint(Model model){
        Point p = new Point();
        model.addAttribute("point", p);
        return "profilClient";
    }
    @RequestMapping(value = "/savePoint", method = RequestMethod.POST)
    public String savePoint(Point point, Client client) {
        Date date = new Date();
        int i = point.getSemaine();
        i = i + 1;
        point.setDatePoint(date);
        point.setClient(client);
        point.setSemaine(i);
//        point.getResumeRdv().setNoteResumeRdv("Pas encore");
        Point p = pointRepository.save(point);
        Long codeClient = p.getClient().getCodeClient();
        Client clientUpdate = clientRepository.findClientByCodeClient(codeClient);
        clientUpdate.setPoidsActuel(clientUpdate.getPoidsActuel()-p.getPoidsPerdus());
        clientRepository.save(clientUpdate);
        return "redirect:/listClientsDuCoach";
    }
    // Mise à jour d'un point
    @RequestMapping(value = "/editPoint", method = RequestMethod.GET)
    public String editPoint(Model model, Long codePoint) {
        Point p = pointRepository.consulterPointCode(codePoint);
        model.addAttribute("point", p);
        return "profilClient";
    }
    @RequestMapping(value = "/updatePoint", method = RequestMethod.POST)
    public String updatePoint(Point point, Client client) {
        Date date = new Date();
        int i = point.getSemaine();
        point.setDatePoint(date);
        point.setClient(client);
        point.setSemaine(i);
        pointRepository.save(point);
        Point p = point;
        Client client1 = client;
        client.setPoidsActuel(client.getPoidsActuel()-point.getPoidsPerdus());
        return "redirect:/listClientsDuCoach";
    }

    @RequestMapping("/listPoint")
    public String tabPoints(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        List<Point> tabPoints = pointMetier.listPoints(username);
        model.addAttribute("listPoint", tabPoints);
        return "listPoint";
    }

    @RequestMapping(value = "/deletePoint")
    public String deletePt(Long codePoint){
       Point p = pointRepository.consulterPointCode(codePoint);
        pointRepository.delete(p);

        return "redirect:/profilClient";
    }

    // **************************** Formulaire d'ajout et d'affichage pour les coachs ****************************************
    @RequestMapping(value = "/ajoutCoach", method = RequestMethod.GET)
    public String formCoach(Model model) {
        model.addAttribute("coach", new Coach());
        return "ajoutCoach";
    }
    @RequestMapping(value = "/saveCoach", method = RequestMethod.POST)
    public String saveCoach(Coach coach) {
        coachRepository.save(coach);
        return "redirect:/listCoach";
    }
    @RequestMapping("/listCoach")
    public String listCocach(Model model, HttpServletRequest request) {
        try {
            String username = request.getUserPrincipal().getName();
            Coach coach = coachRepository.consulterCoach(username);
            model.addAttribute("coach", coach);
            model.addAttribute("clB", pointMetier.ClienteFormule(1L,username));
            model.addAttribute("clS", pointMetier.ClienteFormule(2L,username));
            model.addAttribute("clG", pointMetier.ClienteFormule(3L,username));
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "listCoach";
    }
    // Liste des etudiant nen fonction d'une formule
    @RequestMapping(value = "/listBasic")
    public String tabBasic(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        List<Client> tabClient = pointMetier.findClFormCoach(1L, username);
        model.addAttribute("listBas", tabClient);
        return "listBasic";
    }
    @RequestMapping(value = "/listSilver")
    public String tabSilver(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        List<Client> tabClient = pointMetier.findClFormCoach(2L, username);
        model.addAttribute("listSil", tabClient);
        return "listSilver";
    }
    @RequestMapping(value = "/listGold")
    public String tabGold(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        List<Client> tabClient = pointMetier.findClFormCoach(3L, username);
        model.addAttribute("listGol", tabClient);
        return "listGold";
    }
}
