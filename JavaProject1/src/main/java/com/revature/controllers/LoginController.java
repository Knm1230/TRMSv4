package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Employee;
import com.revature.models.LoginPacket;
import com.revature.repositories.EmployeeRepo;
import com.revature.services.EmployeeService;
import io.javalin.http.Handler;

public class LoginController
{
    EmployeeService es;
    Gson gson = new Gson();

    public LoginController(EmployeeService es){this.es = es;}

    //TEMP
    public Handler login = (ctx) ->
    {
        LoginPacket lp = gson.fromJson(ctx.body(), LoginPacket.class);
        Employee e = es.login(lp.getUsername(), lp.getPassword());
        if(e!=null)
        {
            ctx.result(gson.toJson(e.getId()));
        }
        else
        {
            ctx.status(404);
        }
    };
}
