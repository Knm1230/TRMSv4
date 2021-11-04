populateEventTypes();
populateGradingFormats();
getReq();

let currentGFormat;
let eventTypeArr;
let currentEventCoverage;
let currentEmp;
let currentReq;
let reqEmp;
let currentHierarchy;
getEmployee();

async function getReq()
{    
    if(localStorage.getItem("reqId") != null)
    {
        const url = "http://localhost:7000/request/"+ localStorage.getItem("reqId");
        const fetchRequest = await fetch(url);
        const body = await fetchRequest.json();
        currentReq = body;
    }
}


async function populateEventTypes()
{
    url = "http://localhost:7000/reference/events";
    request = await fetch(url);
    body = await request.json();
    body.forEach(function(obj){
        document.getElementById("eventType").innerHTML += "<option value='"+obj.name+"'>"+obj.name+"</option>";
    });
    eventTypeArr = body;
}

async function populateGradingFormats()
{
    url = "http://localhost:7000/reference/grades";
    request = await fetch(url);
    body = await request.json();
    body.forEach(function(obj){
        document.getElementById("gradingFormat").innerHTML += "<option value="+obj.threshold+">"+obj.threshold+"</option>";
    });
    gFormatChange();
}

function calcCoverage()
{
    eventTypeArr.forEach(function(obj){
        if(document.getElementById("eventType").value == obj.name)
        {
            document.getElementById("coverage").value = (obj.coverage * document.getElementById("cost").value);
            currentEventCoverage = obj;
        }
    });
}

function gFormatChange()
{
    if(document.getElementById("gradingFormat").value == "Presentation")
    {
        document.getElementById("gradeDiv").hidden = true;
        currentGFormat = {"id":"1"};
    }else
    {
        document.getElementById("gradeDiv").hidden = false;
        currentGFormat = {"id":"2"};
    }
}

async function populateRequestData()
{
    //make variables of all fields
    let nameF = document.getElementById("nameF");
    let nameL = document.getElementById("nameL");
    let eventType = document.getElementById("eventType");
    let startDate = document.getElementById("startDate");
    let location = document.getElementById("location");
    let description = document.getElementById("description");
    let cost = document.getElementById("cost");
    let coverage = document.getElementById("coverage");
    let gradingFormat = document.getElementById("gradingFormat");
    let grade = document.getElementById("grade");
    let threshold = document.getElementById("cutoff");
    let attatchments = document.getElementById("attatchments");
    let workTime = document.getElementById("workTime");
    let denyJust = document.getElementById("denyJust");

    
    //TODO: set form values by request
    if(currentReq != null && currentReq.empId != currentEmp.id && currentEmp.id == currentHierarchy[2])
    {
        nameF.setAttribute("disabled", "true");
        nameF.setAttribute("style","color:white;"); 
        nameL.setAttribute("disabled", "true");
        nameL.setAttribute("style","color:white;"); 
        startDate.setAttribute("disabled", "true");
        startDate.setAttribute("style","color:white;"); 
        location.setAttribute("disabled", "true");
        location.setAttribute("style","color:white;"); 
        description.setAttribute("disabled", "true");
        description.setAttribute("style","color:white;"); 
        grade.setAttribute("disabled", "true");
        grade.setAttribute("style","color:white;"); 
        threshold.setAttribute("disabled", "true");
        threshold.setAttribute("style","color:white;"); 
        workTime.setAttribute("disabled", "true");
        workTime.setAttribute("style","color:white;"); 
    }else if(currentReq != null && currentReq.empId != currentEmp.id)
    {
        nameF.setAttribute("disabled", "true");
        nameF.setAttribute("style","color:white;"); 
        nameL.setAttribute("disabled", "true");
        nameL.setAttribute("style","color:white;"); 
        startDate.setAttribute("disabled", "true");
        startDate.setAttribute("style","color:white;"); 
        location.setAttribute("disabled", "true");
        location.setAttribute("style","color:white;"); 
        description.setAttribute("disabled", "true");
        description.setAttribute("style","color:white;"); 
        cost.setAttribute("disabled", "true");
        cost.setAttribute("style","color:white;"); 
        grade.setAttribute("disabled", "true");
        grade.setAttribute("style","color:white;"); 
        threshold.setAttribute("disabled", "true");
        threshold.setAttribute("style","color:white;"); 
        workTime.setAttribute("disabled", "true");
        workTime.setAttribute("style","color:white;"); 
    }
    if(currentReq != null)
    {
        console.log(currentReq);
        nameF.value = reqEmp.firstName;
        nameL.value = reqEmp.lastName;
        let createDate = new Date(currentReq.startTime);
        startDate.value = createDate;//ERROR
        location.value = currentReq.requestLocation;
        description.value = currentReq.description;
        cost.value = currentReq.requestCost;
        if(currentReq.grade !=null){
            grade.value = currentReq.grade;
        }
        threshold.value = currentReq.gradeFormat.threshold;
        gradingFormat.value = currentReq.gradeFormat.threshold;
        workTime.value = currentReq.workTime;
        eventType.value = currentReq.eventType.name;

        calcCoverage();
        gFormatChange();
    }

    if(currentReq == null)
    {
        nameF.value = null;
        nameL.value = null;
        startDate.value = null;
        location.value = null;
        description.value = null;
        cost.value = null;
        coverage.value = null;
        grade.value = null;
        threshold.value = "70%";
        attatchments.value = null;
        workTime.value = null;
    }
}

