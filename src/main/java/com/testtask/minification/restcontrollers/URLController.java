package com.testtask.minification.restcontrollers;

import com.testtask.minification.dto.ActivityDTO;
import com.testtask.minification.dto.UserLinkDTO;
import com.testtask.minification.exceptions.RestResponseEntityExceptionHandler;
import com.testtask.minification.model.Activity;
import com.testtask.minification.services.ActivityService;
import com.testtask.minification.services.UserLinkService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class URLController {

    @Autowired
    private UserLinkService userLinkService;

    @Autowired
    private ActivityService activityService;

    @GetMapping(value = "/{shortURL}")
    public RedirectView redirectURL(@PathVariable String shortURL, HttpServletRequest request, HttpServletResponse response, @RequestHeader(value = "Referer", required = false) final String referer) {
        UserLinkDTO userLinkDTO = userLinkService.findByShortURL(shortURL);
        if (userLinkDTO.isActivation() && userLinkDTO.getEndData().isAfter(LocalDate.now())) {
            String redirectUrl = userLinkDTO.getOriginalURL();
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(redirectUrl);
            String browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser().getName();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            ActivityDTO activityDTO = ActivityDTO.builder()
                    .transitionDate(LocalDateTime.now())
                    .referer(referer)
                    .browser(browser)
                    .ip(ipAddress)
                    .userLinkDTO(userLinkDTO)
                    .build();
            activityService.create(activityDTO);
            return redirectView;
        }else {
            if (userLinkDTO.getEndData().isAfter(LocalDate.now())){
                userLinkDTO.setActivation(false);
                userLinkService.create(userLinkDTO);
            }
            return new RedirectView("errorPage");
        }
    }
}
