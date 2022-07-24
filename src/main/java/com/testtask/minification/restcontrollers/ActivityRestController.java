package com.testtask.minification.restcontrollers;

import com.testtask.minification.dto.ActivityDTO;
import com.testtask.minification.dto.UserLinkDTO;
import com.testtask.minification.services.ActivityService;
import com.testtask.minification.services.UserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ActivityRestController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserLinkService userLinkService;

    @GetMapping(value = "/transitions/{shortURL}")
    @ResponseBody
    public Map<String, String> allTransitions(@PathVariable String shortURL) {
        UserLinkDTO userLinkDTO = userLinkService.findByShortURL(shortURL);
        int countTransitions = activityService.findByUserLinkId(ActivityDTO.builder().userLinkDTO(userLinkDTO).build()).size();
        Map<String, String> mapForJson = new HashMap<>();
        mapForJson.put("amount-all-transitions", String.valueOf(countTransitions));
        return mapForJson;
    }

    @GetMapping(value = "/transitions-shorturl-date/{shortURL}/{dataTransition}")
    @ResponseBody
    public Map<String, String> transitionsByURLAndDate(@PathVariable String shortURL, @PathVariable LocalDateTime dataTransition) {
        UserLinkDTO userLinkDTO = userLinkService.findByShortURL(shortURL);
        int countTransitions = activityService.findByDateAndUserLinkId(ActivityDTO.builder()
                        .userLinkDTO(userLinkDTO)
                        .transitionDate(dataTransition)
                        .build())
                .size();
        Map<String, String> mapForJson = new HashMap<>();
        mapForJson.put("allTransitionsByShortURLAndDate", String.valueOf(countTransitions));
        return mapForJson;
    }

    @GetMapping(value = "/transitions-shorturl-browser/{shortURL}/{browser}")
    @ResponseBody
    public Map<String, String> transitionsByShortURLAndBrowser(@PathVariable String shortURL, @PathVariable String browser) {
        UserLinkDTO userLinkDTO = userLinkService.findByShortURL(shortURL);
        int countTransitions = activityService.findByBrowserAndUserLinkId(ActivityDTO.builder()
                        .browser(browser)
                        .userLinkDTO(userLinkDTO)
                        .build())
                .size();
        Map<String, String> mapForJson = new HashMap<>();
        mapForJson.put("amount-transitions-shorturl-browser", String.valueOf(countTransitions));
        return mapForJson;
    }

    @GetMapping(value = "/transitions-shorturl-referer/{shortURL}/{referer}")
    @ResponseBody
    public Map<String, String> transitionByShortURLAndReferer(@PathVariable String shortURL, @PathVariable String referer) {
        UserLinkDTO userLinkDTO = userLinkService.findByShortURL(shortURL);
        int countTransitions = activityService.findByRefererAndUserLinkId(ActivityDTO.builder()
                        .userLinkDTO(userLinkDTO)
                        .referer(referer)
                        .build())
                .size();
        Map<String, String> mapForJson = new HashMap<>();
        mapForJson.put("amount-transitions-shorturl-referer", String.valueOf(countTransitions));
        return mapForJson;
    }


}
