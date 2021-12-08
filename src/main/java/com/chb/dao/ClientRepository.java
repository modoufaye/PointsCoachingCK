package com.chb.dao;

import com.chb.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("Select c from Client c where c.prenomClient =:x")
    public Client consulterClientDao(@Param("x") String prenomClient);

    @Query("Select c from Client c where c.codeClient =:x")
    public Client findClientByCodeClient(@Param("x") Long codeClient);

    @Query("Select c from Client c where c.coach.codeCoach =:x")
    public List<Client> listClientsDuCoach(@Param("x") String codeCoach);

    @Query("Select c from Client c where c.formule.codeFormule =:x and c.coach.codeCoach =:y")
    public List<Client> findClienteFormule(@Param("x") Long codeFormule, @Param("y") String  codeCoach);

    @Query("Select c from Client c where c.formule.codeFormule =:x and c.coach.codeCoach =:y")
    public List<Client> findClFormCoach(@Param("x") Long codeFormule, @Param("y") String codeCoach);

}
