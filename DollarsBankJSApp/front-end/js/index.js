var programMemory = {
    accounts: [],
    currentAccount: undefined
}

// Set onclick event for loginModal's sign-in button
const loginButton = document.getElementById('login-button')
loginButton.onclick = () => {
    const username = document.getElementById('login-username').value
    const password = document.getElementById('login-password').value
    // console.log(username, password)
    login(username, password)
}

function login(username, password) {
    // Store credentials in browser's localstorage
    localStorage.setItem('username', username)
    localStorage.setItem('password', password)

    // Redirect to account homepage
    if (validate(username, password)) {
        window.location.href = "./account.html"
    } else {
        localStorage.setItem('username', undefined)
        localStorage.setItem('password', undefined)
        alert('Invalid login credentials.')
    }
    
}

function validate(username, password) {
    for (const acc of programMemory.accounts) {
        if (acc.username === username && acc.password === password) {
            programMemory.currentAccount = acc
        }
    }

    programMemory.currentAccount = undefined
}