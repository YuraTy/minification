package com.testtask.minification.services;

import com.testtask.minification.dao.UserLinkDao;
import com.testtask.minification.dto.UserLinkDTO;
import com.testtask.minification.model.UserLink;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLinkService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserLinkDao userLinkDao;

    public UserLinkDTO create(UserLinkDTO userLinkDto) {
        userLinkDto.setShortURL(randomURL());
        UserLink userLink = mappingDtoInModel(userLinkDto);
        userLinkDao.save(userLink);
        return userLinkDto;
    }

    public UserLinkDTO findByShortURL(String shortURL) {
        UserLink userLink = userLinkDao.findByShortURL(shortURL);
        return mappingModelInDTO(userLink);
    }

    public void delete(UserLinkDTO userLinkDTO) {
        UserLink userLink = mappingDtoInModel(userLinkDTO);
        userLinkDao.delete(userLink);
    }

    public UserLinkDTO update(int oldIdURL, UserLinkDTO userLinkDTONew) {
        userLinkDTONew.setId(oldIdURL);
        UserLink userLink = mappingDtoInModel(userLinkDTONew);
        userLinkDao.save(userLink);
        return userLinkDTONew;
    }

    private String randomURL() {
        String shortURL = generateRandomURL();
        while (checkUnique(shortURL)){
            if (checkUnique(shortURL)){
                shortURL = generateRandomURL();
            }
        }
        return shortURL;
    }

    private boolean checkUnique(String texForCheck) {
        return userLinkDao.findAll().stream().anyMatch(userLink -> userLink.getShortURL().equals(texForCheck));
    }

    private String generateRandomURL() {
        return  RandomStringUtils.random(6,true,true);
    }

    private UserLinkDTO mappingModelInDTO(UserLink userLink) {
        return mapper.map(userLink, UserLinkDTO.class);
    }

    private UserLink mappingDtoInModel(UserLinkDTO userLinkDto) {
        return mapper.map(userLinkDto,UserLink.class);
    }


}
