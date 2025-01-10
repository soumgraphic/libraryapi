**Devoir étudiants Brest Open Campus**

Faute de ne pas pouvoir créer de devoir sur Teams, vous trouverez ci-dessous le devoir à réaliser avant le 02 février à 20h

1. Une API développer avec Spring boot connecté à une base de données.
2. Une API qui respecte les bonnes pratiques de qualité de code et de sécurité, authentification JWT avec des rôles.
3. Une API documentée avec Swagger et Postman et testable facilement avec des cas de tests et aussi des tests postman.
4. Une API dont le code source est déployée sur Github accessible en public.
5. Une API qui suit les specifications dans le document ci-dessous homework.md
6. Vous êtes libre de choisir le concept d'API à développer, il faut juste que ça respecte l'exemple de spécification ci-dessous.
7. Votre API doit également avoir des endpoints innovants au delà du CRUD classique sur les 4 ressources minimums requis.
8. Une mini doc dans le readme.md qui explique le concept du projet et sur comment démarrer avec des cas de tests et tout le nécessaire à envoyer a mon mail.

Sure, here is an exercise idea for developing a school API:

**Exercise: Develop a School Management API**

**Objective:**
Create a RESTful API to manage a school's students, teachers, classes, and subjects. The API should allow for CRUD (Create, Read, Update, Delete) operations on these entities and provide endpoints to handle various relationships between them.

**Requirements:**

1. **Entities:**
    - **Student:** Represents a student with attributes like `id`, `firstName`, `lastName`, `birthDate`, `email`, and `enrolledSchoolClasses`.
    - **Teacher:** Represents a teacher with attributes like `id`, `firstName`, `lastName`, `email`, `subjectsTaught`, and `assignedSchoolClasses`.
    - **Class:** Represents a class with attributes like `id`, `name`, `teacher`, `students`, and `subject`.
    - **Subject:** Represents a subject with attributes like `id`, `name`, and `description`.

2. **Endpoints:**
    - **Students:**
        - `GET /students`: Retrieve a list of all students.
        - `GET /students/{id}`: Retrieve details of a specific student.
        - `POST /students`: Create a new student.
        - `PUT /students/{id}`: Update details of a specific student.
        - `DELETE /students/{id}`: Delete a specific student.
    - **Teachers:**
        - `GET /teachers`: Retrieve a list of all teachers.
        - `GET /teachers/{id}`: Retrieve details of a specific teacher.
        - `POST /teachers`: Create a new teacher.
        - `PUT /teachers/{id}`: Update details of a specific teacher.
        - `DELETE /teachers/{id}`: Delete a specific teacher.
    - **Classes:**
        - `GET /classes`: Retrieve a list of all classes.
        - `GET /classes/{id}`: Retrieve details of a specific class.
        - `POST /classes`: Create a new class.
        - `PUT /classes/{id}`: Update details of a specific class.
        - `DELETE /classes/{id}`: Delete a specific class.
    - **Subjects:**
        - `GET /subjects`: Retrieve a list of all subjects.
        - `GET /subjects/{id}`: Retrieve details of a specific subject.
        - `POST /subjects`: Create a new subject.
        - `PUT /subjects/{id}`: Update details of a specific subject.
        - `DELETE /subjects/{id}`: Delete a specific subject.

3. **Relationships:**
    - A student can be enrolled in multiple classes.
    - A teacher can teach multiple subjects and be assigned to multiple classes.
    - A class is associated with one subject and one teacher.
    - A subject can be taught by multiple teachers.

4. **Additional Features:**
    - Implement pagination for listing endpoints.
    - Add validation for input data.
    - Implement error handling and return appropriate HTTP status codes.
    - Secure the API with JWT (JSON Web Token) authentication.
    - Implement role-based access control (RBAC):
       **Admin**: Can create, update, and delete any entity.
       **Teacher**: Can update and delete their assigned classes and students.
       **Student**: Can view their own details and enrolled classes.
    - Document the API using Swagger or a similar tool.

**Outcome:**
By completing this exercise, you will have a fully functional API that can manage students, teachers, classes, and subjects in a school. This API will support basic CRUD operations and handle relationships between different entities, providing a comprehensive solution for school management.