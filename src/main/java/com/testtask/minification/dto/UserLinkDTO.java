package com.testtask.minification.dto;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public class UserLinkDTO {

    private Integer id;

    private String originalURL;

    private String shortURL;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserLinkDTO))
            return false;
        UserLinkDTO userLinkDTO = (UserLinkDTO) obj;
        return this.originalURL.equals(userLinkDTO.originalURL) && this.shortURL.equals(userLinkDTO.shortURL) && this.endData.equals(userLinkDTO.endData) && (this.activation == userLinkDTO.activation);
    }
}
