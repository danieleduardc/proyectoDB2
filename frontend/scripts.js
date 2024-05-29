document.getElementById('login-btn').addEventListener('click', function() {
    let correo = document.getElementById('correo').value;
    const contrasena = document.getElementById('contrasena').value;

    fetch('http://localhost:8081/api/login', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ correo, contrasena })
    })
    .then(response => {
        if (response.status === 200) {
            window.location.href = 'http://localhost:5500/frontend/inicio.html';
        } else {
            response.json().then(data => {
                alert(data.message || 'Error en el inicio de sesión'); 
            });
        }
    })
    .catch(error => {
        alert(error.message);
    });
});

