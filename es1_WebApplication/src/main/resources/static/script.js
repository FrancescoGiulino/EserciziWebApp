let btnAdd = document.querySelector('#submit_button');
let table = document.querySelector('#album_table tbody');

let titleInput = document.querySelector('#input_titolo');
let yearInput = document.querySelector('#input_anno');
let nsongInput = document.querySelector('#input_ncanzoni');

// Function to add new album row
btnAdd.addEventListener('click', () => {
    let title = titleInput.value;
    let year = yearInput.value;
    let nsong = nsongInput.value;

    let template = `<tr>
                        <td>${title}</td>
                        <td>${year}</td>
                        <td>${nsong}</td>
                        <td><button class="btn btn-danger btn-sm deleteButton">Rimuovi <i class="fa-solid fa-trash"></i></button></td>
                    </tr>`;

    table.innerHTML += template;
    addDeleteEvent(); // Attach event listener to new delete button
});

// Function to attach delete event to all delete buttons
function addDeleteEvent() {
    let deleteButtons = document.querySelectorAll('.deleteButton');
    deleteButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            e.target.closest('tr').remove();
        });
    });
}

// Initial call to ensure delete event is attached to existing rows
addDeleteEvent();
