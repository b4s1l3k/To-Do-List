# To-Do-List
This repository presents my largest project in Scala — To-Do List. 
During realization I used Play Framework, PostgreSQL, Slick, Swagger, Docker. There are 2 versions: as a full-fledged application and API.
# Main features
The application implements the system of authentication and registration, adding, deleting and editing tasks, clearing completed and active tasks. User data are stored in a database with hashed passwords.
# Main technologies
To implement my application I used: Scala, Play Framework, Slick, PostgreSQL. The application is launched using Docker. The documentation for the API was written using Swagger.
# Main branch
In the "main" branch is a version of my application with a user interface.
# API branch
The "api" branch stores a version of my application in the form of an API. You can go to `localhost:9000/` to view detailed documentation.
# Start-up instructions
To start, navigate to the application or API folder and enter the **`docker-compose up --build`** command with Docker running. This will automatically create a docker image with the application and database and start the docker container. Next, navigate to `localhost:9000/` to display the start page or view documentation for the API.
