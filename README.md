# TRMSv4
The Tuition Reimbursement Management System will manage the process of reimbursing employees for a variety of approved trainings, courses, and technical certifications. 
All employees can login, view their requests, and requests pending their action, and submit a new request. Supervisors, Department heads, and Benefits Coordinators can approve or deny requests that await their action.

# Technologies Used: 
1. Back-End: Java, PostgreSQL, Hibernate
2. Middle: Javalin
3. Front-End: JavaScript, HTML, CSS
4. Testing: JUnit, Postman

# Setup
The DBGEN.sql file can be run on a PostgreSQL database to set up required tables and populate reference tables. The Java project can run in an IDE, and 
the Front-End must be run locally to ensure a connection to the Java project.

# Useage
Upon entering correct credentials at the login screen, a user is presented with their dashboard, showing their remaining reimbursement available for the year, as well as a table of the requests on their"desk" that await their response, and a table of the requests they have generated. Upon clicking the "new request" button or clicking the linked request id, they are brought to the new request form(with values pre-populated, and appropriate input fields disabled if viewing a request that is not theirs), if they are generating a new request the employee can input the required information and submit it, for it to be passed to the appropriate supervisor or manager. If the employee is a supervisor, department head, or benefits coordinator, they can either approve or reject a request. In order for it to be considered approved, a request must be approved by a benefits coordinator.
