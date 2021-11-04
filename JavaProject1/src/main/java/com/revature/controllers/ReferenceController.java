package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.EventType;
import com.revature.models.GradeFormat;
import com.revature.services.ReferenceService;
import io.javalin.http.Handler;

import java.util.List;

public class ReferenceController
{
    ReferenceService refS;
    Gson gson = new Gson();

    public ReferenceController(ReferenceService refS){this.refS = refS;}

    public Handler getEventTypes = (ctx) ->{
        List<EventType> typeList = refS.getEventTypes();
        ctx.result((typeList != null)? gson.toJson(typeList):"{}");
    };

    public Handler getGradingFormats = (ctx) ->{
        List<GradeFormat> gradeList = refS.getGradeFormats();
        ctx.result((gradeList != null)?gson.toJson(gradeList):"{}");
    };
}
