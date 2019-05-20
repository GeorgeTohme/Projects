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
    <h1 id="create">Create Course</h1>

    <form action="submitCourse.php" method="POST">
        <div id = "left">
            <label>Course Number </label> <br>
            <label>Course Title</label> <br>
        </div>

        <div id = "right">
            <input type="text" name="courseNumber"><br>
            <input type="text" name="courseTitle"><br>
        </div>

        <input type="submit" name="submit" value="Submit" id="submit">
    </form>
</body>
</html>