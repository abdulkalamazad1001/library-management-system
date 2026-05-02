# Spring Boot Library Management System - Project Report

## 1. Introduction
This document outlines the implementation approach for the Spring Boot application built to manage a Library System with two main entities: `Author` and `Book`. The project demonstrates full CRUD operations, database integration using Spring Data JPA, and a server-side rendered UI using JSP and Spring Web MVC.

## 2. Entity Relationship Design
The system uses two JPA entities: `Author` and `Book`.
- **Author:** Represents the writer. Attributes: `id`, `name`, `bio`.
- **Book:** Represents the literature piece. Attributes: `id`, `title`, `isbn`.

**Relationship:**
- **One-to-Many:** An `Author` can write multiple `Book`s. This is mapped using `@OneToMany(mappedBy = "author")` in the `Author` entity.
- **Many-to-One:** Many `Book`s belong to a single `Author`. This is mapped using `@ManyToOne(fetch = FetchType.LAZY)` and `@JoinColumn(name = "author_id", nullable = false)` in the `Book` entity. This ensures referential integrity at the database level.

## 3. Implementation Details

### Database Population
Using Spring Boot's initialization capabilities, a `data.sql` script was provided to automatically populate the H2 in-memory database with 10 classic authors and 10 famous books upon application startup. This ensures data is immediately available for testing.

### Create Operation
- **View:** `form.jsp` provides a clean UI for inputting book details (`title`, `isbn`) and a dropdown to select the `Author`.
- **Controller Method:** The `saveBook()` method in `LibraryController.java` uses `@ModelAttribute` to bind the form data to a `Book` object.
- **Exception Handling:** A `try-catch` block catches `DataIntegrityViolationException` to gracefully handle duplicate ISBN entries (since the ISBN column has a unique constraint), returning the user to the form with a descriptive error message instead of a generic 500 error.

> **[PLACEHOLDER: Insert Screenshot of the Add Book Form here]**

### Read Operation
- **Custom Query:** In `BookRepository.java`, a custom JPQL query was implemented: `@Query("SELECT b FROM Book b JOIN FETCH b.author")`. This performs an inner join, efficiently fetching both Book and Author data in a single database hit.
- **Controller Method:** The `listBooks()` method calls the service layer to retrieve the list and binds it to the model using `model.addAttribute()`.
- **View:** `list.jsp` uses JSTL `<c:forEach>` to iterate over the books and display them along with their associated author in a styled HTML table.

> **[PLACEHOLDER: Insert Screenshot of the Book List View here]**

### Update Operation
- **Controller Method:** Two methods handle updates. `showFormForUpdate()` fetches the existing book by ID and populates the model. `updateBook()` processes the submitted modifications.
- **View:** `update-form.jsp` binds to the existing data so the user can seamlessly edit it.
- **Service Layer:** The `updateBook` method retrieves the persistent entity, updates its fields, and saves it within a `@Transactional` context to ensure atomicity.

> **[PLACEHOLDER: Insert Screenshot of the Update Book Form here]**

## 4. Challenges Faced and Solutions

1. **JSP Rendering in Spring Boot:** Spring Boot natively prefers Thymeleaf, so integrating JSP required careful dependency management. 
   - *Solution:* Added `tomcat-embed-jasper` and `jakarta.servlet.jsp.jstl-api` dependencies to the `pom.xml`, and explicitly configured the view resolver prefix and suffix (`spring.mvc.view.prefix=/WEB-INF/jsp/`) in `application.properties`.
   
2. **N+1 Query Problem:** Displaying the Author's name in the Book list view initially triggered an additional database query for every single book in the table.
   - *Solution:* I utilized the `JOIN FETCH` syntax in a custom `@Query` within the repository layer. This explicitly instructed JPA to perform an inner join and load the author data eagerly along with the books, reducing query overhead drastically.

3. **Handling Duplicate Data Gracefully:** When a user tried to enter a book with an ISBN that already existed, it caused a raw 500 Server Error page due to a database constraint violation.
   - *Solution:* Implemented explicit exception handling in the controller to specifically catch `DataIntegrityViolationException`. This allowed me to return a user-friendly error message bound to the view model while preserving the user's other form inputs.

## 5. Github URL
The project source code is hosted on GitHub.

**URL:** `[PLACEHOLDER: Replace this with your GitHub Repository URL]`
