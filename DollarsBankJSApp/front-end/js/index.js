var programMemory = {}

// Set onclick event for loginModal's sign-in button
const loginButton = document.getElementById('login-button')
loginButton.onclick = () => {
    const username = document.getElementById('login-username')
    const password = document.getElementById('login-password')
    login(username, password)
}

function login(username, password) {
    alert("Clicked login!")

    // Store credentials in browser's localstorage
    localStorage.setItem('username', 'username')
    localStorage.setItem('password', 'password')

    // Redirect to account homepage
    window.location.href="account.html"
}