package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Request;
import com.revature.services.RequestService;
import com.revature.util.TRMSLogger;
import io.javalin.http.Handler;

import java.util.List;

public class RequestController
{
    RequestService rs;
    Gson gson = new Gson();

    public RequestController(RequestService rs){this.rs = rs;}

    public Handler getRequest = (ctx) ->{
        TRMSLogger.logger.info("GetRequest executed");
        int id = Integer.parseInt(ctx.pathParam("id"));
        Request request = rs.getRequest(id);
        if (request != null)
        {
            ctx.result(gson.toJson(request));
        }
        else
        {
            ctx.status(404);
            ctx.result("{}");
        }

    };

    public Handler addRequest = (ctx) ->{
        Request r = gson.fromJson(ctx.body(), Request.class);
        r = rs.addRequest(r);
        ctx.result((r != null)?gson.toJson(r):"{}");
    };

    public Handler updateRequest = (ctx) ->{
        Request r = gson.fromJson(ctx.body(), Request.class);
        r = rs.updateRequest(r);
        ctx.result((r != null)?gson.toJson(r):"{}");
    };

    public Handler getEmpRequests = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Request> rList = rs.getRequestsByEmp(id);
        if(rList !=null)
        {
            ctx.result(gson.toJson(rList));
        }
        else
        {
            ctx.status(404);
        }
    };

    public Handler getEmpDesk = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Request> rList = rs.getDeskByEmp(id);
        if(rList !=null)
        {
            ctx.result(gson.toJson(rList));
        }
        else
        {
            ctx.status(404);
        }
    };
}
