package com.revature.repositories;

import com.revature.models.EventType;
import com.revature.models.GradeFormat;

import java.util.List;

public interface ReferenceRepo
{
    public List<EventType> getEvents();
    public List<GradeFormat> getGradeFormats();
}
