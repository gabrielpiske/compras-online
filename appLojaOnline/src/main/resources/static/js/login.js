function showErrorPopup(message) {
    const popup = document.getElementById('error-popup');
    const messageSpan = document.getElementById('error-message');
    messageSpan.innerText = message;
    popup.style.display = 'flex';

    // Fechar automaticamente após 5 segundos
    setTimeout(() => {
        popup.style.display = 'none';
    }, 5000);
}

function closePopup() {
    document.getElementById('error-popup').style.display = 'none';
}

// Capturar erro da URL
window.onload = function () {
    const params = new URLSearchParams(window.location.search);
    if (params.has('error')) {
        showErrorPopup("Usuário ou senha incorretos!");
    }
};

document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("#register-form form");

    form.addEventListener("submit", function (event) {
        let password = document.getElementById("register-password").value;
        let passwordConfirm = document.getElementById("register-passwordconfirm").value;

        if (password !== passwordConfirm) {
            showErrorPopup("As senhas não coincidem!");
            event.preventDefault(); // Impede o envio do formulário
        }
    });
});