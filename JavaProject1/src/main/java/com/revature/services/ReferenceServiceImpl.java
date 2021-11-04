package com.revature.services;

import com.revature.models.EventType;
import com.revature.models.GradeFormat;
import com.revature.repositories.ReferenceRepo;

import java.util.List;

public class ReferenceServiceImpl implements ReferenceService
{
    ReferenceRepo refR;

    public ReferenceServiceImpl(ReferenceRepo refR){this.refR = refR;}

    @Override
    public List<EventType> getEventTypes()
    {
        return refR.getEvents();
    }

    @Override
    public List<GradeFormat> getGradeFormats()
    {
        return refR.getGradeFormats();
    }
}
