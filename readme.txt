username=admin,pgupta & password=admin for ROLE_ADMIN.



TestNg is also Integrated

Liquibase is also integrated

Bootstrap is also implemented

Spring security with salt is also implement

Spring Batch job is also implemented.
1. BatchJobs:
    userJob : Reads the data from database and write it in file in temp location
    userCSVJob : Reads the data from csv file and write the data in database
2. How to debug  the job:
    a. To debug the job we have added below jvm args in jobbuild.xml right after the job args. By adding below args, when execute the job from ant, job will start listening the port 5556 
       <jvmarg value="-Xdebug" />
	   <jvmarg value="-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5556" /> 
	b. Create Port 5556 for the job
	   Navigate to Debug Configuration --> double click on the Remote Java Application
	   In the Name field, add job name and in port field add the port which is mentioned in jvm args
	   In the Source tab of same window, add the src folder path.

Problems in the project :
1. Although sitemesh for decorator is implemented but its not working correctly.

What to add :
1. To add the unit testcases for updating the userpassword
2. To create a rest service and rest client.
3. To add the aspectj and custom aspect annotation