async function getEmployee()
{
    if(localStorage.getItem("empId") == null)
    {
        window.location.replace("./index.html");
    }
    const url = "http://localhost:7000/emp/"+localStorage.getItem("empId");
    const request = await fetch(url);
    const body = await request.json();
    currentEmp = body;
    console.log(body);
    setButtons();
}

function costChange()
{
    calcCoverage();
    if(currentEmp.balance  < document.getElementById("coverage").value)
    {
        if(currentEmp.specialRole.roleName == 'Ben. Coordinator')
        {
            document.getElementById("overChargeDiv").hidden = false;
        }else
        {
            document.getElementById("cost").setAttribute("max",(1/currentEventCoverage.coverage)*currentEmp.balance);
            document.getElementById("cost").value = (1/currentEventCoverage.coverage)*currentEmp.balance;
            calcCoverage();
        }
    }else
    {
        document.getElementById("overChargeDiv").hidden = true;
    }
}

function setButtons()
{
    if(currentReq == null || currentEmp.id == currentReq.empId && currentReq.status != 4)
    {
        document.getElementById("submitBtn").removeAttribute("hidden");
        document.getElementById("resetBtn").removeAttribute("hidden");
        
        document.getElementById("approveBtn").setAttribute("hidden", true);
        document.getElementById("denyShowBtn").setAttribute("hidden", true);
    }else if(currentReq.status != 4)
    {
        document.getElementById("submitBtn").setAttribute("hidden", true);
        document.getElementById("resetBtn").setAttribute("hidden", true);
        
        document.getElementById("approveBtn").removeAttribute("hidden");
        document.getElementById("denyShowBtn").removeAttribute("hidden");
    }else
    {
        document.getElementById("submitBtn").setAttribute("hidden", true);
        document.getElementById("resetBtn").setAttribute("hidden", true);
        
        document.getElementById("approveBtn").setAttribute("hidden", true);
        document.getElementById("denyShowBtn").setAttribute("hidden", true);
    }
    setHierarchy();
}

