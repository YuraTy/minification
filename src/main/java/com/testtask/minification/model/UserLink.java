package com.testtask.minification.model;


import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "USER_LINK")
public class UserLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORIGINAL_URL" , columnDefinition = "TEXT")
    private String originalURL;

    @Column(name = "SHORT_URL", unique = true)
    private String shortURL;

    @Column(name = "END_DATA")
    private LocalDate endData;

    @Column(name = "ACTIVATION")
    private boolean activation;

    public UserLink(Integer id, String originalURL, String shortURL, LocalDate endData, boolean activation) {
        this.id = id;
        this.originalURL = originalURL;
        this.shortURL = shortURL;
        this.endData = endData;
        this.activation = activation;
    }

    public UserLink(){}

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
