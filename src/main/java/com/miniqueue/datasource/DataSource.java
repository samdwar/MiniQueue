package com.miniqueue.datasource;

import com.miniqueue.representation.request.MessageRequest;
import com.miniqueue.representation.response.CreateMessageResponse;
import com.miniqueue.representation.response.MessageResponse;

import java.util.List;

/**
 * Created by sam on 25/8/16.
 */
public interface DataSource {
    CreateMessageResponse send(MessageRequest messageRequest);

    List<MessageResponse> receive();

    void notifyMessages(List<MessageRequest> messageRequestList);
}
