package com.example.homepage;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserSignUpActivityTest {

    @Test
    public void verifySignupInput() {

        UserSignUpActivity userSignUpActivity = mock(UserSignUpActivity.class);

        String isuID = "0000000";
        String rN = "778975";
        String acctN = "123124123";
        String firstN = "bobby";
        String lastN = "llamma";
        String userN = "bllama";
        String passW = "baconw";
        String net = "bllama";

        when(userSignUpActivity.verifySignupInput(isuID,rN,acctN,firstN,lastN,userN,passW,net)).thenReturn(true);

        Assert.assertEquals(userSignUpActivity.verifySignupInput(isuID,rN,acctN,firstN,lastN,userN,passW,net), true);

    }
}