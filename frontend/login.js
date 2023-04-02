const form = document.getElementById("form");
const changeAccountForm = document.getElementById("change-account-form");
const username = document.getElementById("username");
const password = document.getElementById("password");
const welcomeHeader = document.getElementById("welcome-header");
const editAccountButton = document.getElementById("edit-account");
let usernameReceived = null;

changeAccountForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let data = {
        username: username.value,
        password: password.value,
    };

    changeAccount(data);
});

window.onload = function () {
    renderLoginAndChangeAccountForm();
};

form.addEventListener("submit", (e) => {
    e.preventDefault();
    let data = {
        username: username.value,
        password: password.value,
    };

    login(data);
});

function editAccountClicked() {
    document.getElementById("change-account").style.display = "block";
    editAccountButton.style.display = "none";
}

function renderLoginAndChangeAccountForm() {
    if (userLoggedIn()) {
        const username = sessionStorage.getItem('phraseUsername');
        const loggedInAs = document.getElementById("loggedInAs");
        loggedInAs.innerText = "Logged in as " + username;
        document.getElementById("form-body").style.display = "none";
        editAccountButton.style.display = "block";
    } else {
        document.getElementById("form-body").style.display = "block";
        editAccountButton.style.display = "none";
    }
}

function login(data) {
    try {
        fetch("http://localhost:8080/account-configurations", {
            method: "POST",
            body: JSON.stringify(data),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
            },
        })
            .then((response) => response.json())
            .then((json) => {
                sessionStorage.setItem('phraseUserId', json.id);
                sessionStorage.setItem('phraseUsername', json.username);
                renderLoginAndChangeAccountForm();
                alert("Login successful");
            });
    } catch (error) {
        alert("login unsuccessful", error);
    }
}

function changeAccount(data) {
    const userId = sessionStorage.getItem('phraseUserId');
    fetch("http://localhost:8080/account-configurations/" + userId, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
    })
        .then((response) => {
            if (response.ok) {
                const json = response.json();
                usernameReceived = json.username;
                document.getElementById("form-body").style.display = "none";
                editAccountButton.style.display = "block";
                alert("Account change successful")
                renderLoginAndChangeAccountForm();
            } else {
                alert("Login unsuccessful", response.status, response.statusText);
            }
        });
}

function userLoggedIn() {
    const userId = sessionStorage.getItem('phraseUserId');
    return userId !== null;
}