newSession()

function newSession()
{
    localStorage.clear;
}

async function login()
{
    let username = ""
    username = document.getElementById("username").value;
    let password = ""
    password = document.getElementById("password").value;

    const myBody = {"username": username, "password": password};//NOT SECURE AT ALL, JANKY AND GROSS
    const url = `http://localhost:7000/login`;
    const response = await fetch(url, {method: "POST", body: JSON.stringify(myBody)});

    if(response.status == 200)
    {
        const body = await response.json();
        // console.log(body);
        // console.log(body);
        localStorage.setItem("empId", body);
        window.location.replace("./dashboard.html");
    }
    else
    {
        document.getElementById("loginErr").setAttribute("display", "block");
        document.getElementById("username").value = "";
        document.getElementById("password").value = "";
    }
}