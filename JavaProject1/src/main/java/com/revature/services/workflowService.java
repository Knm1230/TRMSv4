package com.revature.services;

import com.revature.models.Message;
import com.revature.models.Overcharge;
import com.revature.models.Request;

public interface workflowService
{

    public Request approveRequest(int empId, int reqId);
    public Request denyRequest(Message msg, int empId);
    public Request addOvercharge(Overcharge over);
}
