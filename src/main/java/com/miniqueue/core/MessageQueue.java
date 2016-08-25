package com.miniqueue.core;

import com.miniqueue.datasource.DataSource;
import com.miniqueue.representation.request.Message;
import com.miniqueue.representation.response.CreateMessageResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sam on 25/8/16.
 */
@Slf4j
public class MessageQueue {

    private int capacity;

    public static MessageQueue getInstance() {
        if (instance == null) {
            synchronized (MessageQueue.class) {
                if (instance == null) {
                    instance = new MessageQueue(4);
                    return instance;
                } else return instance;
            }
        }
        return instance;
    }

    public static volatile MessageQueue instance;


    private BlockingQueue<Message> queue;

    private MessageQueue(int capacity) {
        this.capacity = capacity;
        queue = new LinkedBlockingQueue<>(capacity);
    }

    public synchronized CreateMessageResponse send(Message message, DataSource dataSource) {
        try {
            queue.put(message);
            log.info("queue size = " + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dataSource.send(message);
    }

    public synchronized List<com.miniqueue.representation.response.Message> receive(DataSource dataSource) {
        List<com.miniqueue.representation.response.Message> messageList = dataSource.receive();
        try {
            for (int i = 0; i < messageList.size(); i++) {
                queue.take();
            }
        } catch (InterruptedException e) {

        }
        return messageList;
    }
}
