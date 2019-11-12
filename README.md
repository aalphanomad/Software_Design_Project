# Software Design Project(AlphaNomad)

[![Build Status](https://travis-ci.org/aalphanomad/Software_Design_Project.svg?branch=VBranch)](https://travis-ci.org/aalphanomad/Software_Design_Project)

Primary Codebase:   [![codecov](https://codecov.io/gh/aalphanomad/Software_Design_Project/branch/VBranch/graph/badge.svg)](https://codecov.io/gh/aalphanomad/Software_Design_Project)

PHP API:  [![Coverage Status](https://coveralls.io/repos/github/aalphanomad/Software_Design_Project/badge.svg?branch=php_test)](https://coveralls.io/github/aalphanomad/Software_Design_Project?branch=php_test)

# About The Project

Are you a lecturer or tutor who dreads the paper-based administrative process of handling tutoring claims? Then our system, AlphaNomad, is the solution to your problems which is a web and mobile-based application to make your life easier, while saving a few trees in the process. 

# Amazing functionality offered by the AlphaNomad application:

The application is carefully curated to transform the endless troubles of recording your claims and searching for supervisor signatures to a tech-savvy environment. 

The application comprises of all the decision-making parties involved in the tutoring process:

# 1) The Tutor:
Tutors will be able to register and apply for courses which they would like to tutor. Once they have been accepted to tutor a specific course, they will have an exclusive account which keeps track of all their tutoring activity.  Tutors may record the details of each tutoring session with the number of hours they tutored. These tuition sessions will appear in their profile history which will automatically give them access to a completed claims form. This claims form may be downloaded as a PDF and handed in for processing and payment. 

# 2) The Lecturer:
Lecturers will have the capability of viewing the courses that they are responsible for, along with the tutors that are associated to each course. They will have the luxury of emailing each user within a course to relay important information. They will also have the responsibility of verifying the claims of each tutor. 

# 3) The Administrator:
The administrator has the authority to accept and reject applications from tutors to tutor a given course. They will then have the ability to change the roles of users such as promoting a tutor to a lecturer. Admins can also create, edit and remove any courses that are offered by the faculty. They will be able to view all courses that are offered along with all users belonging to each course.

# 4) The Super-admin:
Super-admins have the power to grant admin privileges to lecturers as well as the authority to reset the password of any user. They also possess the ability to do everything that an admin can do.    

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"
