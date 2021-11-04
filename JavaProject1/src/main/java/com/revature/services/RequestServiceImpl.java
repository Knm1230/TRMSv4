package com.revature.services;

import com.revature.models.Request;
import com.revature.repositories.RequestRepo;

import java.util.List;

public class RequestServiceImpl implements RequestService
{
    RequestRepo rr;

    public RequestServiceImpl(RequestRepo rr){this.rr = rr;}
    @Override
    public Request getRequest(int id)
    {
        return rr.getRequest(id);
    }

    @Override
    public List<Request> getRequestsByEmp(int id)
    {
        return rr.getRequestsByEmpId(id);
    }

    @Override
    public List<Request> getDeskByEmp(int id)
    {
        return rr.getDeskByEmpId(id);
    }

    @Override
    public Request addRequest(Request r)
    {
        return rr.addRequest(r);
    }

    @Override
    public Request updateRequest(Request change)
    {
        return rr.updateRequest(change);
    }

    @Override
    public Request deleteRequest(int id)
    {
        return rr.deleteRequest(id);
    }
}
