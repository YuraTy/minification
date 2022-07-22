package com.testtask.minification.dao;

import com.testtask.minification.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityDao extends JpaRepository<Activity,Long> {

    List<Activity> findByUserLinkIdAndBrowser(int userLinkId,String browser);

    List<Activity> findByUserLinkIdAndReferer(int userLinkId,String referer);

    List<Activity> findByUserLinkId(int userLinkId);

    List<Activity> findByUserLinkIdAndTransitionDate(int userLinkId,LocalDateTime transitionDate);

}
