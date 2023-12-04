document.addEventListener("DOMContentLoaded", function() {
    var card = document.querySelector(".card");
    var loginBtn = document.getElementById("loginBtn");
    var registerBtn = document.getElementById("registerBtn");
    var showRegisterBtn = document.getElementById("showRegisterBtn");
    var showLoginBtn = document.getElementById("showLoginBtn");

    loginBtn.addEventListener("click", function() {
        Swal.fire({
            title: "Login Successful",
            // text: "Entering...",
            icon: "success"
          });
    });

    registerBtn.addEventListener("click", function() {
        alert("You clicked the button!"); //other option
    });

    showRegisterBtn.addEventListener("click", function() {
        card.classList.add("flip");

        document.getElementById("front").classList.add("disable-mouse-events")
        document.getElementById("back").classList.remove("disable-mouse-events")
    });

    showLoginBtn.addEventListener("click", function() {
        card.classList.remove("flip");

        document.getElementById("front").classList.remove("disable-mouse-events")
        document.getElementById("back").classList.add("disable-mouse-events")
    });
});