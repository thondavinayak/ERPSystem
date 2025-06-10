Student Management System
This project implements a Student Management System in Java, featuring a graphical user interface (GUI) and database operations using JDBC with SQLite. It allows adding, updating, and deleting student records, as well as displaying them in a table.

📁 Project Structure
graphql
Copy
Edit
.
├── DBManagement.java    # Handles database connection and operations
├── Gui.java             # Main GUI window with student table and buttons
├── Student.java         # Student model class
└── README.md            # Project documentation
🚀 Features
✅ Add a new student record
✅ Update existing student data
✅ Delete a student record
✅ View all student records in a JTable
✅ SQLite database integration for persistent data storage

⚙️ How It Works
1. Database Management (DBManagement.java)
Establishes a connection to an SQLite database (students.db).

Provides methods to:

Insert a new student.

Update an existing student.

Delete a student by ID.

Retrieve all student records.

2. Student Model (Student.java)
Represents a student object with the following fields:

id (int)

name (String)

age (int)

grade (String)

3. Graphical User Interface (Gui.java)
Built using Swing components.

Allows interaction with the database through:

Add button to insert a new student.

Update button to modify an existing student’s details.

Delete button to remove a student by ID.

Load button to refresh and display all records in the table.

🖥️ Prerequisites
Java 8 or later

SQLite JDBC driver (sqlite-jdbc-<version>.jar) added to your classpath

🔧 Running the Application
Clone the repository:

bash
Copy
Edit
git clone https://github.com/<your-username>/student-management-system.git
cd student-management-system
Compile the Java files:

bash
Copy
Edit
javac -cp ".:sqlite-jdbc-<version>.jar" DBManagement.java Gui.java Student.java
Run the application:

bash
Copy
Edit
java -cp ".:sqlite-jdbc-<version>.jar" Gui
📝 Notes
The database file (students.db) will be created automatically in the project directory if it doesn’t exist.

Make sure to replace <version> with the actual SQLite JDBC driver version you’re using.

If running on Windows, replace : with ; in the classpath (e.g., . ; sqlite-jdbc-<version>.jar).
