package com.revature.app;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.ReferenceController;
import com.revature.controllers.RequestController;
import com.revature.repositories.*;
import com.revature.services.*;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class App
{
    public static void main(String[] args)
    {
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins);

        createLoginEndpoints(app);
        createRequestEndpoints(app);
        createEmployeeEndpoints(app);
        createReferenceEndpoints(app);

        app.start(7000);
    }

    private static void createLoginEndpoints(Javalin app)
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        LoginController lc = new LoginController(es);

        app.post("/login", lc.login);
    }

    public static void createEmployeeEndpoints(Javalin app)
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        EmployeeController ec = new EmployeeController(es);

        app.get("/emp/:id", ec.getEmp);
        app.get("/emp/:id/high", ec.getHierarchy);
        app.put("/emp/:id", ec.updateEmp);
    }

    public static void createRequestEndpoints(Javalin app)
    {
        RequestRepo rr = new RequestRepoImpl();
        RequestService rs = new RequestServiceImpl(rr);
        RequestController rc = new RequestController(rs);

        app.get("/request/:id", rc.getRequest);
        app.post("/request", rc.addRequest);
        app.put("/request", rc.updateRequest);
        app.get("/emp/:id/requests", rc.getEmpRequests);
        app.get("emp/:id/desk", rc.getEmpDesk);

    }

    public static void createReferenceEndpoints(Javalin app)
    {
        ReferenceRepo refR = new ReferenceRepoImpl();
        ReferenceService refS = new ReferenceServiceImpl(refR);
        ReferenceController refC = new ReferenceController(refS);

        app.get("/reference/events", refC.getEventTypes);
        app.get("/reference/grades", refC.getGradingFormats);
    }
}
