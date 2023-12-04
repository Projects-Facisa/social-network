document.addEventListener("DOMContentLoaded", function() {
    var card = document.querySelector(".card");
    var loginBtn = document.getElementById("loginBtn");
    var registerBtn = document.getElementById("registerBtn");
    var showRegisterBtn = document.getElementById("showRegisterBtn");
    var showLoginBtn = document.getElementById("showLoginBtn");

    loginBtn.addEventListener("click", function() {
        // ADICIONAR LOGICA DE LOGIN
    });

    registerBtn.addEventListener("click", function() {
        // ADICIONAR LOGICA DE REGISTRO
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