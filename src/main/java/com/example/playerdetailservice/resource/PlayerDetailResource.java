package com.example.playerdetailservice.resource;

import com.example.playerdetailservice.connector.DynamoDBConnector;
import com.example.playerdetailservice.model.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/player")
public class PlayerDetailResource {

    @Autowired
    private DynamoDBConnector dbConnector;

    @RequestMapping(value = "/{userId}")
    public UserDetails getUserProfileInfo(@PathVariable String userId) {
        log.info("Call came for getting profile data for userId: {}", userId);
        UserDetails details = dbConnector.getUserProfileData(userId);
        log.info("Fetched userDetails: {}", details.toString());
        return details;
    }

    @RequestMapping(value = "/{userId}/{emailId}/{mobileNumber}/{password}")
    public String saveUserDetails(@PathVariable String userId, @PathVariable String emailId,
                                @PathVariable String mobileNumber, @PathVariable String password) {
        UserDetails details=new UserDetails();
        details.setMobileNumber(mobileNumber);
        details.setUserId(userId);
        details.setPassword(password);
        details.setEmailId(emailId);
        dbConnector.saveUserProfileData(details);
        return "Save successfully done";
    }
}
