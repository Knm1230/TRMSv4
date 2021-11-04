populateAwaiting()
populateMyRequests()

async function populateAwaiting()
{
    // const params = new URLSearchParams(window.location.search);
    // const id = params.get("id");

    const id = localStorage.getItem("empId")

    url = "http://localhost:7000/emp/"+ id + "/desk"

    const request = await fetch(url);

    const body = await request.json();

    //TODO: implement loading of requests from array, then new endpoint for pending
    body.forEach(element => {
        if(element.empId != element.currentDesk)
        {
            console.log(element);
            let createDate = new Date(element.startTime);
            document.getElementById("awaitBody").innerHTML += "<tr><th scope='row'><a onclick='genLink("+element.id+")'>"+element.id+"</a></th><td>"+createDate.getDate()+"-"+createDate.getMonth()+"-"+createDate.getFullYear()+"</td><td>"+(element.requestCost*element.eventType.coverage)+"</td><td>"+getStatusName(element)+"</td><td>"+element.messageList+"</td></tr>"
        }
    });
}

async function populateMyRequests()
{
    // const params = new URLSearchParams(window.location.search);
    // const id = params.get("id");

    const id = localStorage.getItem("empId")

    url = "http://localhost:7000/emp/"+ id + "/requests"

    const request = await fetch(url);

    const body = await request.json();

    let pendingReimbursement = 0;
    //TODO: implement loading of requests from array, then new endpoint for pending
    body.forEach(element => {
        console.log(element);
        pendingReimbursement+=element.requestCost*element.eventType.coverage;
        let createDate = new Date(element.startTime);
        document.getElementById("currentBody").innerHTML += "<tr><th scope='row'><a onclick='genLink("+element.id+")'>"+element.id+"</a></th><td>"+createDate.getDate()+"-"+createDate.getMonth()+"-"+createDate.getFullYear()+"</td><td>"+(element.requestCost*element.eventType.coverage)+"</td><td>"+getStatusName(element)+"</td><td>"+element.messageList+"</td></tr>"
    });
    updateReimbursement(pendingReimbursement, id)
}

function genLink(id)
{
    localStorage.setItem("reqId",id);
    window.location.href = "./newTRForm.html";
}

function getStatusName(element)
{
    if(element.status == 0)
    {
        return "created";
    }else if(element.status == 1)
    {
        return "Awaiting Supervisor";
    }else if(element.status == 2)
    {
        return "Awaiting Dep. Head";
    }else if(element.status == 3)
    {
        return "Awaiting Ben. Co.";
    }else if(element.status == 4)
    {
        return "Approved";
    }
}

function newReq()
{
    localStorage.removeItem("reqId");
    window.location.replace("./newTRForm.html");
}

async function updateReimbursement(pendingReimbursement, id)
{
    const url = "http://localhost:7000/emp/"+id;
    const request1 = await fetch(url);
    const body1 = await request1.json();

    let change = body1;
    change.balance = (1000 - pendingReimbursement);

    const request2 = await fetch(url, {"method": "PUT", "body": JSON.stringify(change)});
    const body2 = await request2.json();

    document.getElementById("balance").innerHTML = "Remaining Balance: "+body2.balance;
}