package com.example.homepage;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class MainActivityTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void verifyUser() {

        MainActivity mainActivity = mock(MainActivity.class);

        String response = "sucesss";


        when(mainActivity.verifyUser(response)).thenReturn(true);

        Assert.assertEquals(mainActivity.verifyUser(response), true);
    }
}