package com.gamecenter.gamecenter.websocketmanage;

import android.text.TextUtils;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WebSocketManage {
    private static WebSocketManage mInstance;
    private final String TAG = "WebSocketManage";
    private String url;

    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String DEF_URL = "ws://192.168.0.127:8777/";

    private WebSocket ws;
    private WsListener wsListener;

    private Logger logger;


    public static WebSocketManage getInstance(){
        if(mInstance == null){
            synchronized (WebSocketManage.class){
                if(mInstance == null){
                    mInstance = new WebSocketManage();
                }
            }
        }
        return mInstance;
    }

    public void init()
    {
        try {
            String configUrl = "";
            configUrl = "ws://39.107.234.248:8777/";
            url = TextUtils.isEmpty(configUrl) ? DEF_URL : configUrl;
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT) //ws地址，和设置超时时间
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(wsListener = new WsListener())//添加回调监听
                    .connectAsynchronously();//异步连接

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendTextMsg(String msg)
    {
        if(!ws.isOpen()){
            init();
            ws.sendText(msg);
        }else {
            ws.sendText(msg);
        }
    }

    public void disconnect(){
        if(ws != null)
            ws.disconnect();
    }

    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            // 处理收到的消息
            DealServerMsg.dealServerWsMsg(text);
            //logger.info(text);

        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            logger.info("连接成功");
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            logger.warning("连接错误：" + exception.getMessage());
        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            logger.warning("断开连接");
        }

    }
}
