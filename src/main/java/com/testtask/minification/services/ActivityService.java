package com.testtask.minification.services;

import com.testtask.minification.dao.ActivityDao;
import com.testtask.minification.dto.ActivityDTO;
import com.testtask.minification.model.Activity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ModelMapper mapper;

    public ActivityDTO create(ActivityDTO activityDTO) {
        Activity activity = mappingDtoInModel(activityDTO);
        activityDao.save(activity);
        return activityDTO;
    }

    public List<ActivityDTO> findByUserLinkId(ActivityDTO activityDTO) {
        return activityDao.findByUserLinkId(activityDTO.getUserLink().getId()).stream().map(this::mappingModelInDTO).collect(Collectors.toList());
    }

    public List<ActivityDTO> findByDateAndUserLinkId(ActivityDTO activityDTO) {
        return activityDao.findByUserLinkIdAndTransitionDate(activityDTO.getUserLink().getId(),activityDTO.getTransitionDate()).stream().map(this::mappingModelInDTO).collect(Collectors.toList());
    }

    public List<ActivityDTO> findByBrowserAndUserLinkId(ActivityDTO activityDTO) {
        return activityDao.findByUserLinkIdAndBrowser(activityDTO.getUserLink().getId(),activityDTO.getBrowser()).stream().map(this::mappingModelInDTO).collect(Collectors.toList());
    }

    public List<ActivityDTO> findByRefererAndUserLinkId(ActivityDTO activityDTO) {
        return activityDao.findByUserLinkIdAndReferer(activityDTO.getUserLink().getId(),activityDTO.getReferer()).stream().map(this::mappingModelInDTO).collect(Collectors.toList());
    }

    private ActivityDTO mappingModelInDTO(Activity activity) {
        return mapper.map(activity, ActivityDTO.class);
    }

    private Activity mappingDtoInModel(ActivityDTO activityDTO) {
        return mapper.map(activityDTO,Activity.class);
    }
}
