package com.testtask.minification.restcontrollers;

import com.testtask.minification.dto.UserLinkDTO;
import com.testtask.minification.services.UserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserLinkRestController {

    @Autowired
    private UserLinkService userLinkService;

    @PutMapping(value = "/link")
    @ResponseBody
    public UserLinkDTO create(@RequestBody UserLinkDTO userLinkDTO) {
        return userLinkService.create(userLinkDTO);
    }

    @PostMapping(value = "/link/{oldId}")
    @ResponseBody
    public UserLinkDTO update(@RequestBody UserLinkDTO userLinkDTONew,@PathVariable int oldId) {
        return userLinkService.update(oldId,userLinkDTONew);
    }

    @DeleteMapping(value = "/link")
    @ResponseBody
    public void delete(@RequestBody UserLinkDTO userLinkDTO) {
        userLinkService.delete(userLinkDTO);
    }

}
