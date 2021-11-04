package com.revature.services;

import com.revature.models.EventType;
import com.revature.models.GradeFormat;
import com.revature.repositories.ReferenceRepo;

import java.util.List;

public interface ReferenceService
{
    public List<EventType> getEventTypes();
    public List<GradeFormat> getGradeFormats();
}
