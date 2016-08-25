package com.miniqueue.command;

import javax.ws.rs.core.Response;

/**
 * Created by sam on 24/8/16.
 */
public interface Command {
    Response run();
}
