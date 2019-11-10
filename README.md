# Software Design Project(AlphaNomad)

[![Build Status](https://travis-ci.org/aalphanomad/Software_Design_Project.svg?branch=test2)](https://travis-ci.org/aalphanomad/Software_Design_Project)

[![codecov](https://codecov.io/gh/aalphanomad/Software_Design_Project/branch/test2/graph/badge.svg)](https://codecov.io/gh/aalphanomad/Software_Design_Project)


Are you a lecturer or tutor? AlphaNomad is a web and mobile-based application to make your life easier! AlphaNomad is designed to keep track of the tutoring contributions of each user. This will assist faculty in determining how much is owed to a particular tutor. The application will also allow the admin to assign tutors to courses according to the tutors' preferences. Tutors will be able to download a PDF of their claims. Tutors will be able to upload their transcripts via the application which the admin will be able to view at any time.


Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Client-Side compilation
-------------------------

The generated maven project is using an automatically generated widgetset by default. 
When you add a dependency that needs client-side compilation, the maven plugin will 
automatically generate it for you. Your own client-side customizations can be added into
package "client".

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

Developing a theme using the runtime compiler
-------------------------

When developing the theme, Vaadin can be configured to compile the SASS based
theme at runtime in the server. This way you can just modify the scss files in
your IDE and reload the browser to see changes.

To use the runtime compilation, open pom.xml and comment out the compile-theme 
goal from vaadin-maven-plugin configuration. To remove a possibly existing 
pre-compiled theme, run "mvn clean package" once.

When using the runtime compiler, running the application in the "run" mode 
(rather than in "debug" mode) can speed up consecutive theme compilations
significantly.

It is highly recommended to disable runtime compilation for production WAR files.

Using Vaadin pre-releases
-------------------------

If Vaadin pre-releases are not enabled by default, use the Maven parameter
"-P vaadin-prerelease" or change the activation default value of the profile in pom.xml .
