// Validaciones y UX para la página de login
const loginForm = document.querySelector('.login-box');

function crearModal(mensaje, tipo = 'exito') {
    const overlay = document.createElement('div');
    overlay.style.position = 'fixed';
    overlay.style.top = '0';
    overlay.style.left = '0';
    overlay.style.width = '100%';
    overlay.style.height = '100%';
    overlay.style.background = 'rgba(0,0,0,0.5)';
    overlay.style.display = 'flex';
    overlay.style.justifyContent = 'center';
    overlay.style.alignItems = 'center';
    overlay.style.zIndex = '9999';

    const modal = document.createElement('div');
    modal.style.background = 'white';
    modal.style.padding = '25px';
    modal.style.borderRadius = '12px';
    modal.style.width = '320px';
    modal.style.textAlign = 'center';
    modal.style.boxShadow = '0 4px 15px rgba(0,0,0,0.3)';

    const icon = document.createElement('div');
    icon.style.fontSize = '36px';
    icon.style.marginBottom = '10px';
    icon.textContent = tipo === 'exito' ? '✅' : '❌';

    const texto = document.createElement('p');
    texto.textContent = mensaje;
    texto.style.marginBottom = '15px';

    const btn = document.createElement('button');
    btn.textContent = 'Aceptar';
    btn.style.background = '#2c5282';
    btn.style.color = 'white';
    btn.style.border = 'none';
    btn.style.padding = '8px 15px';
    btn.style.borderRadius = '8px';
    btn.style.cursor = 'pointer';

    btn.onclick = () => overlay.remove();

    modal.appendChild(icon);
    modal.appendChild(texto);
    modal.appendChild(btn);
    overlay.appendChild(modal);
    document.body.appendChild(overlay);
}

if (loginForm) {
    const emailInput = loginForm.querySelector('input[type="email"]');
    const passwordInput = loginForm.querySelector('input[type="password"]');

    // Precargar email guardado en localStorage (si existe)
    if (emailInput) {
        try {
            const guardado = localStorage.getItem('loginEmail');
            if (guardado) emailInput.value = guardado;
        } catch (err) {
            console.error('Error leyendo loginEmail de localStorage', err);
        }
    }

    // Toggle para ver/ocultar contraseña
    if (passwordInput) {
        const toggle = document.createElement('button');
        toggle.type = 'button';
        toggle.textContent = 'Mostrar';
        toggle.style.padding = '6px 8px';
        toggle.style.margin = '15px 0px';
        toggle.style.fontSize = '12px';
        toggle.style.cursor = 'pointer';
        toggle.onclick = () => {
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                toggle.textContent = 'Ocultar';
            } else {
                passwordInput.type = 'password';
                toggle.textContent = 'Mostrar';
            }
        };
        passwordInput.insertAdjacentElement('afterend', toggle);
    }

    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const email = emailInput ? emailInput.value.trim() : '';
        const password = passwordInput ? passwordInput.value.trim() : '';

        if (!email || !password) {
            crearModal('Por favor complete el correo y la contraseña.', 'error');
            return;
        }

        const emailRegex = /\S+@\S+\.\S+/;
        if (!emailRegex.test(email)) {
            crearModal('Ingrese un correo válido.', 'error');
            return;
        }

        // Guardar el correo en localStorage para precarga futura
        try {
            localStorage.setItem('loginEmail', email);
        } catch (err) {
            console.error('Error guardando loginEmail en localStorage', err);
        }

        // Simular autenticación rápida y enviar
        crearModal('Inicio de sesión correcto. Redirigiendo...', 'exito');
        setTimeout(() => {
            loginForm.submit();
        }, 700);
    });
}
