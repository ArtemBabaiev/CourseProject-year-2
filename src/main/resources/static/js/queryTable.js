window.onload = function () {
    let value = document.querySelector('input[name="type"]:checked').value;
    if (value === "SQL"){
        renderSqlTable();
    } else if(value === "MONGO"){
        renderMongoResult();
    }
}

function renderMongoResult(){
    let data;
    try {
        data = JSON.parse(result.replace(/&quot;/g, "\"")).cursor.firstBatch;
    }catch (e){
        data = [];
    }
    document.getElementById("mongo-result").innerHTML = "<pre>"+JSON.stringify(data,undefined, 2) +"</pre>"
}

function renderSqlTable(){
    let data;
    let fields;
    try {
        data = JSON.parse(result.replace(/&quot;/g, "\""));
        fields = JSON.parse(description.replace(/&quot;/g, "\""));
        console.log(fields)
    } catch (e) {
        data = [];
        fields = [];
    }
    let table = document.getElementById("resultTable");

    if (data.length === 0) {
        let row = table.insertRow(0);
        row.insertCell(0).innerHTML = "Данні відсутні";
    } else if (result.length === 1) {

        let headerRow = table.tHead.insertRow(0);
        for (let i = 0; i < fields.length; i++) {
            headerRow.insertCell().outerHTML = "<th>" + fields[i][0] + "</th>"
        }

        let row = table.getElementsByTagName('tbody')[0].insertRow();
        for (let i = 0; i < data[0].length; i++) {
            row.insertCell().innerHTML = data[0][i];
        }
    } else {
        let headerRow = table.tHead.insertRow(0);
        for (let i = 0; i < fields.length; i++) {
            console.log(fields[i][0])
            headerRow.insertCell().outerHTML = "<th>" + fields[i][0] + "</th>"
        }
        for (let i = 0; i < data.length; i++) {
            let row = table.getElementsByTagName('tbody')[0].insertRow();
            for (let j = 0; j < data[i].length; j++) {
                row.insertCell().innerHTML = data[i][j];
            }
        }
    }
}