Group Members:
- George Tohme; gat03;
- Marwan Nour; msn17;

How to use the site:
- the whole website is divided into 2 sections that are first displayed as buttons on the index.html:
	1- courses: list of all courses, functionality: controlling the master list of courses
	2- students list of all students, functionality: controlling and manipulating everything that could be done to a student
	(create, update, view a student's courses/thesis/project, enrol/remove courses, add thesis/project, filter students)
	
Done:
- view courses
- view students
- create/update student
- create course 
- queries (with XML)

Not done: 
- update course (follows the same pattern as updating student)
- create project/thesis (follows same pattern as create course)
- update project/thesis (follows same pattern as update student)
- json (follows same pattern as XML)

Ideas:
For the "View Courses" button whos code will be written in the courses.php file but with a parameter passed to it: id=studentID:
- 	"view courses" button attached to each student profile should show a list of courses belonging to a specific student depending 
	on the studentid attached to it as a value which is sent as a parameter in the url. Using the following query, we can acquire a student's courses:
	"SELECT * FROM studentcourses WHERE studentID = $studentID;" where $studentID is the value of $_REQUEST["id"] (the id passed as a parameter in the url)
	It uses the courses.php file which initially displays a list of all courses if not given studentID as parameter in the url. (similar 
	paradigm used to update student info using create.php) 
-	In addition to displaying all the courses, we wanted to add a "remove" button next to each one to remove a student from this course.
	SQL statement is simple: "DELETE FROM studentcourses WHERE studentID = $studentID AND courseNumber = $courseNumber;".
	In this case, $studentID would be passed in a session between the courses.php file and the removeStudentFromCourse.php file 
	(as we did between createStudent and submitStudent)
-	Finally, we wanted to add an "Add Courses" button on this page that allows the student to enrol in available courses.
	He will be redirected to the courses.php page (that displays a list of all courses in the database) but this time there will
	be a check box next to each course that when checked and user clicks submit, enrolls the student in these courses.
	This requires an insert statement into the studentcourses table that will include 1 entry for each course selected
	with courseNumber = course number that was checked (for each)
	and studentID = $_SESSION["id"]. 
	
Final thoughts:
Given the time, we could get these done. Creating the XML object and parsing it was a hassle and consumed a lot of our time.
Also, writing our php files in such a way as to accommodate creates (inserts) and edits (updates) was annoying but worth it, as it taught us
the power of sending data in urls and sessions.
