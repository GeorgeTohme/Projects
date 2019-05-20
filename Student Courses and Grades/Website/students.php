<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Update</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="filters.js"></script>

</head>
<body>
    <div id="top">
        <h1>Students</h1>
        <fieldset id="filter">
            <legend>Filter by students who have: </legend>
            <input type="radio" name="filter" value="notRegistered"> finished their courses but haven't
            registered a thesis/project. <br>
            <input type="radio" name="filter" value="registered"> finished their courses and registered
            a thesis/project. <br>
            <input type="radio" name="filter" value="over2years"> been in the program for more than 2 years. <br>
            <input type="radio" name="filter" value="none" checked> No filter.
        </fieldset>
    </div>
    <form action="createStudent.php" id="createNewStudent">
        <input type="submit" value= "Create New Student" class="selectButton">
    </form>
<?php
    try {  
        // create PDO object that connects to my database
        $db = new PDO("mysql:host=localhost;dbname=university", "root", "");
        // set exception and error mode
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // recive results
        $rows = $db->query("SELECT * from studentinfo;");
        if ($rows->rowCount() > 0) {
            ?>
            <h3>Filter Results</h3>
            <table id="studentsTable">
                <tr>
                    <th>Student ID</th>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Starting Year</th>
                    <th>Graduation Year</th>
                    <th>Graduation Flag</th>
                    <th>Option</th>
                    <th>Comments</th>
                    <th>View/Update</th>
                </tr>   
                
                <?php
                    foreach ($rows as $row) {
                ?>  
                <tr>
                <?php
                    for ($i=0; $i < $rows->columnCount(); $i++) {
                ?>
                    <td> 
                        <?= $row[$i]; ?> 
                    </td> 
                    <?php  }  ?> 
                    <td>
                        <!-- create edit profile button and make it submit to createStudent.php with the id of the student -->
                        <form action="createStudent.php?id=<?= $row[0] ?>" method="POST">
                            <input type="submit" class="updateButton" value="Edit Profile">
                        </form>
                        <?php
                            if ($row[8] == "courses") { ?> 
                            <!-- create "View Courses" button and make it submit to courses.php with the id of the student -->
                            <form action="courses.php?id=<?= $row[0] ?>" method="POST">
                                <input type="submit" class="updateButton" value="View Courses">
                            </form>
                        <?php   } else if ($row[8] == "thesis" || $row[8] == "project") { ?>
                            <!-- create "View Thesis/Project" button and make it submit to thesisproject.php with the id of the student -->
                            <form action="thesisproject.php?id=<?= $row[0] ?>" method="POST">
                                <input type="submit" class="updateButton" value="View Thesis/Project">
                            </form>
                        <?php   } ?>  
                    </td>   
                </tr>   
                    <?php }  ?>
            </table>
        <?php }
    } catch (PDOException $e) {
        die("Connection failed: " . $e->getMessage()."</body></html>");
	}
?>    


</body>
</html>