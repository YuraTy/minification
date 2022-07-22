package com.testtask.minification.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITY")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_link_id")
    private UserLink userLink;

    @Column(name = "TRANSITION_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime transitionDate;

    @Column(name = "REFERER")
    private String referer;

    @Column(name = "IP")
    private String ip;

    @Column(name = "BROWSER")
    private String browser;

    public UserLink getUserLink() {
        return userLink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserLink(UserLink userLink) {
        this.userLink = userLink;
    }

    public LocalDateTime getTransitionDate() {
        return transitionDate;
    }

    public void setTransitionDate(LocalDateTime transitionDate) {
        this.transitionDate = transitionDate;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
