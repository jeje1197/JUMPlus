// Setup environment
var programMemory = undefined;

function preload() {
    programMemory = {
        accounts: [new Account("Checking", 500, "Big Joe", "bigjoe@gmail.com", "bigjoe", "password", true)],
        currentAccount: undefined
    }
    
    localStorage.setItem('programMemory', JSON.stringify(programMemory))
}

preload()

// Set onclick event for loginModal's sign-in button
const loginButton = document.getElementById('login-button')
loginButton.onclick = () => {
    const username = document.getElementById('login-username').value
    const password = document.getElementById('login-password').value
    login(username, password)
}

function login(username, password) {
    if (validate(username, password)) {
        // Store program state in browser's localstorage
        localStorage.setItem('programMemory', JSON.stringify(programMemory))
        window.location.href = "./account.html"
    } else {
        localStorage.setItem('programMemory', JSON.stringify(undefined))
        alert('Invalid login credentials.')
    }
}

function validate(username, password) {
    for (let acc of programMemory.accounts) {
        if (acc.username === username && acc.password === password) {
            programMemory.currentAccount = acc
            return true
        }
    }

    programMemory.currentAccount = undefined
    return false
}