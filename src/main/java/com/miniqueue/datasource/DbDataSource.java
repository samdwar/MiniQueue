package com.miniqueue.datasource;

import com.miniqueue.dao.MessageDAO;
import com.miniqueue.representation.request.Message;
import com.miniqueue.representation.response.CreateMessageResponse;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * Created by sam on 25/8/16.
 */
public class DbDataSource implements DataSource {
    private DBI jdbi;

    public DbDataSource(DBI jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public CreateMessageResponse send(Message message) {
        MessageDAO messageDAO = jdbi.onDemand(MessageDAO.class);
        messageDAO.createNewMessage(message);
        CreateMessageResponse createMessageResponse = new CreateMessageResponse();
        createMessageResponse.setMessageId(message.getMessageId());
        return createMessageResponse;
    }


    @Override
    public List<com.miniqueue.representation.response.Message> receive() {
        MessageDAO messageDAO = jdbi.onDemand(MessageDAO.class);
        List<com.miniqueue.representation.response.Message> messageList = messageDAO.getMessages();
        for (com.miniqueue.representation.response.Message message : messageList) {
            message.setIsProcessing(1);
        }
        return messageList;
    }


}
