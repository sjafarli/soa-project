let invoice_port = 8085;
let voting_port = 10223;
let search_port = 8084;

let home_url = "http://localhost:"


window.onload = function() {
    getMovies();
    getVotingList();
}

function show_status(response) {
    status = response.status;
    if (status < 300) {displayNote(0, "Action Successfull")}
    else {displayNote(2, "Critical Error")}
}

function getMovies() {
    fetch(home_url + search_port + "/cinema/movies", {method: 'GET'})
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            displayMovies(data)
        })
        .catch(function(error) {
            console.log(error);
        });
}

function getVotingList() {
    fetch(home_url + voting_port + "/cinema/voting/list", {method: 'GET'})
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            displayVotings(data)
        })
        .catch(function(error) {
            console.log(error);
        });
}

function search(form) {
    fetch(home_url + search_port + "/cinema/movies/search?query=" + form.query.value, {method: 'GET'})
        .then((response) => {
            console.log(response);
            show_status(response);
            return response.json();
        })
        .then((data) => {
            displayMovies(data);
            document.getElementById("nav1").click();
        })
        .catch(function(error) {
            console.log(error);
        });
}

function suggest(id) {
    fetch(home_url + search_port + "/cinema/voting/suggest?id=" + id, {method: 'POST'})
        .then((response) => {
            show_status(response);
            getVotingList();
            document.getElementById("nav2").click();
        })
        .catch(function(error) {
            console.log(error);
        });
}

function buyTicket(id) {
    fetch(home_url + invoice_port + "/cinema/movie/tt0468569" + "/payment", {method: 'GET'})
        .then((response) => {
            displayNote(0,"Payment processed successfuly. Amount: 13 euro, reference ahdhgavdh");
        })
        .catch(function(error) {
            console.log(error);
        });
}

function addMovie(id) {
    fetch(home_url + search_port + "/cinema/movies/add?id=" + id, {method: 'PUT'})
        .then((response) => {
            show_status(response);
            getMovies();
            document.getElementById("nav1").click();
        })
        .catch(function(error) {
            console.log(error);
        });
}

function removeMovie(id) {
    fetch(home_url + search_port + "/cinema/movie/" + id + "/remove", {method: 'DELETE'})
        .then((response) => {
            show_status(response);
            getMovies();
        })
        .catch(function(error) {
            console.log(error);
        });
}

function addVote(id) {
    fetch(home_url + voting_port + "/cinema/voting/tt0066070", {method: 'PUT'})
        .then((response) => {
            show_status(response);
            getVotingList();
        })
        .catch(function(error) {
            console.log(error);
        });
}

function displayMovies(data) {
    elem = document.getElementById("movies");
    elem.innerHTML = "";
    for (var i in data) {
        var html =
            `<tr>
      <td>${data[i].name}</td>
      <td>${data[i].year}</td>
      <td>${addButton(data[i])}</td>
    </tr>`;
        elem.innerHTML += html;
    }

    function addButton(e) {
        if (e.available) {
            return (`<div class="btn-group w-100">
        <button class="btn btn-primary" onClick="buyTicket('` + e.imdb_id + `')">Buy Ticket</button>
        <button class="btn btn-danger" onClick="removeMovie('` + e.imdb_id + `')">Remove</button>
        </div>`)
        } else {
            return (`<button class="btn btn-warning w-100" onClick="suggest('` + e.imdb_id + `')">Suggest</button>`)
        }
    }
}

function displayVotings(data) {
    elem = document.getElementById("votings");
    elem.innerHTML = "";
    for (var i in data) {
        var html =
            `<tr>
      <td>${data[i].name}</td>
      <td>${data[i].votes}</td>
      <td>${addButton(data[i])}</td>
    </tr>`;
        elem.innerHTML += html;
    }

    function addButton(e) {
        return (`<div class="btn-group w-100">
      <button class="btn btn-primary" onClick="addVote('` + e.imdb_id + `')">Vote</button>
      <button class="btn btn-success" onClick="addMovie('` + e.imdb_id + `')">Add</button>
      </div>`)
    }
}

function displayNote(type,text)  {
    var newone = document.getElementById('note');
    var n = newone.cloneNode(true);
    newone.parentNode.replaceChild(n,newone);

    n.innerHTML = text;
    switch (type) {
        case 0: n.style.backgroundColor = "#5cb85c"; break;
        case 1: n.style.backgroundColor = "#f0ad4e"; break;
        case 2: n.style.backgroundColor = "#d9534f"; break;
        default: n.style.backgroundColor = "#5bc0de"; break;
    }
    n.classList.add("note_animation");
}
