# request-proxy-service

To run the project locally : 
--
Simply clone the project and load in into any IDE, then just run RequestProxyServiceApplication.java class
Note : Make sure to configure the port in application.yml, in case the mentioned port is already occupied

1.1. Configuration
--
Usually, we only need to configure the build plugin:









	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.4.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	
	<groupId>com.example</groupId>
	<artifactId>request-proxy-service</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>request-proxy-service</name>
	
	<description>Typical proxy service to replay an HTTPS request </description>
	
	<properties>
		<java.version>11</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<dependency>
		    <groupId>com.google.api-client</groupId>
		    <artifactId>google-api-client</artifactId>
		    <version>1.31.2</version>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>




1.2. Running the Application
--
Now, we can run our example war with two simple commands:

- $ mvn clean package spring-boot:repackage

- $ java -jar target/spring-boot-ops.war

1.3. Inside the War File
--
To understand better how the command mentioned above can run a full server application, we can take a look into our spring-boot-ops.war.

If we uncompress it and peek inside, we find the usual suspects:

META-INF, with the auto-generated MANIFEST.MF
WEB-INF/classes, containing our compiled classes
WEB-INF/lib, which holds our war dependencies and the embedded Tomcat jar files
That's not all though, as there are some folders specific to our fat package configuration:

 WEB-INF/lib-provided, containing external libraries required when running embedded but not required when deploying
org/springframework/boot/loader, which holds the Spring Boot custom class loader — this library is responsible for loading our external dependencies and making them accessible in runtime

1.4. Inside the War Manifest
--
As mentioned before, the Maven Spring Boot plugin finds the main class and generates the configuration needed for running the java command.

The resulting MANIFEST.MF has some additional lines:

- Start-Class: com.fw.https.RequestProxyServiceApplication
- Main-Class: org.springframework.boot.loader.WarLauncher
In particular, we can observe that the last one specifies the Spring Boot class loader launcher to use.

1.5. Inside a Jar File
--
Due to the default packaging strategy, our war packaging scenario doesn't differ much, whether we use the Spring Boot Maven Plugin or not.

To better appreciate the advantages of the plugin, we can try changing the pom packaging configuration to jar and run mvn clean package again.

We can now observe that our fat jar is organized a bit differently from our previous war file:

All our classes and resources folders are now located under 
BOOT-INF/classes
BOOT-INF/lib holds all the external libraries
Without the plugin, the lib folder would not exist, and all the content of BOOT-INF/classes would be located in the root of the package.

1.6. Inside the Jar Manifest
--
Also the MANIFEST.MF has changed, featuring these additional lines:

Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
Spring-Boot-Version: 2.4.2.RELEASE
Main-Class: org.springframework.boot.loader.JarLauncher



Make Request :
---
Use Browser or Postman or some driver program to make requests : 
- 
- Target : https://localhost:7777/response?q=query&client=user1 
- Here I'm replaying :
www.google.com/?q=query

Where, q = query
       client = clientID based on which we will manage the requests
       
       *(Assuming some external application will redirect each unique client with it's unique client ID)
