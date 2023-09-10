# To-Do-List
This repository presents my largest project in Scala — To-Do List. 
During realization I used Play Framework, PostgreSQL, Slick, Swagger, Docker. There are 2 versions: as a full-fledged application and API.
# Main features
The application implements the system of authentication and registration, adding, deleting and editing tasks, clearing completed and active tasks. User data are stored in a database with hashed passwords.
# Main technologies
To implement my application I used: Scala, Play Framework, Slick, PostgreSQL. The application is launched using Docker. The documentation for the API was written using Swagger.
# Main branch
In the "main" branch is a version of my application with a user interface.

**Methods of the Main Page**

- `GET localhost:9000/`:
  - Renders the welcome page of the application.
- `GET localhost:9000/main`:
  - Renders the first task page.
- `POST localhost:9000/main`:
  - Saves data submitted to controller.
- `GET localhost:9000/register`:
  - Renders the user registration form.
- `POST localhost:9000/register`:
  - Handles user registration and form submission.
- `GET localhost:9000/login`:
  - Renders the user login form.
- `POST localhost:9000/login`:
  - Handles user login and form submission.
- `GET localhost:9000/logout`:
  - Logs the user out of the application.

**Methods of the To-Do List**

- `GET localhost:9000/tasks`:
  - Retrieves and displays all active tasks.
- `GET localhost:9000/tasks/create`:
  - Renders the page for creating a new task.
- `POST localhost:9000/tasks/create`:
  - Handles the submission of a new task.
- `GET localhost:9000/tasks/:id`:
  - Retrieves and displays details of a specific task by its ID.
- `GET localhost:9000/tasks/edit/:id`:
  - Renders the page for editing a specific task by its ID.
- `POST localhost:9000/tasks/edit`:
  - Handles the submission of edited task details.
- `GET localhost:9000/tasks/delete/:id`:
  - Deletes a specific task by its ID.
- `GET localhost:9000/deleted`:
  - Displays a list of deleted tasks.
- `GET /clear/:tableName`:
  - Clears a specified tasks in the database.
 
 # API branch
The "api" branch stores a version of my application in the form of an API. You can go to `localhost:9000/` to view detailed documentation.

 # Start-up instructions
 To start, navigate to the application or API folder and enter the **`docker-compose up --build`** command with Docker running. This will automatically create a docker image with the application and database and start the docker container. Next, navigate to `localhost:9000/` to display the start page or view documentation for the API.
