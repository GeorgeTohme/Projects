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


    /*********** CREATE COURSE *********/

    try {  
        // create PDO object that connects to my database
        $db = new PDO("mysql:host=localhost;dbname=university", "root", "");
        // set exception and error mode
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        
        if (isset($_REQUEST["submit"])) {
            if (isset($_REQUEST["courseNumber"]) &&
                isset($_REQUEST["courseTitle"]) ){
                    
                    //Test
                    echo $_REQUEST["courseNumber"];
                    echo $_REQUEST["courseTitle"];

                    $courseNumber = $db->quote($_REQUEST["courseNumber"]);
                    $courseTitle = $db->quote($_REQUEST["courseTitle"]);

                    // Test
                    echo $courseNumber;
                    echo $courseTitle;

                // Handle Regular Expression
                if (preg_match("/\w{0,15}/", $courseNumber) &&
                    preg_match("/\w{0,200}/", $courseTitle)
                    )
                {
                    //Test
                    echo "Preg works ";

                    // insert query
                    $insert_statement = 'INSERT INTO coursedescription (courseNumber, courseTitle)
                                        VALUES (' . $courseNumber . ', 
                                        ' . $courseTitle. '); 
                                        ';

                    // send queries and recive results
                    $db->exec($insert_statement);
                    $rows = $db->query("SELECT * from coursedescription;");
                    if ($rows->rowCount() > 0) {
                        ?>
                        <h3>Search Results</h3>
                        <table>
                            <?php
                                foreach ($rows as $row) {
                            ?>     
                            <tr>
                            <?php
                                for ($i=0; $i < $rows->columnCount(); $i++) {
                            ?>
                                <td> <?= $row[$i]; ?> </td> 
                                <?php  }  ?> 
                            </tr>       
                                <?php }  ?>
                        </table>
                    <?php } ?>
                    <br />Your search yielded <strong> <?= $rows->rowCount()?> results.<br /><br /></strong>
                    <?php

                    //Test
                    echo "Done";
                }
                //Test
                else {
                    echo "preg doesn't work";

                    //Redirect
                    // header("Location : create.php");
                    // die();
                }
            }
        }
    } catch (PDOException $e) {
        die("Connection failed: " . $e->getMessage()."</body></html>");
	}
?>    

</body>
</html>