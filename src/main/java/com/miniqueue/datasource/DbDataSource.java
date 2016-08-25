package com.miniqueue.datasource;

import com.miniqueue.dao.MessageDAO;
import com.miniqueue.representation.request.MessageRequest;
import com.miniqueue.representation.response.CreateMessageResponse;
import com.miniqueue.representation.response.MessageResponse;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * Created by sam on 25/8/16.
 */
public class DbDataSource implements DataSource {
    private DBI jdbi;

    public DbDataSource(DBI jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public CreateMessageResponse send(MessageRequest messageRequest) {
        MessageDAO messageDAO = jdbi.onDemand(MessageDAO.class);
        messageDAO.createNewMessage(messageRequest);
        CreateMessageResponse createMessageResponse = new CreateMessageResponse();
        createMessageResponse.setMessageId(messageRequest.getMessageId());
        return createMessageResponse;
    }


    @Override
    public List<MessageResponse> receive() {
        MessageDAO messageDAO = jdbi.onDemand(MessageDAO.class);
        List<MessageResponse> messageResponseList = messageDAO.getMessages();
        messageDAO.updateMessage(messageResponseList);
        return messageResponseList;
    }

    @Override
    public void notifyMessages(List<MessageRequest> messageRequestList) {
        MessageDAO messageDAO = jdbi.onDemand(MessageDAO.class);
        messageDAO.updateMessageAfterProcessed(messageRequestList);
    }


}
