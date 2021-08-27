package com.ticketmanagement.ticketmanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    @Query("SELECT t FROM Ticket t WHERE t.username = ?1")
    List<Ticket> findByUsername(String username);

    @Query("SELECT t FROM Ticket t WHERE t.maintainer = ?1 AND t.maintainer IS NOT NULL AND t.status = 'assigned' OR t.status = 'work in progress'")
    List<Ticket> findByMaintainer(String maintainer);

    @Query("SELECT t FROM Ticket t WHERE t.status <> 'closed'")
    List<Ticket> findOpenTicket();

}
