package com.miniqueue.command;

import com.miniqueue.core.MessageQueue;
import com.miniqueue.datasource.DbDataSource;
import com.miniqueue.representation.request.MessageRequest;
import com.miniqueue.representation.response.CreateMessageResponse;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by sam on 24/8/16.
 */
public class ProducerCommand implements Command {

    private final DBI jdbi;

    public ProducerCommand(DBI jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Response run() {
        DbDataSource dbDataSource = new DbDataSource(jdbi);
        MessageQueue messageQueue = MessageQueue.getInstance();
        String messageId = generateUniqueId();
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessageId(messageId);
        CreateMessageResponse createMessageResponse = messageQueue.send(messageRequest, dbDataSource);
        return Response.status(Response.Status.OK).entity(createMessageResponse).build();
    }

    private String generateUniqueId() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
