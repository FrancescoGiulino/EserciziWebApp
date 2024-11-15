const API_BASE_URL = 'http://localhost:8080/ristoranti';
const API_PIATTI_URL = 'http://localhost:8080/piatti';

// Carica tutti i ristoranti
async function loadRistoranti() {
    try {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) throw new Error('Errore nel caricamento dei ristoranti');
        const ristoranti = await response.json();

        const tableBody = document.querySelector('#ristorantiTable tbody');
        tableBody.innerHTML = '';

        ristoranti.forEach(ristorante => {
            const piattiList = ristorante.piatti ? ristorante.piatti.map(piatto => piatto.nome).join(', ') : 'Nessuno';

            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${ristorante.id}</td>
                <td>${ristorante.nome}</td>
                <td>${ristorante.descrizione}</td>
                <td>${ristorante.ubicazione}</td>
                <td>${piattiList}</td>
                <td>
                    <button onclick="editRistorante(${ristorante.id})">Edit</button>
                    <button onclick="deleteRistorante(${ristorante.id})">Delete</button>
                </td>
            `;
            tableBody.appendChild(row);
        });

        // Popola il dropdown dei ristoranti per il collegamento con i piatti
        populateRistoranteDropdown();

        // Popola il dropdown dei ristoranti per la rimozione dei piatti
        populateRistoranteRemoveDropdown(ristoranti);
    } catch (error) {
        console.error('Errore:', error);
    }
}

// Funzione per aggiungere un nuovo ristorante
async function addRistorante() {
    const nome = document.getElementById('nomeRistorante').value;
    const descrizione = document.getElementById('descrizioneRistorante').value;
    const ubicazione = document.getElementById('ubicazioneRistorante').value;

    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome, descrizione, ubicazione })
        });

        if (!response.ok) throw new Error('Errore durante l\'aggiunta del ristorante');

        // Ricarica la lista dei ristoranti dopo aver aggiunto uno nuovo
        loadRistoranti();
    } catch (error) {
        console.error('Errore:', error);
    }
}

// Funzione per modificare un ristorante
async function editRistorante(ristoranteId) {
    const nuovoNome = prompt("Inserisci il nuovo nome:");
    const nuovaDescrizione = prompt("Inserisci la nuova descrizione:");
    const nuovaUbicazione = prompt("Inserisci la nuova ubicazione:");

    if (!nuovoNome || !nuovaDescrizione || !nuovaUbicazione) return;

    try {
        await fetch(`${API_BASE_URL}/${ristoranteId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome: nuovoNome, descrizione: nuovaDescrizione, ubicazione: nuovaUbicazione })
        });

        // Ricarica i ristoranti aggiornati
        loadRistoranti();
    } catch (error) {
        console.error('Errore:', error);
    }
}

// Funzione per cancellare un ristorante
async function deleteRistorante(ristoranteId) {
    if (!confirm('Sei sicuro di voler eliminare questo ristorante?')) return;

    try {
        await fetch(`${API_BASE_URL}/${ristoranteId}`, {
            method: 'DELETE'
        });

        // Ricarica la lista dopo la cancellazione
        loadRistoranti();
    } catch (error) {
        console.error('Errore:', error);
    }
}

// Funzione per popolare il dropdown dei ristoranti
function populateRistoranteDropdown() {
    const ristoranteSelect = document.getElementById('ristoranteSelect');
    fetch(API_BASE_URL)
        .then(response => response.json())
        .then(ristoranti => {
            ristoranteSelect.innerHTML = '<option value="">Seleziona Ristorante</option>';
            ristoranti.forEach(ristorante => {
                const option = document.createElement('option');
                option.value = ristorante.id;
                option.text = ristorante.nome;
                ristoranteSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Errore nel caricamento dei ristoranti:', error));
}

// Funzione per popolare il dropdown dei ristoranti per la rimozione dei piatti
function populateRistoranteRemoveDropdown(ristoranti) {
    const ristoranteRemoveSelect = document.getElementById('ristoranteRemoveSelect');
    ristoranteRemoveSelect.innerHTML = '<option value="">Seleziona Ristorante</option>';

    ristoranti.forEach(ristorante => {
        const option = document.createElement('option');
        option.value = ristorante.id;
        option.text = ristorante.nome;
        ristoranteRemoveSelect.appendChild(option);
    });
}

// Funzione per caricare i piatti per il ristorante selezionato (per rimuovere)
async function populatePiattoRemoveDropdown() {
    const ristoranteId = document.getElementById('ristoranteRemoveSelect').value;
    const piattoRemoveSelect = document.getElementById('piattoRemoveSelect');

    if (!ristoranteId) {
        piattoRemoveSelect.innerHTML = '<option value="">Seleziona Ristorante</option>';
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/${ristoranteId}`);
        const ristorante = await response.json();

        piattoRemoveSelect.innerHTML = '<option value="">Seleziona Piatto</option>';
        if (ristorante.piatti) {
            ristorante.piatti.forEach(piatto => {
                const piattoOption = document.createElement("option");
                piattoOption.value = piatto.id;
                piattoOption.text = piatto.nome;
                piattoRemoveSelect.appendChild(piattoOption);
            });
        }
    } catch (error) {
        console.error('Errore nel caricamento dei piatti:', error);
    }
}

// Funzione per rimuovere un piatto da un ristorante
async function removePiattoFromRistorante() {
    const ristoranteId = document.getElementById('ristoranteRemoveSelect').value;
    const piattoId = document.getElementById('piattoRemoveSelect').value;

    if (!ristoranteId || !piattoId) {
        alert("Per favore, seleziona sia un ristorante che un piatto.");
        return;
    }

    try {
        // Invia la richiesta DELETE per rimuovere il piatto
        const response = await fetch(`${API_BASE_URL}/${ristoranteId}/piatti/${piattoId}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error('Errore durante la rimozione del piatto');

        // Ricarica i ristoranti aggiornati
        loadRistoranti();
        alert('Piatto rimosso correttamente!');
    } catch (error) {
        console.error('Errore:', error);
        alert('Errore nella rimozione del piatto.');
    }
}

// Carica tutti i piatti e popola il dropdown dei piatti
async function loadPiattiDropdown() {
    const response = await fetch(API_PIATTI_URL);
    const piatti = await response.json();
    const piattoSelect = document.getElementById('piattoSelect');

    piattoSelect.innerHTML = '<option value="">Seleziona Piatto</option>';
    piatti.forEach(piatto => {
        const option = document.createElement('option');
        option.value = piatto.id;
        option.text = piatto.nome;
        piattoSelect.appendChild(option);
    });
}

// Funzione per collegare un piatto a un ristorante
async function linkPiattoToRistorante() {
    const ristoranteId = document.getElementById('ristoranteSelect').value;
    const piattoId = document.getElementById('piattoSelect').value;

    if (!ristoranteId || !piattoId) {
        alert('Seleziona sia un ristorante che un piatto.');
        return;
    }

    try {
        await fetch(`${API_BASE_URL}/${ristoranteId}/piatti/${piattoId}`, {
            method: 'POST'
        });
        loadRistoranti();
    } catch (error) {
        console.error('Errore nel collegamento del piatto al ristorante:', error);
    }
}

// Carica tutti i dati all'avvio
window.onload = () => {
    loadRistoranti();
    loadPiattiDropdown();
};
