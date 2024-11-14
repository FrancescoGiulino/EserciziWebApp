window.addEventListener("load", function () {
    btn_aggiungi.addEventListener("click", aggiungiRistorante)
});

//funzione per aggiungere un ristorante e aggiornare la tabella
function aggiungiRistorante() {
    var nome = document.querySelector("#nome_rist").value;
    var descrizione = document.querySelector("#desc_rist").value;
    var ubicazione = document.querySelector("#ub_ristorante").value;

    var ristorante = {
        nome: nome,
        descrizione: descrizione,
        ubicazione: ubicazione
    };

    $.ajax({
        url: "/addRistorante", //URL dell'endpoint che aggiunge il ristorante
        type: "POST", // Cambia il metodo a POST
        contentType: "application/json", // Imposta il tipo di contenuto come JSON
        data: JSON.stringify(ristorante), // Invia i dati come stringa JSON
        success: function(response) {
            console.log("Ristorante aggiunto con successo");
            aggiornaTabellaRistoranti(); //aggiorna la lista dei ristoranti
        },
        error: function(response) {
            alert("Errore nell'aggiungere il ristorante");
        }
    });
}


$(document).ready(function() {
    aggiornaTabellaRistoranti(); //carica i ristoranti appena la pagina è pronta
});

//funzione per aggiornare la tabella dei ristoranti
function aggiornaTabellaRistoranti() {
    $.ajax({
        url: "/elencoRistoranti", //URL dell'endpoint per ottenere tutti i ristoranti
        type: "GET", // Metodo HTTP
        success: function(response) {
            console.log(response); // Aggiungi questo per vedere cosa restituisce il server
            //pulisci la tabella esistente
            var tbody = document.querySelector("#tabella-ristoranti tbody");
            tbody.innerHTML = ""; // Rimuove tutte le righe esistenti

            //popola la tabella con i ristoranti
            response.forEach(function(ristorante) {
                var row = document.createElement("tr");
                row.innerHTML = `
                    <td>${ristorante.nome}</td>
                    <td>${ristorante.descrizione}</td>
                    <td>${ristorante.ubicazione}</td>
                `;
                tbody.appendChild(row); // Aggiungi la nuova riga alla tabella
            });
        },
        error: function(response) {
            alert("Errore nel recuperare i ristoranti");
        }
    });
}

//aggiungi un evento per il click del bottone di aggiunta
document.querySelector("#btn_aggiungi").addEventListener("click", aggiungiRistorante);
