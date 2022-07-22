package com.testtask.minification.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class UserLinkDTO {

    private Integer id;

    private String originalURL;

    private String shortURL;

    private LocalDate endData;

    private boolean activation;

    public UserLinkDTO(){}

    public UserLinkDTO(Integer id, String originalURL, String shortURL, LocalDate endData, boolean activation) {
        this.id = id;
        this.originalURL = originalURL;
        this.shortURL = shortURL;
        this.endData = endData;
        this.activation = activation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public LocalDate getEndData() {
        return endData;
    }

    public void setEndData(LocalDate endData) {
        this.endData = endData;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }
}
