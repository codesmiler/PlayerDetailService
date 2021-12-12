package com.example.playerdetailservice.model.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "UserProfileData")
public class UserProfileTable {
    private String userId;
    private String password;
    private String emailId_mobileNumber;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId_mobileNumber() {
        return emailId_mobileNumber;
    }

    public void setEmailId_mobileNumber(String emailId_mobileNumber) {
        this.emailId_mobileNumber = emailId_mobileNumber;
    }
}
