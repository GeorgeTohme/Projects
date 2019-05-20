// retrieves DOM elements with the given name
function $name(name) { return document.getElementsByName(name); }

// returns DOM element with the given ID
function $id(id) { return document.getElementById(id); }

// takes a DOM element and returns all tags of a given name inside that element
function $tag(element, tag) {
    return element.getElementsByTagName(tag);
}

// given an array of XML objects, returns the nodeValue of the first element
function nodeValue(XML) {
    return XML[0].firstChild.nodeValue;
}

window.onload = function () { 
    var filters = document.getElementsByName("filter");
    // filters[0].onclick = loadStudentsJSON; // notRegistered
    // filters[1].onclick = loadStudentsJSON; // registered
    // alert(filters[0]);   
    filters[0].onclick = loadStudentsXML; // notRegistered
    filters[1].onclick = loadStudentsXML; // registered
    filters[2].onclick = loadStudentsXML; // over2years
    filters[3].onclick = loadStudentsXML; // none
};

// links to filterXML.php with specific filter type passed as parameter in the url (data is returned as XML)
function loadStudentsXML() {
    var ajax = new XMLHttpRequest();
    ajax.onload = gotStudentsXML; 
    ajax.onerror = ajaxFailed;
    ajax.open("POST", "filterXML.php?filter=" + this.value, true);
    ajax.send(null);
}

// updates the HTML DOM after receiving data from the database in XML form
function gotStudentsXML() {
    var studentsTable = $id("studentsTable");
    studentsTable.innerHTML = ""; // delete previous state of table

    if (this.status == 200) {
        // alert(this.responseText);
        // create a <table> tag to store data in
        var thead = createTableHeader(); // returns table with thead
        var tableBody = document.createElement("tbody");
        // alert(this.responseXML);
        var allStudents = $tag(this.responseXML, "student");
        for (var i = 0; i < allStudents.length; i++) {
            // Retrieve data from XML DOM
            // <student id="0" lname="1" fname="2">
            //      <address> 3 </address>
            //      <email> 4 </email>
            //      <status startYear="5" gradYear="6"> "7" </status>
            //      <studentOption> 8 </studentOption>
            //      <comments> 9 </comments>
            // </student>
            var student = allStudents[i];
            var data = new Array();
            data[0] = student.getAttribute("id");
            data[1] = student.getAttribute("lname");
            data[2] = student.getAttribute("fname");           
            data[3] = nodeValue($tag(student, "address"));            
            data[4] = nodeValue($tag(student, "email"));    
            var status = $tag(student, "status");
            data[5] = status[0].getAttribute("startYear");
            data[6] = status[0].getAttribute("gradYear");
            data[7] = nodeValue(status); // grad flag
            data[8] = nodeValue($tag(student, "studentOption"));
            data[9] = nodeValue($tag(student, "comments"));

            // Update HTML DOM with retrieved data
            // create a new <tr> tag and a <td> tag for each data value retrieved, then add them to the <tbody>
            var tr = document.createElement("tr");
            data.forEach(element => {
                var td = document.createElement("td");
                td.innerHTML = element;
                tr.appendChild(td);
            }); // end foreach

            // add input forms that redirect to other pages at the end of each row
            var view_updateTD = document.createElement("td");
            // edit profile
            var form1 = createForm("createStudent", data[0], "Edit Profile");

            var form2;
            if (data[8] == "courses") { // view courses
                form2 = createForm("courses", data[0], "View Courses");
            } else if (data[8] == "thesis" || data[8] == "project") { // view thesis/project
                form2 = createForm("thesisproject", data[0], "View Thesis/Project");
            }

            view_updateTD.appendChild(form1);
            view_updateTD.appendChild(form2);

            tr.append(view_updateTD);
            tableBody.appendChild(tr);   
        } // end for loop
        studentsTable.appendChild(thead);
        studentsTable.appendChild(tableBody);
    } // end if 
    else {
        studentsTable.innerHTML = "Error: Status != 200";
    }
}

// function that creates a table and its header, then returns an html table element
function createTableHeader() {
    // create table header
    var thead = document.createElement("thead");
    var tr = document.createElement("tr");

    // create table headers
    var th = new Array();
    for (var i = 0; i < 11; i++) {
        th[i] = document.createElement("th");
    }

    // populate table headers:
    th[0].innerHTML = "Student ID";
    th[1].innerHTML = "Last Name";
    th[2].innerHTML = "First Name";
    th[3].innerHTML = "Address";
    th[4].innerHTML = "Email";
    th[5].innerHTML = "Graduated";
    th[6].innerHTML = "Start Year";
    th[7].innerHTML = "Graduation Year";
    th[8].innerHTML = "Student Option";
    th[9].innerHTML = "Comments";
    th[10].innerHTML = "View/Update";

    // append table headers to the table row
    for (var i = 0; i < 11; i++) {
        tr.appendChild(th[i]);
    }

    // append table row to table
    thead.appendChild(tr);

    return thead;
}

// function that creates a form element with an input submit button and returns it
function createForm(fileName, id, inputValue) {
    var form = document.createElement("form");
    form.setAttribute("action", fileName + ".php?id=" + id);
    form.setAttribute("method", "GET");

    var editProfileInput = document.createElement("input");
    editProfileInput.setAttribute("type", "submit");
    editProfileInput.setAttribute("class", "updateButton");
    editProfileInput.setAttribute("value", inputValue);
    
    form.appendChild(editProfileInput);
    return form;
}

// in case there was an error with the ajax request 
function ajaxFailed(exception) {
	var errorMessage = "Error making Ajax request:\n\n";
	if (exception) {
		errorMessage += "Exception: " + exception.message;
	} else {
		errorMessage += "Server status:\n" + ajax.status + " " + ajax.statusText + 
		                "\n\nServer response text:\n" + ajax.responseText;
	}
	alert(errorMessage);
}

/*
// links to filterJSON.php with specific filter type passed as parameter in the url (data is returned as JSON)
function loadStudentsJSON() {
    var ajax = new XMLHttpRequest();
    ajax.onload = gotStudentsJSON;
    ajax.onerror = ajaxFailed;
    ajax.open("GET", "filterJSON.php?filter=" + this.value, true);
    ajax.send(null);
}
// updates the HTML DOM after receiving data from the database in JSON form
function gotStudentsJSON() {

}
*/


