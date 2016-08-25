package com.miniqueue.dao;

import com.miniqueue.representation.request.Message;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;


/**
 * Created by sam on 24/8/16.
 */
public interface MessageDAO {
    public static final String TABLE = "message";

    @SqlUpdate("create table " + TABLE + "(message_id varchar(256) primary key, is_processed int, is_processing int)")
    void createMessageTable();

    @SqlUpdate("insert into " + TABLE + " (message_id,is_processed,is_processing) values (:message.messageId, :message.isProcessed, :message.isProcessing)")
    void createNewMessage(@BindBean("message") Message message);


    @RegisterMapper(com.miniqueue.representation.response.Message.MessageMapper.class)
    @SqlQuery("select * from " + TABLE)
    List<com.miniqueue.representation.response.Message> getMessages();

}
