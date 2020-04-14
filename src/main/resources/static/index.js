// window.onload = function() {
//   getMovies();
//   getVotingList();
//   jQuery('.toast').toast({delay: 3000});
//   jQuery('.toast').toast('show');
// }
//
// function getMovies() {
//   fetch("/cinema/movies")
//   .then((response) => {
//     return response.json();
//   })
//   .then((data) => {
//     displayMovies(data)
//   });
// }
//
// function getVotingList() {
//   fetch("/cinema/voting/list")
//   .then((response) => {
//     return response.json();
//   })
//   .then((data) => {
//     console.log(data);
//     displayVotings(data)
//   });
// }
//
// function search(form) {
//   fetch("/cinema/movies/search?query=" + form.query.value)
//   .then((response) => {
//     return response.json();
//   })
//   .then((data) => {
//     displayMovies(data);
//     document.getElementById("nav1").click();
//   });
// }
//
// function suggest(id) {
//   fetch("/cinema/voting/suggest?id=" + id, {method: 'POST'})
//   .then((response) => {
//     console.log(response);
//     getVotingList();
//     document.getElementById("nav2").click();
//   });
// }
//
// function buyTicket(id) {
//   fetch("/cinema/movie/" + id + "/payment", {method: 'GET'})
//   .then((response) => {
//     console.log(response);
//   })
// }
//
// function addMovie(id) {
//   fetch("/cinema/movies/add?id=" + id, {method: 'PUT'})
//   .then((response) => {
//     console.log(response);
//     getMovies();
//     document.getElementById("nav1").click();
//   });
// }
//
// function removeMovie(id) {
//   fetch("/cinema/movie/" + id + "/remove", {method: 'DELETE'})
//   .then((response) => {
//     console.log(response);
//     getMovies();
//   });
// }
//
// function addVote(id) {
//   fetch("/cinema/voting/" + id, {method: 'PUT'})
//   .then((response) => {
//     console.log(response);
//     getVotingList();
//   });
// }
//
// function displayMovies(data) {
//   elem = document.getElementById("movies");
//   elem.innerHTML = "";
//   for (var i in data) {
//     var html =
//     `<tr>
//       <td>${data[i].name}</td>
//       <td>${data[i].year}</td>
//       <td>${addButton(data[i])}</td>
//     </tr>`;
//     elem.innerHTML += html;
//   }
//
//   function addButton(e) {
//     if (e.available) {
//       return (`<div class="btn-group w-100">
//         <button class="btn btn-primary" onClick="buyTicket('` + e.imdb_id + `')">Buy Ticket</button>
//         <button class="btn btn-danger" onClick="removeMovie('` + e.imdb_id + `')">Remove</button>
//         </div>`)
//     } else {
//       return (`<button class="btn btn-warning w-100" onClick="suggest('` + e.imdb_id + `')">Suggest</button>`)
//     }
//   }
// }
//
// function displayVotings(data) {
//   elem = document.getElementById("votings");
//   elem.innerHTML = "";
//   for (var i in data) {
//     var html =
//     `<tr>
//       <td>${data[i].name}</td>
//       <td>${data[i].votes}</td>
//       <td>${addButton(data[i])}</td>
//     </tr>`;
//     elem.innerHTML += html;
//   }
//
//   function addButton(e) {
//     return (`<div class="btn-group w-100">
//       <button class="btn btn-primary" onClick="addVote('` + e.imdb_id + `')">Vote</button>
//       <button class="btn btn-success" onClick="addMovie('` + e.imdb_id + `')">Add</button>
//       </div>`)
//   }
// }
