package com.miniqueue.dao;

import com.miniqueue.representation.request.MessageRequest;
import com.miniqueue.representation.response.MessageResponse;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;


/**
 * Created by sam on 24/8/16.
 */
public interface MessageDAO {
    public static final String TABLE = "message";

    @SqlUpdate("create table " + TABLE + "(message_id varchar(256) primary key, is_processed int, is_processing int)")
    void createMessageTable();

    @SqlUpdate("insert into " + TABLE + " (message_id,is_processed,is_processing) values (:messageRequest.messageId, 0,0)")
    void createNewMessage(@BindBean("messageRequest") MessageRequest messageRequest);


    @RegisterMapper(MessageResponse.MessageMapper.class)
    @SqlQuery("select * from " + TABLE + " where is_processed =0 or is_processing=0 and SECOND(UNIX_TIMESTAMP(NOW()) - UNIX_TIMESTAMP(processing_time))>=30 limit 10")
    List<MessageResponse> getMessages();

    @SqlBatch("update " + TABLE + " set  is_processed =0, is_processing =1 where message_id =:messageResponseList.messageId")
    void updateMessage(@BindBean("messageResponseList") List<MessageResponse> messageResponseList);

    @SqlBatch("delete from " + TABLE + " where message_id =:messageRequestList.messageId")
    void updateMessageAfterProcessed(@BindBean("messageRequestList") List<MessageRequest> messageRequestList);
}
