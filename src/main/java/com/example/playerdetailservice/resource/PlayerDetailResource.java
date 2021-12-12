package com.example.playerdetailservice.resource;

import com.example.playerdetailservice.model.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerDetailResource {

    @RequestMapping(value="/{userId}")
    public UserDetails getUserProfileInfo(@PathVariable String userId) {
        UserDetails details=new UserDetails();
        details.setUserId(userId);
        // Hard coding UserDetails for now
        details.setPassword("pwd");
        details.setEmailId("id");
        details.setMobileNumber("num");
        return details;
    }
}
