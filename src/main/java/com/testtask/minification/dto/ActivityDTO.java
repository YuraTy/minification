package com.testtask.minification.dto;


import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public class ActivityDTO {

    private Integer id;

    private UserLinkDTO userLinkDTO;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime transitionDate;

    private String referer;

    private String ip;

    private String browser;

    public ActivityDTO(){}

    public ActivityDTO(Integer id, UserLinkDTO userLinkDTO, LocalDateTime transitionDate, String referer, String ip, String browser) {
        this.id = id;
        this.userLinkDTO = userLinkDTO;
        this.transitionDate = transitionDate;
        this.referer = referer;
        this.ip = ip;
        this.browser = browser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserLinkDTO getUserLink() {
        return userLinkDTO;
    }

    public void setUserLink(UserLinkDTO userLinkDTO) {
        this.userLinkDTO = userLinkDTO;
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
