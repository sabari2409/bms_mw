package com.scaler.bms.entity;

import com.scaler.bms.constants.TicketStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ticket extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Shows shows;

    //    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;

    @OneToMany(mappedBy = "ticket")
    private List<Payment> paymentList;

    private Long price;

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Shows getShow() {
        return shows;
    }

    public void setShow(Shows shows) {
        this.shows = shows;
    }

    public Users getUsers() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
