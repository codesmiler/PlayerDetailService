package com.example.playerdetailservice.connector;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.playerdetailservice.exception.DynamoDBException;
import com.example.playerdetailservice.model.UserDetails;
import com.example.playerdetailservice.model.db.UserProfileTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DynamoDBConnector {

    @Autowired
    private DynamoDBMapper dbMapper;

    public void saveUserProfileData(UserDetails userDetails) {
        UserProfileTable dbObj = new UserProfileTable();
        dbObj.setUserId(userDetails.getUserId());
        dbObj.setPassword(userDetails.getPassword());
        dbObj.setEmailId_mobileNumber(userDetails.getEmailId() + "_" + userDetails.getMobileNumber());
        log.info("Making a save call for UserDetails: {}", userDetails.toString());
        try {
            dbMapper.save(dbObj);
            log.info("Saving userDetails successful for: {}", userDetails.toString());
        } catch (Exception e) {
            log.error("Exception while saving the user profile data: {}",e);
            new DynamoDBException(e.getMessage());
        }
    }

    public UserDetails getUserProfileData(String userId) {
        UserProfileTable dbObj = new UserProfileTable();
        UserDetails userDetails = new UserDetails();
        try {
            dbObj.setUserId(userId);
            log.info("Making a get call for userId: {}", userId);
            UserProfileTable userData = dbMapper.load(dbObj);
            userDetails.setUserId(userData.getUserId());
            userDetails.setPassword(userData.getPassword());
            userDetails.setEmailId(userData.getEmailId_mobileNumber().split("_")[0]);
            userDetails.setMobileNumber(userData.getEmailId_mobileNumber().split("_")[1]);
            log.info("Get call successful for user: {}", userDetails.toString());
        } catch (Exception e) {
            log.error("Exception while getting the user profile data: {}",e);
            new DynamoDBException(e.getMessage());
        }
        return userDetails;
    }
}