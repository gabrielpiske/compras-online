function preencherFormulario(botao) {
    // Obtém os dados do botão clicado
    let id = botao.getAttribute("data-id");
    let name = botao.getAttribute("data-name");
    let address = botao.getAttribute("data-address");
    let description = botao.getAttribute("data-description");

    // Preenche os campos do formulário principal
    document.getElementById("store-id").value = id;
    document.getElementById("name").value = name;
    document.getElementById("address").value = address;
    document.getElementById("description").value = description;

    // Altera o título e o botão do formulário
    document.getElementById("form-title").innerText = "Editar Loja";
    document.getElementById("form-submit-btn").innerText = "Salvar Alterações";

    // Modifica a ação do formulário para enviar uma atualização
    document.getElementById("store-form").setAttribute("action", "/store/update");
}

function verifyEdit() {
    let titulo = document.getElementById("form-title").innerText;

    if (titulo === "Editar Loja") {
        document.getElementById("form-title").innerText = "Cadastrar Loja";
        document.getElementById("form-submit-btn").innerText = "Cadastrar";
        document.getElementById("store-form").removeAttribute;

        document.getElementById("store-id").value = "";
    }
}