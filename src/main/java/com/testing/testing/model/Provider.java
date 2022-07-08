package com.testing.testing.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "providers")
public class Provider {
    

    @Id
    @Column(name = "provider_name")
    private String provider_name;
    
    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    @Column(name = "flow_name")
    private String flow_name;
    
    public String getFlow_name() {
        return flow_name;
    }

    public void setFlow_name(String flow_name) {
        this.flow_name = flow_name;
    }

    @Column(name = "downtime_from")
    public LocalDateTime downtime_from;

    public LocalDateTime getDowntime_from() {
        return downtime_from;
    }

    public void setDowntime_from(LocalDateTime downtime_from) {
        this.downtime_from = downtime_from;
    }

    @Column(name = "downitme_to")
    public LocalDateTime downtime_to;

    public LocalDateTime getDowntime_to() {
        return downtime_to;
    }

    public void setDowntime_to(LocalDateTime downtime_to) {
        this.downtime_to = downtime_to;
    }

    //Constructor
    public Provider(String provider_name, String flow_name, LocalDateTime downtime_from, LocalDateTime downtime_to) {
        this.provider_name = provider_name;
        this.flow_name = flow_name;
        this.downtime_from = downtime_from;
        this.downtime_to = downtime_to;
    }
}
