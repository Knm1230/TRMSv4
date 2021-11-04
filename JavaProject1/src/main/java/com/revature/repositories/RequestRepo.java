package com.revature.repositories;

import com.revature.models.Request;

import java.util.List;

public interface RequestRepo
{
    public Request addRequest(Request r);
    public List<Request> getRequestsByEmpId(int id);
    public List<Request> getDeskByEmpId(int id);
    public Request getRequest(int id);
    public Request updateRequest(Request change);
    public Request deleteRequest(int id);
}
