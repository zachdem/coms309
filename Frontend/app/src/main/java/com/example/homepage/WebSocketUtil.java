package com.example.homepage;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.util.GlobalAppInfo;
import com.util.VolleyCallback;

import java.io.IOException;

public class WebSocketUtil {

    private static WebSocket ws = null;

    public static void connectWebSocket(final VolleyCallback callback){
        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);

        try {
            ws = factory.createSocket("ws://" + GlobalAppInfo.serverName + ":8080/userhome");

            ws.addListener(new WebSocketAdapter() {

                @Override
                public void onTextMessage(WebSocket websocket, String message) throws Exception {
                    callback.onVolleyResponse(message);
                }
            });

            ws.connectAsynchronously();
            Thread.sleep(500);
        }
        catch (IOException e) {
        e.printStackTrace();
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
    }

    public static void sendText(String text){
        ws.sendText(text);
    }

    public static void disconnectWebSocket(){
        ws.disconnect();
    }

}
