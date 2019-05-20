<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Courses</title>

     <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Courses</h1>

    <form action="createCourse.php">
        <input type="submit" value= "Create New Course" class="selectButton">
    </form>
<?php
    try {  
        // create PDO object that connects to my database
        $db = new PDO("mysql:host=localhost;dbname=university", "root", "");
        // set exception and error mode
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        
        // recive results
        $rows = $db->query("SELECT * from coursedescription;");
        if ($rows->rowCount() > 0) {
            ?>
            <h3>Search Results</h3>
            <table>
                <tr>
                    <th>Course Number</th>
                    <th>Course Title</th>
                    <th>Update</th>
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
                        <form action="createCourse.php">
                            <input type="submit" value="Edit Course" class="updateButton">
                        </form>  
                    </td>
                    
                </tr>
                       
                    <?php }  ?>
            </table>
        <?php } ?>
    <?php
    } catch (PDOException $e) {
        die("Connection failed: " . $e->getMessage()."</body></html>");
	}
?>    
</body>
</html>