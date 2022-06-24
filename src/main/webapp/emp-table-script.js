console.log('Hello javascript world');

// grab the table element 
// we can modify how it looks and add elements


//var let const
let table = document.querySelector('table')
// saves the table element to the variable

let button = document.getElementById('all-emps')

//WHEN the button is clicked, we
// make a call to the server, fetch the json data, 
// and parse it and append it to the table

button.addEventListener('click', fetchEmps)

function buildTable(data) {

    console.log('buildTable method triggered')

    console.log(data)

    let header = document.createElement('thead')
    let headerRow = document.createElement('tr')

    header.appendChild(headerRow);

    // append the header to the table
    table.appendChild(header);

    // create a header column for first name, last name, etc
    let th1 = document.createElement("th")
    th1.innerHTML = 'First Name'

    let th2 = document.createElement("th")
    th1.innerHTML = 'Last Name'

    let th3 = document.createElement("th")
    th1.innerHTML = 'Username'

    // append child nodes onto header
    headerRow.appendChild(th1)
    headerRow.appendChild(th2)
    headerRow.appendChild(th3)

    data.forEach(e => {

        console.log(e)

        let row = document.createElement('tr')
        let td1 = document.createElement('td')
        let td2 = document.createElement('td')
        let td3 = document.createElement('td')

        // set the inner html of each cell to the diff properties of empployees
        td1.innerHTML = e.firstName
        td2.innerHTML = e.lastName
        td3.innerHTML = e.username

        // finally append each table cell to the row
        row.appendChild(td1)
        row.appendChild(td2)
        row.appendChild(td3)

        // append the row to the table
        table.appendChild(row)
        
    });
}

function fetchEmps() {

    // Fetch API is a modern interface that allows you to make 
    // HTTP requests to a server and process the results
    // that you get back asynchronously

    let hostname = window.location.hostname // grabs ip of where its deployed

    fetch(`http://${hostname}:8080/employee-servlet-app/employees`)
    .then(response => response.json()) // this takes a json string and transforms it 
                                    // to a javascript object
    //.then(Obj => console.log(Obj)) // print Js obj to the console
    .then(buildTable) // this automatically passes the data that's been parsed The JS object is an array of Employees
                    // passes it to the build table
}

let user = {

    fisrtName : "first",
    lastName : "last",
    username : "bob",
    password : "secretpass"
}

function sayHello() {

   // console.log('Hello There')


}
