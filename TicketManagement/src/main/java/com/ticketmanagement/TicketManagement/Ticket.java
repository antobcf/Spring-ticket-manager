package com.ticketmanagement.TicketManagement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date openDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private Long asset_id;

    @Column(nullable = true)
    private String maintainer;

    @Column(nullable = true)
    private String mainEmail;
    
    public Ticket() {

    }

    public String getMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(Long asset_id) {
        this.asset_id = asset_id;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Ticket(Long ticket_id, String description, Date openDate, String status, String username, Long asset_id,
            String maintainer) {
        this.ticket_id = ticket_id;
        this.description = description;
        this.openDate = openDate;
        this.status = status;
        this.username = username;
        this.asset_id = asset_id;
        this.maintainer = maintainer;
    }

}
