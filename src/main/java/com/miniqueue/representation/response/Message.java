package com.miniqueue.representation.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sam on 24/8/16.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String messageId;
    private int isProcessed;
    private int isProcessing;

    public static class MessageMapper implements ResultSetMapper<Message> {

        @Override
        public Message map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

            return new Message(resultSet.getString("message_id"),
                    resultSet.getInt("is_processed"),
                    resultSet.getInt("is_processing"));
        }
    }

}
