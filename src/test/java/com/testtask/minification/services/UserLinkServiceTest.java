package com.testtask.minification.services;

import com.testtask.minification.dao.UserLinkDao;
import com.testtask.minification.dto.UserLinkDTO;
import com.testtask.minification.model.UserLink;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = UserLinkService.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserLinkServiceTest {

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserLinkDao userLinkDao;

    @Autowired
    private UserLinkService userLinkService;

    private UserLink testUserLink() {
        return UserLink.builder()
                .id(1)
                .shortURL("http://loca.ly/aWda21")
                .originalURL("https://habr.com/ru/post/678072/")
                .endData(LocalDate.parse("2022-07-22"))
                .activation(true)
                .build();
    }

    private UserLinkDTO testUsrLinkDTO() {
        return UserLinkDTO.builder()
                .id(1)
                .originalURL("https://habr.com/ru/post/678072/")
                .endData(LocalDate.parse("2022-07-22"))
                .activation(true)
                .build();
    }

    @Test
    void create() {
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(testUserLink());
        Mockito.when(userLinkDao.findAll()).thenReturn(new ArrayList<>());
        userLinkService.create(testUsrLinkDTO());
        Mockito.verify(userLinkDao).save(Mockito.any());
    }

    @Test
    void delete() {
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(testUserLink());
        userLinkService.delete(testUsrLinkDTO());
        Mockito.verify(userLinkDao).delete(Mockito.any());
    }

    @Test
    void update() {
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(testUserLink());
        userLinkService.update(1,testUsrLinkDTO());
        Mockito.verify(userLinkDao).save(Mockito.any());
    }
}