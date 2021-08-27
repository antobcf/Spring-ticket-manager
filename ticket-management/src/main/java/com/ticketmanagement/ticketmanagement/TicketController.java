package com.ticketmanagement.ticketmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:9001")
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private TicketRepository repo;

    @Autowired
    private EmailConfiguration emailCfg;

    @GetMapping("")
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = repo.findAll();
        return tickets;
    }

    @GetMapping("/employee/{username}")
    public List<Ticket> getUserTickets(@PathVariable("username") String username) {
        List<Ticket> tickets = repo.findByUsername(username);
        return tickets;
    }

    @GetMapping("/maintainer/{maintainer}")
    public List<Ticket> getMaintainerTickets(@PathVariable("maintainer") String maintainer) {
        List<Ticket> tickets = repo.findByMaintainer(maintainer);
        return tickets;
    }

    @GetMapping("/admin")
    public List<Ticket> getAdminTickets() {
        List<Ticket> tickets = repo.findOpenTicket();
        return tickets;
    }

    @PostMapping("/new")
    public void createTicket(@RequestBody Ticket ticket) {
        repo.save(ticket);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticketUpdate) {
        Optional<Ticket> ticketOpt = repo.findById(id);
        Ticket ticket = ticketOpt.get();
        if(ticket != null) {
            ticket.setStatus(ticketUpdate.getStatus());
            ticket.setMaintainer(ticketUpdate.getMaintainer());
            ticket.setMainEmail(ticketUpdate.getMainEmail());
            repo.save(ticket);
        }
    }

    @PostMapping("/email/newTicket/{userEmail}")
    public void confirmNewTicket(@PathVariable("userEmail") String userEmail) {
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("office@office.com");
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Ticket opened confirmation");
        mailMessage.setText("Your ticket has been opened. You will be notified when it will be approved");

        mailSender.send(mailMessage);

        SimpleMailMessage mailMessageAdmin = new SimpleMailMessage();
        mailMessageAdmin.setFrom("office@office.com");
        mailMessageAdmin.setTo("mario.rossi@libero.it");
        mailMessageAdmin.setSubject("New ticket opened");
        mailMessageAdmin.setText("A new ticket has been opened.");

        mailSender.send(mailMessageAdmin);
    }

    @PostMapping("/email/approved/{id}")
    public void approvedTicket(@PathVariable("id") Long id) {
    
        Optional<Ticket> ticketOpt = repo.findById(id);
        Ticket ticket = ticketOpt.get();
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("office@office.com");
        mailMessage.setTo(ticket.getUserEmail());
        mailMessage.setSubject("Ticket approved");
        mailMessage.setText("Your ticket "+ticket.getTicket_id()+" has been approved. You will be notified when it will be closed.");

        mailSender.send(mailMessage);
    }

    @CrossOrigin(origins = "http://localhost:9001")
    @PostMapping("/email/assigned/{mainEmail}")
    public void assigneddTicket(@PathVariable("mainEmail") String mainEmail) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("office@office.com");
        mailMessage.setTo(mainEmail);
        mailMessage.setSubject("Ticket assigned");
        mailMessage.setText("A new work has been assigned to you.");

        mailSender.send(mailMessage);
    }

    @PostMapping("/email/rejected/{id}")
    public void rejectedTicket(@PathVariable("id") Long id) {
        
        Optional<Ticket> ticketOpt = repo.findById(id);
        Ticket ticket = ticketOpt.get();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("office@office.com");
        mailMessage.setTo(ticket.getUserEmail());
        mailMessage.setSubject("Ticket rejected");
        mailMessage.setText("Your ticket "+ticket.getTicket_id()+"has been rejected.");

        mailSender.send(mailMessage);
    }

    @PostMapping("/email/closed/{id}")
    public void closedTicket(@PathVariable("id") Long id) {
        
        Optional<Ticket> ticketOpt = repo.findById(id);
        Ticket ticket = ticketOpt.get();
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("office@office.com");
        mailMessage.setTo(ticket.getUserEmail());
        mailMessage.setSubject("Ticket closed");
        mailMessage.setText("Your ticket has been closed. Thanks for your support.");

        mailSender.send(mailMessage);
    }
}
