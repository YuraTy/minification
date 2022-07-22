package com.testtask.minification.dao;

import com.testtask.minification.model.UserLink;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserLinkDao extends JpaRepository<UserLink,Long> {

    UserLink findByShortURL(String shortURL);
}
