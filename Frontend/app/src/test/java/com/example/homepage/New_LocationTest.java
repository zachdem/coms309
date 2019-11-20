package com.example.homepage;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class New_LocationTest {

    @Test
    public void checkURL() {
        New_Location new_location = mock(New_Location.class);

        String url = "\"http://coms-309-ks-6.misc.iastate.edu:8080/clydes";

        when(new_location.checkURL(url)).thenReturn(true);
        Assert.assertEquals(new_location.checkURL(url), true);
    }
}