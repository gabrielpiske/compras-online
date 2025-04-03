let intervalId; // Variável para armazenar o intervalo de atualização do horário
let isEditing = false; // Variável para controlar se está no modo de edição

function atualizarDataHora() {
    if (!isEditing) {
        // Apenas atualiza se não estiver no modo de edição
        const now = new Date();
        const dataHoraFormatada = formatarData(now);
        document.getElementById("data").value = dataHoraFormatada;
    }
}

// Inicia a atualização automática do horário
intervalId = setInterval(atualizarDataHora, 1000);
atualizarDataHora();

function formatarData(data) {
    return new Intl.DateTimeFormat("pt-BR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: false,
        timeZone: "America/Sao_Paulo",
    }).format(new Date(data));
}

function atualizarStoreId() {
    const selectLoja = document.getElementById("loja");
    document.getElementById("storeId").value = selectLoja.value;
}

function preencherFormulario(botao) {
    isEditing = true;
    clearInterval(intervalId);

    // Obtém os dados do botão clicado
    let id = botao.getAttribute("data-id");
    let usuario = botao.getAttribute("data-user");
    let lojaNome = botao.getAttribute("data-storeName");
    let lojaId = botao.getAttribute("data-storeId");
    let data = botao.getAttribute("data-date");

    // Preenche os campos do formulário principal
    document.getElementById("buy-id").value = id;
    document.getElementById("user").value = usuario;
    document.getElementById("storeId").value = lojaId;
    document.getElementById("data").value = formatarData(data);

    // Seleciona a loja correta no <select>
    let selectLoja = document.getElementById("loja");
    for (let option of selectLoja.options) {
        if (option.textContent.trim() === lojaNome.trim()) {
            option.selected = true;
            break;
        }
    }

    // Altera o título e o botão do formulário
    document.getElementById("form-title").innerText = "Editar Compra";
    document.getElementById("form-submit-btn").innerText = "Salvar Alterações";

    // Modifica a ação do formulário para enviar uma atualização
    document.getElementById("buy-form").setAttribute("action", "/buy/update");
}

function verifyEdit() {
    let titulo = document.getElementById("form-title").innerText;

    if (titulo === "Editar Compra") {
        isEditing = false; // Sai do modo de edição
        intervalId = setInterval(atualizarDataHora, 1000); // Retorna a atualização do horário

        document.getElementById("form-title").innerText = "Cadastrar Compra";
        document.getElementById("form-submit-btn").innerText = "Cadastrar";
        document.getElementById("buy-form").removeAttribute("action");

        document.getElementById("buy-id").value = "";
    }
}