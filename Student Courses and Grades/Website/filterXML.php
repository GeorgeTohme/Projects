<?php
// # A PHP script to return book data.  Author: Marty Stepp, Nov 30 2008
// $BOOKS_FILE = "books.txt";

try {  
    // create PDO object that connects to my database
    $db = new PDO("mysql:host=localhost;dbname=university", "root", "");
    // set exception and error mode
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // prepare sql statement:
    if (isset($_REQUEST["filter"])) {
        $filter = $_REQUEST["filter"];
        
        
        // check which radio button was selected and create the appropriate sql query statement
        $select_statement;
        switch ($filter) {
            case "notRegistered":
                $select_statement = "SELECT si.studentID, si.lastName, si.firstName, si.address, 
                                        si.email, si.startingYear, si.graduationYear, si.graduationFlag, 
                                        si.studentOption, si.comments
                                    FROM studentinfo si
                                    WHERE si.graduationFlag = 1 AND si.studentID NOT IN (
                                        SELECT stp.studentID
                                        FROM studentthesisproject stp
                                    );" ;
                break;    
            case "registered":
                $select_statement = "SELECT si.studentID, si.lastName, si.firstName, si.address, 
                                        si.email, si.startingYear, si.graduationYear, si.graduationFlag, 
                                        si.studentOption, si.comments
                                    FROM studentinfo si
                                    WHERE si.graduationFlag = 1 AND si.studentID IN (
                                        SELECT stp.studentID
                                        FROM studentthesisproject stp
                                    );" ;
                break;
            case "over2years":
                $date =  $db->quote(date("Y"));
                $select_statement = 'SELECT si.studentID, si.lastName, si.firstName, si.address, 
                                        si.email, si.startingYear, si.graduationYear, si.graduationFlag, 
                                        si.studentOption, si.comments
                                    FROM studentinfo si
                                    WHERE (' . $date . '-si.startingYear) > 2;' ;
                break;
            case "none":
                $select_statement = "SELECT * from studentinfo;";
                break;
            default:
                $select_statement = "SELECT * from studentinfo;";
                break;
        }

        // query database
        $rows = $db->query($select_statement);       
        // parse result as xml object
        // for each row create this <student> tag:
        //  <student id="0" lname="1" fname="2">
        //         <address> 3 </address>
        //         <email> 4 </email>
        //         <status startYear="5" gradYear="6"> "7" </status>
        //         <studentOption> 8 </studentOption>
        //         <comments> 9 </comments>
        //  </student> 
        header("Content-type: application/xml");
        print "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

        print "<students>\n"; 
	    foreach ($rows as $row) {
            list($id, $lname, $fname, $address, $email, $startingYear,
                $graduationYear, $graduationFlag, $studentOption, $comments) = $row;
	    	# print it as XML
	    	print "\t<student id=\"$id\" lname=\"$lname\" fname=\"$fname\">\n";
	    	print "\t\t<address>$address</address>\n";
            print "\t\t<email>$email</email>\n";
            print "\t\t<status startYear=\"$startingYear\" gradYear=\"$graduationYear\">$graduationFlag</status> \n";
            print "\t\t<studentOption>$studentOption</studentOption>\n";
            print "\t\t<comments>$comments</comments>\n";
            print "\t</student>\n";
        }
        print "</students>\n"; 
    } 
} catch (PDOException $e) {
        die("Connection failed: " . $e->getMessage()."</body></html>");
}
?>
