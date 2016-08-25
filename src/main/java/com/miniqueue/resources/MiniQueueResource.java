package com.miniqueue.resources;

import com.miniqueue.command.Command;
import com.miniqueue.command.ConsumerCommand;
import com.miniqueue.command.NotifyProcessedMessageCommand;
import com.miniqueue.command.ProducerCommand;
import com.miniqueue.executor.ConsumerExecutor;
import com.miniqueue.executor.Executor;
import com.miniqueue.executor.ProducerExecutor;
import com.miniqueue.representation.request.MessageRequest;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sam on 24/8/16.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MiniQueueResource {
    final DBI jdbi;

    public MiniQueueResource(DBI jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String greet() {
        return "It works !";
    }

    @POST
    @Path("/send")
    public Response send() {
        Command sendCommand = new ProducerCommand(jdbi);
        Executor executor = new ProducerExecutor();
        Response response = executor.execute(sendCommand);
        return response;
    }

    @GET
    @Path("/receive")
    public Response receive() {
        Command receiveCommand = new ConsumerCommand(jdbi);
        Executor executor = new ConsumerExecutor();
        Response response = executor.execute(receiveCommand);
        return response;
    }

    @POST

    @Path("/notifyProcessed")
    public Response notifyProcessed(List<MessageRequest> messageRequestList) {
        Command sendCommand = new NotifyProcessedMessageCommand(jdbi,messageRequestList);
        Executor executor = new ProducerExecutor();
        Response response = executor.execute(sendCommand);
        return response;
    }
}
