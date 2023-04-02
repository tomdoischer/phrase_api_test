$(document).ready(function () {
    loadData()
});

window.setInterval(function () {
    loadData();
}, 5000);

function loadData() {
    const userId = sessionStorage.getItem('phraseUserId');
    if (userId !== null) {
        $.ajax({
            url: 'http://localhost:8080/projects/1',
            type: 'GET',
            dataType: 'json',
            success: function (response, statusText) {
                populateField(response);
            }
        });
    } else {
        const content = document.getElementById("content");
        content.innerText = "You're likely not logged in. Log in in account management."
    }


}

function populateField(response) {
    const projectsTableBody = document.querySelector("#projects-table > tbody");
    while (projectsTableBody.firstChild) {
        projectsTableBody.removeChild(projectsTableBody.firstChild);
    }
    response.forEach((row) => {
        const tr = document.createElement("tr");
        const name = document.createElement("td");
        name.textContent = row.name;
        tr.appendChild(name);
        //
        const status = document.createElement("td");
        status.textContent = row.status;
        tr.appendChild(status);
        //
        const sourceLang = document.createElement("td");
        sourceLang.textContent = row.sourceLang;
        tr.appendChild(sourceLang);
        //
        const targetLangs = document.createElement("td");
        targetLangs.textContent = row.targetLangs;
        tr.appendChild(targetLangs);

        projectsTableBody.appendChild(tr);
    });
}