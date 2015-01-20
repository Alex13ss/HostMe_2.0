function validateRouteCreation($name, $description) {
    if (checkName($name) && checkDescription($description)) {
        return true;
    }
}

function checkName($name) {
    if ($name.val().length == 0) {
        $name.css("background-color", "red");
        return false;
    }
    return true;
}

function checkDescription($description) {
    if ($description.val().length == 0) {
        $description.css("background-color", "red");
        return false;
    }
    return true;
}