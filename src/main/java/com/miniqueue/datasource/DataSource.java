package com.miniqueue.datasource;

import com.miniqueue.representation.request.Message;
import com.miniqueue.representation.response.CreateMessageResponse;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sam on 25/8/16.
 */
public interface DataSource {
    CreateMessageResponse send(Message message);

    List<com.miniqueue.representation.response.Message> receive();
}
