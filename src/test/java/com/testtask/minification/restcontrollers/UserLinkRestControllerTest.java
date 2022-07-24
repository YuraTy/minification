package com.testtask.minification.restcontrollers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.minification.dto.UserLinkDTO;
import com.testtask.minification.services.UserLinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
import java.time.format.DateTimeFormatter;

@SpringBootTest
@AutoConfigureMockMvc
class UserLinkRestControllerTest {

    @MockBean
    private UserLinkService userLinkService;

    @Autowired
    private MockMvc mockMvc;

    private UserLinkDTO getTestUserDTO() {
        return UserLinkDTO.builder()
                .activation(true)
                .id(1)
                .endData(LocalDate.parse("2022-07-27", DateTimeFormatter.ISO_LOCAL_DATE))
                .originalURL("https://habr.com/ru/post/678542/")
                .shortURL("http://localhost:8080/WE78de")
                .build();
    }

    @Test
    void create() throws Exception {
        Mockito.when(userLinkService.create(Mockito.any())).thenReturn(getTestUserDTO());
        String uri = "/api/link";
        String inputJson = mapToJson(getTestUserDTO());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        UserLinkDTO actualLink = mapFromJson(content, UserLinkDTO.class);
        Assertions.assertEquals(getTestUserDTO(), actualLink);
    }

    @Test
    void update() throws Exception {
        String uri = "/api/link/1";
        UserLinkDTO expectedLink = getTestUserDTO();
        expectedLink.setEndData(LocalDate.parse("2022-08-27", DateTimeFormatter.ISO_LOCAL_DATE));
        String inputJson = mapToJson(expectedLink);
        Mockito.when(userLinkService.update(Mockito.anyInt(), Mockito.any())).thenReturn(expectedLink);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        UserLinkDTO actualUserLinkDTO = mapFromJson(content, UserLinkDTO.class);
        Assertions.assertEquals(expectedLink, actualUserLinkDTO);
    }

    @Test
    void delete() throws Exception {
        Mockito.when(userLinkService.create(Mockito.any())).thenReturn(getTestUserDTO());
        String uri = "/api/link";
        String inputJson = mapToJson(getTestUserDTO());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(json, clazz);
    }
}