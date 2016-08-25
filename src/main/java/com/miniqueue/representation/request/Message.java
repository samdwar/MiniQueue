package com.miniqueue.representation.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
