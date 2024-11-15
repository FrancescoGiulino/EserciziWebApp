const API_PIATTI_URL = 'http://localhost:8080/piatti';

//funzione per caricare tutti i piatti dal database.
async function loadPiatti() {
    const response = await fetch(API_PIATTI_URL);
    const piatti = await response.json();

    const tableBody = document.querySelector('#piattiTable tbody');
    tableBody.innerHTML = '';

    piatti.forEach(piatto => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${piatto.id}</td>
            <td>${piatto.nome}</td>
            <td>${piatto.descrizione}</td>
            <td>
                <button onclick="editPiatto(${piatto.id})">Edit</button>
                <button onclick="deletePiatto(${piatto.id})">Delete</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

//funzione per aggiungere un nuovo piatto.
async function addPiatto() {
    const nome = document.getElementById('nomePiatto').value;
    const descrizione = document.getElementById('descrizionePiatto').value;

    const newPiatto = {
        nome,
        descrizione
    };

    await fetch(API_PIATTI_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newPiatto)
    });

    document.getElementById('addPiattoForm').reset();
    loadPiatti();
}

//funzione per cancellare un piatto.
async function deletePiatto(id) {
    await fetch(`${API_PIATTI_URL}/${id}`, {
        method: 'DELETE'
    });
    loadPiatti();
}

//funzione per editare un piatto.
async function editPiatto(id) {
    const nome = prompt('Inserisci il nuovo nome:');
    const descrizione = prompt('Inserisci la nuova descrizione:');

    const updatedPiatto = { nome, descrizione };

    await fetch(`${API_PIATTI_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedPiatto)
    });
    loadPiatti();
}

//carica tutti i piatti all'avvio.
window.onload = loadPiatti;
