package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.services.EmployeeService;
import io.javalin.http.Handler;

import java.util.List;


public class EmployeeController
{
    EmployeeService es;
    Gson gson = new Gson();

    public EmployeeController(EmployeeService es){this.es = es;}

    public Handler getEmp = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee e = es.getEmployee(id);
        if(e!=null)
        {
            ctx.result(gson.toJson(e));
        }
        else
        {
            ctx.status(404);
        }
    };

    public Handler updateEmp = (ctx) ->{
        Employee e = gson.fromJson(ctx.body(), Employee.class);
        e = es.updateEmployee(e);
        ctx.result((e != null)?gson.toJson(e):"{}");
    };

    public Handler getHierarchy = (ctx) ->{
      int id = Integer.parseInt(ctx.pathParam("id"));
      List<Integer> eList = es.getHierarchy(id);
      if(eList !=null)
      {
          ctx.result(gson.toJson(eList));

      }
      else
      {
          ctx.status(404);
      }
    };
}
