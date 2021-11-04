package com.revature.unittesting;

import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.repositories.EmployeeRepo;
import com.revature.repositories.EmployeeRepoImpl;
import com.revature.repositories.RequestRepo;
import com.revature.repositories.RequestRepoImpl;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.RequestService;
import com.revature.services.RequestServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepoTests
{
    public static Request r;
    public static Employee e;

    @BeforeAll
    static void addTestData()
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        RequestRepo rr = new RequestRepoImpl();
        RequestService rs = new RequestServiceImpl(rr);

        e = new Employee();
        e.setBenCo(5);
        e.setDepHead(5);
        e.setSupervisor(5);

        e = es.addEmployee(e);

        r = new Request();
        r.setEmpId(e.getId());
        r.setCurrentDesk(e.getId());
        r = rs.addRequest(r);

    }

    @Test
    public void getData()
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        RequestRepo rr = new RequestRepoImpl();
        RequestService rs = new RequestServiceImpl(rr);

        Assertions.assertEquals(e.getId(), es.getEmployee(e.getId()).getId());
        Assertions.assertEquals(r.getId(), rs.getRequest(r.getId()).getId());
    }

    @Test
    public void updateData()
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        RequestRepo rr = new RequestRepoImpl();
        RequestService rs = new RequestServiceImpl(rr);

        e.setFirstName("name");
        es.updateEmployee(e);

        r.setRequestLocation("location");
        rs.updateRequest(r);

        Assertions.assertEquals(e.getFirstName(), es.getEmployee(e.getId()).getFirstName());
        Assertions.assertEquals(r.getRequestLocation(), rs.getRequest(r.getId()).getRequestLocation());

    }

    @Test
    public void checkEmpLists()
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        RequestRepo rr = new RequestRepoImpl();
        RequestService rs = new RequestServiceImpl(rr);

        List<Integer> f = es.getHierarchy(e.getId());
        Assertions.assertEquals(e.getSupervisor(), f.get(0));
        List<Employee> g = es.getAllEmployees();
        Assertions.assertEquals(es.getEmployee(e.getId()).getId(), g.get(g.size()-1).getId());
    }

    @Test
    public void checkReqLists()
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        RequestRepo rr = new RequestRepoImpl();
        RequestService rs = new RequestServiceImpl(rr);

        List<Request> s = rs.getRequestsByEmp(e.getId());
        Assertions.assertEquals(r.getId(), s.get(0).getId());
        List<Request> t = rs.getDeskByEmp(e.getId());
        Assertions.assertEquals(r.getId(), t.get(0).getId());

    }

    @AfterAll
    static void delData()
    {
        EmployeeRepo er = new EmployeeRepoImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        RequestRepo rr = new RequestRepoImpl();
        RequestService rs = new RequestServiceImpl(rr);

        rs.deleteRequest(r.getId());
        es.deleteEmployee(e.getId());
    }

}
