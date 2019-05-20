<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Result</title>
</head>
<body>

<?php
    
    // id is passed through session
    session_start();
    // keep track of boolean value to differentiate between an insert and update request
    $update = isset($_SESSION["id"]);
    try {  
        // create PDO object that connects to my database
        $db = new PDO("mysql:host=localhost;dbname=university", "root", "");
        // set exception and error mode
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        
        if (isset($_REQUEST["submit"]) &&
            isset($_REQUEST["studentID"]) &&
            isset($_REQUEST["lastname"]) &&
            isset($_REQUEST["firstname"]) &&  
            isset($_REQUEST["email"]) &&
            isset($_REQUEST["startingYear"]) &&
            isset($_REQUEST["gradFlag"]) && 
            isset($_REQUEST["gradYear"]) &&
            isset($_REQUEST["option"])) {
               
            $studentID = $db->quote($_REQUEST["studentID"]);
            $lastname = $db->quote($_REQUEST["lastname"]);
            $firstname = $db->quote($_REQUEST["firstname"]);
            $address = $db->quote($_REQUEST["address"]);
            $email = $db->quote($_REQUEST["email"]);
            $startingYear = $db->quote($_REQUEST["startingYear"]);
            $gradFlag = $db->quote($_REQUEST["gradFlag"]);
            $gradYear = $db->quote($_REQUEST["gradYear"]);
            $option = $db->quote($_REQUEST["option"]);
            $comments = $db->quote($_REQUEST["comments"]);

            // Handle Regular Expression
            if (preg_match("/[0-9]{9}/", $studentID) &&
                preg_match("/[a-zA-Z]/", $lastname) &&
                preg_match("/[a-zA-Z]/", $firstname) &&
                preg_match("/[\w\s,]*/", $address) &&
                preg_match("/\w{3,}[@][\w{3,}.]/", $email) &&
                preg_match("/(19[5-9]\d|20[0-4]\d|2019)/", $startingYear) &&
                preg_match("/(19[5-9]\d|20[0-4]\d|2050)/", $gradYear) &&
                $startingYear < $gradYear) {
                // setup query statement appropriately depending on whether id is passed in url
                $query_statement = "";
                if (!$update) { 
                    $query_statement = 'INSERT INTO studentinfo (studentID, lastName, firstName, address, email, startingYear, graduationYear, graduationFlag, studentOption, comments)
                                         VALUES (' . $studentID . ', 
                                         ' . $lastname . ', 
                                         ' . $firstname . ',
                                         ' . $address . ',
                                         ' . $email . ', 
                                         ' . $startingYear . ', 
                                         ' . $gradYear . ', 
                                         ' . $gradFlag . ', 
                                         ' . $option . ',
                                         ' . $comments . '); '; 
                } else {
                    $id = $db->quote($_SESSION["id"]);
                    $query_statement = 'UPDATE studentinfo 
                                        SET studentID = ' . $studentID . ', 
                                            lastname = ' . $lastname . ', 
                                            firstname = ' . $firstname . ',
                                            address = ' . $address . ',
                                            email = ' . $email . ', 
                                            startingYear = ' . $startingYear . ', 
                                            graduationYear = ' . $gradYear . ', 
                                            graduationFlag = ' . $gradFlag . ', 
                                            studentOption = ' . $option . ',
                                            comments = ' . $comments . '
                                        WHERE studentID = ' . $id . ';';
                }
                // execute query
                $db->exec($query_statement);
            }
        }
    } catch (PDOException $e) {
        die("Connection failed: " . $e->getMessage()."</body></html>");
    } ?>

    <form action="students.php">
        <input type="submit" value ="Go back to Students page" class="selectButton">
    </form>
</body>
</html>