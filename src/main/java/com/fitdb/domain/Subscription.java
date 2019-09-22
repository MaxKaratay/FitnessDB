package com.fitdb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instruct_id")
    private Instruct instruct;
    @OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visits> visits;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date durationEnd;

    public Subscription(){}

    public Subscription(@NotNull Client client, @NotNull Instruct instruct,@NotNull Date durationEnd) {
        this.client = client;
        this.instruct = instruct;
        this.durationEnd = durationEnd;
    }

    public List<Visits> getVisits() {
        return visits;
    }

    public void addVisit(Visits visit) {
        visits.add(visit);
    }

    public void removeVisit(Visits visit) {
        visits.remove(visit);
        if (visit != null) {
            visit.setSubscription(null);
        }
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getDurationEnd() {
        return durationEnd;
    }

    public void setDurationEnd(Date durationEnd) {
        this.durationEnd = durationEnd;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Instruct getInstruct() {
        return instruct;
    }

    public void setInstruct(Instruct instruct) {
        this.instruct = instruct;
    }
}
