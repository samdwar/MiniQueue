package com.miniqueue.command;

import com.miniqueue.core.MessageQueue;
import com.miniqueue.datasource.DbDataSource;
import com.miniqueue.representation.response.MessageResponse;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sam on 24/8/16.
 */
public class ConsumerCommand implements Command {
    private final DBI jdbi;

    public ConsumerCommand(DBI jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Response run() {
        DbDataSource dbDataSource = new DbDataSource(jdbi);
        MessageQueue messageQueue = MessageQueue.getInstance();
        List<MessageResponse> messageResponseList = messageQueue.receive(dbDataSource);
        return Response.status(Response.Status.OK).entity(messageResponseList).build();
    }
}
