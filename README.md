# attendanceWebAppWar
Spring Boot attendance system web application

# Project in progress:
-User login web app with admin page
-Reports 
-Filters

# Technologies

Technologies: Spring Boot Web, Spring Data JPA & Hibernate, PostgreSQL
Software programs: IntelliJ IDEA Ultimate, PgAdmin4
Deployed: Database Amazon RDS (PostgreSQL)

# Requirements
JDK 17
Gradle

# Set up PostgresSQL
Configure database according to application.properties 

# Viewing the running application
To view the running application, visit http://localhost:8080 in your browser

# Database diagram:
![image](https://github.com/kysaadatlie/attendanceWebAppWar/assets/94738149/d4b52da2-e5c1-49f7-bf15-14ad20a5b8bf)

Relationships on the diagram:
Gender/Department <-> PersonInfo (One-To-Many)
PersonInfo <-> Staff/Faculty (One-To-One)
Staff <-> Record (One-To-Many)
Status <-> Record (One-To-Many)
Faculty <-> Room (One-To-One)

# CRUD API endpoints
Department:
http://localhost:8080/department (get all department)
http://localhost:8080/department/{id} (get department by id)
http://localhost:8080/department/create (create department)
http://localhost:8080/department/{id}/update (update department)
http://localhost:8080/department/{id}/delete (delete department)

Room:
http://localhost:8080/room (get all room)
http://localhost:8080/room/{id} (get room by id)
http://localhost:8080/room/create (create room)
http://localhost:8080/room/{id}/update (update room)
http://localhost:8080/room/{id}/delete (delete room)

PersonInfo:
http://localhost:8080/person_info (get all person_info)
http://localhost:8080/department/{departmentId}/person_info (get person_info by departmentId)
http://localhost:8080/department/{departmentId}/person_info/{personInfoId} (get person_info by personInfoId)
http://localhost:8080/department/{departmentId}/person_info/create (create person_info)
http://localhost:8080/department/{departmentId}/person_info/{personInfoId}/update (update person_info)
http://localhost:8080/department/{departmentId}/person_info/{personInfoId}/delete (delete person_info)

Staff:
http://localhost:8080/staff (get all staff)
http://localhost:8080/person_info/{personInfoId}/staff (get staff by personInfoId)
http://localhost:8080/person_info/{personInfoId}/staff/{staffId} (get staff by staffId)
http://localhost:8080/person_info/{personInfoId}/staff/create (create staff)
http://localhost:8080/person_info/{personInfoId}/staff/{staffId}/update (update staff)
http://localhost:8080/person_info/{personInfoId}/staff/{staffId}/delete (delete staff)

Faculty:
http://localhost:8080/faculty (get all faculty)
http://localhost:8080/person_info/{personInfoId}/faculty (get faculty by personInfoId)
http://localhost:8080/room/{roomId}/faculty (get faculty by roomId)
http://localhost:8080/person_info/{personInfoId}/room/{roomId}/faculty/{facultyId} (get faculty by facultyId)
http://localhost:8080/person_info/{personInfoId}/room/{roomId}/faculty (create faculty)
http://localhost:8080/person_info/{personInfoId}/room/{roomId}/faculty/{facultyId}/update (update faculty)
http://localhost:8080/person_info/{personInfoId}/room/{roomId}/faculty/{facultyId}/delete (delete faculty)

Record:
http://localhost:8080/record (get all record)
http://localhost:8080/staff/{staffId}/record (get record by staffId)
http://localhost:8080/staff/{staffId}/record/{recordId} (get record by recordId)
http://localhost:8080/staff/{staffId}/record/create (create record)
http://localhost:8080/staff/{staffId}/record/{recordId}/update (update record)
http://localhost:8080/staff/{staffId}/record/{recordId}/delete (delete record)

# Tables in PostgreSQL (deployed on AWS RDS)
![image](https://github.com/kysaadatlie/attendanceWebAppWar/assets/94738149/c620dc81-3552-4ae2-810a-4b5a1ab726aa)

![image](https://github.com/kysaadatlie/attendanceWebAppWar/assets/94738149/7ead4b83-08dd-4301-8046-203670fed099)

(P.S. I didn't deploy the application to AWS EC2 due to problems with AWS tomcat):
![image](https://github.com/kysaadatlie/attendanceWebAppWar/assets/94738149/0bedc36f-ec6e-4853-a487-b9cff00f88fa)

