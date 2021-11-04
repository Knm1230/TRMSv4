package com.revature.services;

import com.revature.models.Request;

import java.util.List;

public interface RequestService
{
    public Request getRequest(int id);
    public List<Request> getRequestsByEmp(int id);
    public List<Request> getDeskByEmp(int id);
    public Request addRequest(Request r);
    public Request updateRequest(Request change);
    public Request deleteRequest(int id);
}
