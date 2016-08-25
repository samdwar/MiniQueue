package com.miniqueue.command;

import com.miniqueue.core.MessageQueue;
import com.miniqueue.datasource.DbDataSource;
import com.miniqueue.representation.request.MessageRequest;
import com.miniqueue.representation.response.CreateMessageResponse;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sam on 25/8/16.
 */
public class NotifyProcessedMessageCommand implements Command {

    private final DBI jdbi;
    private List<MessageRequest> messageRequestList;

    public NotifyProcessedMessageCommand(DBI jdbi, List<MessageRequest> messageRequestList) {
        this.jdbi = jdbi;
        this.messageRequestList = messageRequestList;
    }

    @Override
    public Response run() {
        DbDataSource dbDataSource = new DbDataSource(jdbi);
        MessageQueue messageQueue = MessageQueue.getInstance();
        messageQueue.notifyProcessedMessages(messageRequestList, dbDataSource);
        return Response.status(Response.Status.OK).build();
    }
}