async function submitRequest()
{

    if(currentReq == null)
    {
        currentReq = consumeForm();

    }

    if(currentReq != null || currentEmp.id == currentReq.empId)
    {
        if(currentReq != null && currentReq.status == 2)
        {//set currentDesk to dep head
            currentReq.currentDesk = currentHierarchy[1];

        }else if(currentReq != null && currentReq.status == 3)
        {//set currentDesk to benco
            currentReq.currentDesk = currentHierarchy[2];

        }else
        {//set status to 1, set currentDesk to supervisor
            currentReq.currentDesk = currentHierarchy[0];
            currentReq.status = 1;
        }
        if(currentReq != null && currentReq.id !=null)
        {//update
            updateRequest();
        }
        else if(currentReq !=null)
        {//new
            newRequest();
        }
    }
}

async function approveRequest()
{
    if(currentReq != null && currentEmp.id != currentReq.empId)
    {
        if(currentEmp.id == currentHierarchy[0])
        {//set currentDesk to dep head
            currentReq.currentDesk = currentHierarchy[1];
            currentReq.status = 2;
            updateRequest();
        }else if(currentEmp.id == currentHierarchy[1])
        {//set currentDesk to benco
            currentReq.currentDesk = currentHierarchy[2];
            currentReq.status = 3;
            updateRequest();
        }else if(currentEmp.id == currentHierarchy[2])
        {
            currentReq.currentDesk = currentReq.empId;
            currentReq.status = 4;
            //TODO: manage balance
            updateRequest();
        }
    }
}

async function denyRequest()
{
    if(currentReq != null)
    {
        currentReq.currentDesk = currentReq.empId;
        //TODO: manage messages
        updateRequest();
    }
}

function showDenyDiv()
{
    document.getElementById("denyReasoning").removeAttribute("hidden");
}

function hideDenyDiv()
{
    document.getElementById("denyReasoning").setAttribute("hidden", true);
}

async function setHierarchy()
{
    if(currentReq != null && currentReq.empId != null)
    {
        const url = "http://localhost:7000/emp/"+ currentReq.empId +"/high";
        const request = await fetch(url);
        const body = await request.json();
        currentHierarchy = body;
    }else
    {
        const url = "http://localhost:7000/emp/"+ currentEmp.id +"/high";
        const request = await fetch(url);
        const body = await request.json();
        console.log(body);
        currentHierarchy = body;
    }

    if(currentReq != null && currentReq.status == 0)
    {
        currentReq.status = 1;
    }
    if(currentReq)
    {
        const url = "http://localhost:7000/emp/"+currentReq.empId;
        const request = await fetch(url);
        const body = await request.json();
        reqEmp = body;
    }
    populateRequestData();
}

function consumeForm()
{
    let eventType = document.getElementById("eventType");
    let startDate = document.getElementById("startDate");
    let location = document.getElementById("location");
    let description = document.getElementById("description");
    let cost = document.getElementById("cost");
    let coverage = document.getElementById("coverage");
    let gradingFormat = document.getElementById("gradingFormat");
    let grade = document.getElementById("grade");
    let threshold = document.getElementById("cutoff");
    let attatchments = document.getElementById("attatchments");
    let workTime = document.getElementById("workTime");
    let denyJust = document.getElementById("denyJust");
    let overCharge = document.getElementById("overcharge")

    if(startDate.value == null ||location.value == null ||description.value == null ||cost.value == null ||workTime.value == null)
    {
        //Skipping validation for time
    }else
    {
        if(overCharge.value != "" && grade.value != "")
        {
            let ownerId = currentReq.empId;
            return buildRequest(
                location.value,
                cost.value,
                "",//file transfer, would be easy if you organized anything here
                description.value,
                workTime.value,
                Date.now(),
                Date.parse(startDate.value),
                grade.value,
                currentGFormat,
                {"id":currentEventCoverage.id},
                ownerId,
                ownerId,
                overCharge.value
            );
        }else if(overCharge.value != "")
        {
            let ownerId = currentReq.empId;
            return buildRequest(
                location.value,
                cost.value,
                "",//file transfer, would be easy if you organized anything here
                description.value,
                workTime.value,
                Date.now(),
                Date.parse(startDate.value),
                null,
                currentGFormat,
                {"id":currentEventCoverage.id},
                ownerId,
                ownerId,
                overCharge.value
            );

        }else if(grade.value != "")
        {
            let ownerId = currentReq.empId;
            return buildRequest(
                location.value,
                cost.value,
                "",//file transfer, would be easy if you organized anything here
                description.value,
                workTime.value,
                Date.now(),
                Date.parse(startDate.value),
                grade.value,
                currentGFormat,
                {"id":currentEventCoverage.id},
                ownerId,
                ownerId,
                null
            );

        }else
        {
            if(currentReq == null)
            {
                let ownerId = currentEmp.id;
                return buildRequest(
                    location.value,
                    cost.value,
                    "",//file transfer, would be easy if you organized anything here
                    description.value,
                    workTime.value,
                    Date.now(),
                    Date.parse(startDate.value),
                    null,
                    currentGFormat,
                    {"id":currentEventCoverage.id},
                    ownerId,
                    ownerId,
                    null
                );
            }else
            {
                let ownerId = currentReq.empId;
                return buildRequest(
                    location.value,
                    cost.value,
                    "",//file transfer, would be easy if you organized anything here
                    description.value,
                    workTime.value,
                    Date.now(),
                    Date.parse(startDate.value),
                    null,
                    currentGFormat,
                    {"id":currentEventCoverage.id},
                    ownerId,
                    ownerId,
                    null
                );
            }
        }
    }

}

