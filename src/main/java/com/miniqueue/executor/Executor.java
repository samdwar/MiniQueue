package com.miniqueue.executor;

import com.miniqueue.command.Command;

import javax.ws.rs.core.Response;

/**
 * Created by sam on 24/8/16.
 */
public interface Executor {
    Response execute(Command command);
}
