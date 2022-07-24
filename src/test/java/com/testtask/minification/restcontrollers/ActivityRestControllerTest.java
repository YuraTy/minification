package com.testtask.minification.restcontrollers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.minification.dto.ActivityDTO;
import com.testtask.minification.dto.UserLinkDTO;
import com.testtask.minification.services.ActivityService;
import com.testtask.minification.services.UserLinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class ActivityRestControllerTest {

    @MockBean
    private ActivityService activityService;

    @MockBean
    private UserLinkService userLinkService;

    @Autowired
    private MockMvc mockMvc;

    private UserLinkDTO getTestUserDTO() {
        return UserLinkDTO.builder()
                .activation(true)
                .id(1)
                .endData(LocalDate.parse("2022-07-27"))
                .originalURL("https://habr.com/ru/post/678542/")
                .shortURL("http://localhost:8080/WE78de")
                .build();
    }

    private ActivityDTO getTestActivityDTO() {
        return ActivityDTO.builder()
                .userLinkDTO(getTestUserDTO())
                .transitionDate(LocalDateTime.now())
                .id(2)
                .browser("Chrome 10")
                .referer("habr.com")
                .ip("192.167.0.104")
                .build();
    }

    @Test
    void allTransitions() throws Exception {
        List<ActivityDTO> testList = new ArrayList<>();
        testList.add(getTestActivityDTO());
        Mockito.when(userLinkService.findByShortURL(Mockito.any())).thenReturn(getTestUserDTO());
        Mockito.when(activityService.findByUserLinkId(Mockito.any())).thenReturn(testList);

        String uri = "/api/transitions/WE78de";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        Map<String, String> mapTest = new HashMap<>();
        mapTest.put("amount-all-transitions", String.valueOf(1));

        Map<String, String> actualList = mapFromJson(content, Map.class);
        Assertions.assertEquals(mapTest, actualList);
    }

    @Test
    void transitionsByURLAndDate() throws Exception {
        List<ActivityDTO> testList = new ArrayList<>();
        testList.add(getTestActivityDTO());
        Mockito.when(userLinkService.findByShortURL(Mockito.any())).thenReturn(getTestUserDTO());
        Mockito.when(activityService.findByDateAndUserLinkId(Mockito.any())).thenReturn(testList);

        String uri = "/api/transitions-shorturl-date/WE78de/2022-07-22T18:22:00";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        Map<String, String> mapTest = new HashMap<>();
        mapTest.put("amount-transitions-shorturl-date", String.valueOf(1));

        Map<String, String> actualList = mapFromJson(content, Map.class);
        Assertions.assertEquals(mapTest, actualList);
    }

    @Test
    void transitionsByShortURLAndBrowser() throws Exception {
        List<ActivityDTO> testList = new ArrayList<>();
        testList.add(getTestActivityDTO());
        Mockito.when(userLinkService.findByShortURL(Mockito.any())).thenReturn(getTestUserDTO());
        Mockito.when(activityService.findByBrowserAndUserLinkId(Mockito.any())).thenReturn(testList);

        String uri = "/api/transitions-shorturl-browser/WE78de/Chrome 10";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        Map<String, String> mapTest = new HashMap<>();
        mapTest.put("amount-transitions-shorturl-browser", String.valueOf(1));

        Map<String, String> actualList = mapFromJson(content, Map.class);
        Assertions.assertEquals(mapTest, actualList);
    }

    @Test
    void transitionByShortURLAndReferer() throws Exception {
        List<ActivityDTO> testList = new ArrayList<>();
        testList.add(getTestActivityDTO());
        Mockito.when(userLinkService.findByShortURL(Mockito.any())).thenReturn(getTestUserDTO());
        Mockito.when(activityService.findByRefererAndUserLinkId(Mockito.any())).thenReturn(testList);

        String uri = "/api/transitions-shorturl-referer/WE78de/habr.com";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        Map<String, String> mapTest = new HashMap<>();
        mapTest.put("amount-transitions-shorturl-referer", String.valueOf(1));

        Map<String, String> actualList = mapFromJson(content, Map.class);
        Assertions.assertEquals(mapTest, actualList);
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}