function buildRequest(requestLocation, requestCost, filePath, description, workTime, lastUpdateTime, startTime, grade, gradeFormat, eventType, empId, currentDesk, overcharge)
{
    if(grade != null && overcharge != null)
    {
        return {
            "requestLocation":requestLocation,
            "requestCost":requestCost,
            "filePath":filePath,
            "description": description,
            "workTime":workTime,
            "lastUpdateTime":lastUpdateTime,
            "startTime":startTime,
            "grade":grade,
            "gradeFormat":gradeFormat,
            "eventType":eventType,
            "empId":empId,
            "currentDesk":currentDesk,
            "overcharge":overcharge
        }
    }else if(overcharge != null)
    {
        return {
            "requestLocation":requestLocation,
            "requestCost":requestCost,
            "filePath":filePath,
            "description": description,
            "workTime":workTime,
            "lastUpdateTime":lastUpdateTime,
            "startTime":startTime,
            "gradeFormat":gradeFormat,
            "eventType":eventType,
            "empId":empId,
            "currentDesk":currentDesk,
            "overcharge":overcharge
        }

    }else if(grade != null)
    {
        return {
            "requestLocation":requestLocation,
            "requestCost":requestCost,
            "filePath":filePath,
            "description": description,
            "workTime":workTime,
            "lastUpdateTime":lastUpdateTime,
            "startTime":startTime,
            "grade":grade,
            "gradeFormat":gradeFormat,
            "eventType":eventType,
            "empId":empId,
            "currentDesk":currentDesk
        }
    }else
    {
        return {
            "requestLocation":requestLocation,
            "requestCost":requestCost,
            "filePath":filePath,
            "description": description,
            "workTime":workTime,
            "lastUpdateTime":lastUpdateTime,
            "startTime":startTime,
            "gradeFormat":gradeFormat,
            "eventType":eventType,
            "empId":empId,
            "currentDesk":currentDesk
        }
    }
}

async function updateRequest()
{
    console.log(currentReq);
    const url = "http://localhost:7000/request";
    const request = await fetch(url, {"method": "PUT", "body": JSON.stringify(currentReq)});
    const body = await request.json();
    if(body != "{}")
    {
        console.log(body);
        window.location.replace("./dashboard.html");
    }
    console.log(body);
}

async function newRequest()
{
    console.log(currentReq);
    const url = "http://localhost:7000/request";
    const request = await fetch(url, {"method": "POST", "body": JSON.stringify(currentReq)});
    const body = await request.json();
    if(body != "{}")
    {
        console.log(body);
        window.location.replace("./dashboard.html");
    }
    console.log(body);
}