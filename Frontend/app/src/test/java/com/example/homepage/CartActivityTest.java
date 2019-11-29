package com.example.homepage;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartActivityTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void jsonResponse() {

        CartActivity cartActivity = mock(CartActivity.class);

        String response = "response";


        when(cartActivity.jsonResponse(response)).thenReturn(true);
        Assert.assertEquals(cartActivity.jsonResponse(response), true);
    }

    @Test
    public void falseJsonResponse() {

        CartActivity cartActivity = mock(CartActivity.class);

        String response = " ";


        when(cartActivity.jsonResponse(response)).thenReturn(false);
        Assert.assertEquals(cartActivity.jsonResponse(response), false);
    }
}
