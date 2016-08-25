package com.miniqueue.core;

import com.miniqueue.datasource.DataSource;
import com.miniqueue.representation.request.MessageRequest;
import com.miniqueue.representation.response.CreateMessageResponse;
import com.miniqueue.representation.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sam on 25/8/16.
 */
@Slf4j
public class MessageQueue {

    private static int capacity = 1024;

    public static MessageQueue getInstance() {
        if (instance == null) {
            synchronized (MessageQueue.class) {
                if (instance == null) {
                    instance = new MessageQueue(capacity);
                    return instance;
                } else return instance;
            }
        }
        return instance;
    }

    public static volatile MessageQueue instance;
    private Map<String, MessageRequest> messageMap;

    private BlockingQueue<MessageRequest> queue;

    private MessageQueue(int capacity) {
        this.capacity = capacity;
        messageMap = new HashMap<>();
        queue = new LinkedBlockingQueue<>(capacity);
    }

    public synchronized CreateMessageResponse send(MessageRequest messageRequest, DataSource dataSource) {
        try {
            queue.put(messageRequest);
            messageMap.put(messageRequest.getMessageId(), messageRequest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dataSource.send(messageRequest);
    }

    public synchronized List<MessageResponse> receive(DataSource dataSource) {
        List<MessageResponse> messageResponseList = dataSource.receive();
        for (MessageResponse messageResponse : messageResponseList) {
            if (messageMap.containsKey(messageResponse.getMessageId())) {
                queue.remove(messageMap.get(messageResponse.getMessageId()));
                messageMap.remove(messageResponse.getMessageId());
            }
        }
        return messageResponseList;
    }

    public synchronized void notifyProcessedMessages(List<MessageRequest> messageRequestList, DataSource dataSource) {
        dataSource.notifyMessages(messageRequestList);
    }
}
