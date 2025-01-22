document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.querySelector('section');
    signupForm.style.opacity = 0;

    setTimeout(() => {
        signupForm.style.transition = 'opacity 1s ease-in-out';
        signupForm.style.opacity = 1;
    }, 500);

    const signupButton = document.querySelector('button');
    signupButton.addEventListener('click', function () {
        const usernameInput = document.querySelector('input[type="text"][name=username]')
        const passwordInput = document.querySelector('input[type="password"]');
        const confirmPasswordInput = document.querySelector('input[type="password"][name="confirm-password"]');

        let isValid = true;
        let errorMessage = '';

        if (usernameInput.value.length < 3) {
            errorMessage += 'Username must be at least 3 characters long.\n';
            isValid = false;
        }

        if (passwordInput.value.length < 6) {
            errorMessage += 'Password must be at least 6 characters long.\n';
            isValid = false;
        }

        if (passwordInput.value !== confirmPasswordInput.value) {
            errorMessage += 'Passwords do not match.\n';
            isValid = false;
        }

        if (!isValid) {
            alert(errorMessage);
            signupForm.classList.add('shake');

            setTimeout(() => {
                signupForm.classList.remove('shake');
            }, 1000);}else {

            sendRequest(usernameInput.value, passwordInput.value)
        }
    });
});

function sendRequest(username, password){

    const data = {
        username: username,
        password: password
    };

    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert('Registration successful');
                window.location.href = '/login'
            } else {
                alert('Error during registration');
            }
        })
        .catch(error => console.error('Error:', error));

}
