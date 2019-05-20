<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Create</title>
</head>

<body>
<?php
    $update = isset($_REQUEST["id"]); // if is set, then update request was sent, else a create request was sent
    // declare and initialize variables to be used later
    $id = "";
    $lname = "";
    $fname = "";
    $address = "";
    $email = "";
    $startingYear = "";
    $graduationYear = "";
    $graduationFlag = "";
    $studentOption = "";
    $comments = "";

    // if student id is passed in url
    if ($update) {
        try {
            // use session to store id
            session_start();
            $_SESSION["id"] = $_REQUEST["id"];
            // create PDO object that connects to my database
            $db = new PDO("mysql:host=localhost;dbname=university", "root", "");
            // set exception and error mode
            $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            
            // query database
            $id = $db->quote($_REQUEST["id"]);
            $rows = $db->query("SELECT * from studentinfo WHERE studentID=". $id .";");

            // convert returned values from DB to list of php variables
            foreach ($rows as $row) { // only one row will be returned (becuause studentIDs are unique primary keys)
                list($id, $lname, $fname, $address, $email, $startingYear, $graduationYear, 
                    $graduationFlag, $studentOption, $comments) = $row;
            }
        } catch (PDOException $e) {
            die("Connection failed: " . $e->getMessage()."</body></html>");
        }
    }

    $h1 = $update ? "Update" : "Create"; // displays appropriate text
?>
    <h1><?= $h1 ?> Student</h1>
    <form action="submitStudent.php" method="POST">
        <div id = "left">
            <label for="studentID">Student ID</label> <br>
            <label for="lastname">Last Name</label> <br>
            <label for="firstname">First Name</label> <br>
            <label for="address">Address</label> <br>
            <label for="email">Email</label> <br>
            <label for="startingYear">Starting Year</label> <br>
            <label for="gradYear">Graduation Year</label> <br>
            <!-- -->
            <label for="gradFlag">Graduation Flag</label> <br>
            <!-- -->
            <label for="options">Option</label> <br>
            <label for="comments">Comments</label> <br>
        </div>

        <div id = "right">
            <input type="text" name="studentID" value="<?= $id ?>"><br>
            <input type="text" name="lastname" value="<?= $lname ?>"><br>
            <input type="text" name = "firstname" value="<?= $fname ?>"><br>
            <input type="text" name = "address" value="<?= $address ?>"><br>
            <input type="text" name = "email" value="<?= $email?>"><br>
            <input type="text" name="startingYear" value="<?=  $startingYear ?>"><br>
            <input type="text" name="gradYear" value="<?=  $graduationYear ?>"><br>

<?php if (!$update) { // create request ?>
            <input type="radio" name="gradFlag" value="1">Done
            <input type="radio" name="gradFlag" value="0" checked>Not Done<br> 
            <select name="options">
                <option name="option" value="thesis">Thesis</option>
                <option name="option" value="project">Project</option>
                <option name="option" value="courses" selected>Courses</option>
            </select>
<?php   } else { // update request
                if ((int)$graduationFlag) {  // If gradflag is 1 -> make the Done checked ?>            
                    <input type="radio" name="gradFlag" value="1" checked> Done
                    <input type="radio" name="gradFlag" value="0"> Not Done<br>
        <?php } else { // else is 0 -> make the Not Done checked ?>
                    <input type="radio" name="gradFlag" value="1"> Done
                    <input type="radio" name="gradFlag" value="0" checked> Not Done<br>
        <?php } 
                if ($studentOption == "thesis") { ?>
                    <input type="radio" name="option" value="thesis" checked>Thesis
                    <input type="radio" name="option" value="project" >Project
                    <input type="radio" name ="option" value="courses"> Courses
        <?php   } else if ($studentOption == "project") { ?>
                    <input type="radio" name="option" value="thesis">Thesis
                    <input type="radio" name="option" value="project" checked>Project
                    <input type="radio" name ="option" value="courses"> Courses
        <?php   } else { ?>                                  
                    <input type="radio" name="option" value="thesis" >Thesis
                    <input type="radio" name="option" value="project" >Project
                    <input type="radio" name ="option" value="courses" checked> Courses
        <?php   }  ?> 
    <?php   }  ?> 
            <textarea name="comments" cols="30" rows="3" value="<?= $comments ?>"><?= $comments ?></textarea>
            <input type="submit" name="submit" value="Submit" id="submit">
        </div>   
    </form>
</body>
</html>