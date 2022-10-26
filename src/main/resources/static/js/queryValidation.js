function validateQuery() {
    let value = document.querySelector('input[name="type"]:checked').value;
    if (value === "SQL") {
        return validateSql();
    } else if (value === "MONGO") {
        return true;
    }
    return true;
}

function validateSql() {
    const SQL_REGEX = /CREATE|UPDATE|DELETE|SET|INSERT|DROP|TRUNCATE|ALTER|USE/gi
    let queryInput = document.getElementById('query');
    if (SQL_REGEX.test(queryInput.value)) {
        let error = document.getElementById('queryError');
        error.style.visibility = "visible";
        error.innerHTML = "Only read operation permitted";
        return false
    }
    return true;
}

window.validateQuery = validateQuery;