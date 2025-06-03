const API_URL = "http://localhost:8080/veiculos";
const form = document.getElementById("veiculoForm");
const tabela = document.getElementById("tabelaVeiculos");

async function listarVeiculos() {
    const response = await fetch(API_URL);
    const veiculos = await response.json();
    tabela.innerHTML = "";
    veiculos.forEach(veiculo => {
        const especificacaoTexto =
            veiculo.potencia ||
            veiculo.carroceria ||
            veiculo.cilindradas ||
            veiculo.cv ||
            veiculo.capacidadePassageiros ||
            veiculo.capacidadeCarga ||
            "-";

        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${veiculo.id}</td>
            <td>${veiculo.tipo}</td>
            <td>${veiculo.marca}</td>
            <td>${veiculo.modelo}</td>
            <td>${veiculo.ano}</td>
            <td>${especificacaoTexto}</td>
            <td>
                <button onclick="editarVeiculo('${veiculo.id}')">Editar</button>
                <button onclick="deletarVeiculo('${veiculo.id}')">Excluir</button>
            </td>
        `;
        tabela.appendChild(tr);
    });
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.getElementById("id").value;

    const veiculo = {
        marca: document.getElementById("marca").value,
        modelo: document.getElementById("modelo").value,
        ano: parseInt(document.getElementById("ano").value),
        tipo: document.getElementById("tipo").value.toLowerCase(),
        potencia: document.getElementById("potencia")?.value || ""
    };

    const method = id ? "PUT" : "POST";
    const url = id ? `${API_URL}/${id}` : API_URL;

    await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(veiculo)
    });

    form.reset();
    document.getElementById("camposEspecificos").innerHTML = "";
    listarVeiculos();
    fecharModalCriar();
});

async function editarVeiculo(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const veiculo = await response.json();

    document.getElementById("id").value = veiculo.id;
    document.getElementById("marca").value = veiculo.marca;
    document.getElementById("modelo").value = veiculo.modelo;
    document.getElementById("ano").value = veiculo.ano;
    document.getElementById("tipo").value = veiculo.tipo;

    atualizarCamposEspecificos();
    document.getElementById("potencia").value = veiculo.potencia || "";

    document.getElementById("modalCriar").classList.remove("hidden");
}

async function deletarVeiculo(id) {
    if (confirm("Tem certeza que deseja excluir este veículo?")) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        listarVeiculos();
    }
}

function atualizarCamposEspecificos() {
    const container = document.getElementById("camposEspecificos");
    container.innerHTML = `
        <input type="text" id="potencia" placeholder="Potência (ex: 150cv)" required />
    `;
}

function abrirModalCriar() {
    form.reset();
    atualizarCamposEspecificos();
    document.getElementById("id").value = "";
    document.getElementById("modalCriar").classList.remove("hidden");
}

function fecharModalCriar() {
    document.getElementById("modalCriar").classList.add("hidden");
}

listarVeiculos();
