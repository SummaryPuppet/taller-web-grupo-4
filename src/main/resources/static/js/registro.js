// Validaciones y UX para la página de registro
const registerForm = document.querySelector('.register-box');

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
    modal.style.width = '360px';
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

if (registerForm) {
    const nameInput = registerForm.querySelector('input[type="text"]');
    const emailInput = registerForm.querySelector('input[type="email"]');
    const passwordInputs = registerForm.querySelectorAll('input[type="password"]');
    const passwordInput = passwordInputs[0];
    const confirmInput = passwordInputs[1];

    registerForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const nombre = nameInput ? nameInput.value.trim() : '';
        const email = emailInput ? emailInput.value.trim() : '';
        const pass = passwordInput ? passwordInput.value : '';
        const confirm = confirmInput ? confirmInput.value : '';

        if (!nombre || !email || !pass || !confirm) {
            crearModal('Por favor complete todos los campos.', 'error');
            return;
        }

        if (nombre.length < 3) {
            crearModal('El nombre debe tener al menos 3 caracteres.', 'error');
            return;
        }

        const emailRegex = /\S+@\S+\.\S+/;
        if (!emailRegex.test(email)) {
            crearModal('Ingrese un correo válido.', 'error');
            return;
        }

        if (pass.length < 6) {
            crearModal('La contraseña debe tener al menos 6 caracteres.', 'error');
            return;
        }

        if (pass !== confirm) {
            crearModal('Las contraseñas no coinciden.', 'error');
            return;
        }

        // Éxito: mostrar modal y redirigir a login (o enviar formulario)
        crearModal('Registro exitoso. Redirigiendo al inicio de sesión...', 'exito');
        setTimeout(() => {
            // La plantilla actual envía a /login; mantener ese comportamiento
            window.location.href = '/login';
        }, 900);
    });
}
