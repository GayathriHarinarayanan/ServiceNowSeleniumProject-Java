This project automates the steps to create a new incident on ServiceNow instance.  
-----------What was very challenging in this project?--------------------------- 
Handling shadow root DOMs.   
-----------How did I reach shadow root elements in my project?------------------  
I used getShadowRoot() method of SearchContext class, which is available from Selenium 4.  
It can be solved with JavascriptExecutor as well.  
-------------------------------------------------------------------------------------------  
1: Launch the browser and navigate to ServiceNow instance  
2: Login and verify the login is successful  
3: Open the new incident form from the All menu -> Filter Navigator  
4: Create a new incident by filling in the mandatory and required details  
5: Incident should not be created if the caller record is not present    
--------------------------------------------------------------------------------------------
Programming language - Java  
Automation library - Selenium  
Testing framework - TestNG  
Design technique - page Object Model  
Reporting library - Extent Reports  
Project management - Maven
Reading inputs - Properties file
---------------------------------------------------------------------------------------------   
<img width="917" alt="image" src="https://github.com/GayathriHarinarayanan/ServiceNowSeleniumProject-Java/assets/160327903/18e57c9d-8bea-43d9-9989-9707945239a0">
