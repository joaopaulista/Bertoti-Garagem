const API_URL = "http://localhost:8080/carros";
const form = document.getElementById("carroForm");
const tabela = document.getElementById("tabelaCarros");

async function listarCarros() {
    const response = await fetch(API_URL);
    const carros = await response.json();
    tabela.innerHTML = "";
    carros.forEach(carro => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
      <td>${carro.id}</td>
      <td>${carro.marca}</td>
      <td>${carro.modelo}</td>
      <td>${carro.ano}</td>
      <td>${carro.tipo}</td>
      <td>
        <button onclick="editarCarro('${carro.id}')">Editar</button>
        <button onclick="deletarCarro('${carro.id}')">Excluir</button>
      </td>
    `;
        tabela.appendChild(tr);
    });
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("id").value;
    const carro = {
        marca: document.getElementById("marca").value,
        modelo: document.getElementById("modelo").value,
        ano: parseInt(document.getElementById("ano").value),
        tipo: document.getElementById("tipo").value
    };

    const method = id ? "PUT" : "POST";
    const url = id ? `${API_URL}/${id}` : API_URL;

    await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(carro)
    });

    form.reset();
    listarCarros();
});

async function editarCarro(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const carro = await response.json();

    document.getElementById("id").value = carro.id;
    document.getElementById("marca").value = carro.marca;
    document.getElementById("modelo").value = carro.modelo;
    document.getElementById("ano").value = carro.ano;
    document.getElementById("tipo").value = carro.tipo;
}

async function deletarCarro(id) {
    if (confirm("Tem certeza que deseja excluir este carro?")) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        listarCarros();
    }
}

listarCarros();
