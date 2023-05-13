var programMemory = undefined

function preload() {
    const jsonData = localStorage.getItem('programMemory')
    programMemory = JSON.parse(jsonData)

    if (!programMemory.currentAccount) {
        window.location.href = "./index.html"
    }
}

function updateDisplay() {
    const welcomeHeader = document.getElementById('account-welcome')
    const balanceHeader = document.getElementById('account-balance')
    const transactionList = document.getElementById('transaction-list')

    // Load welcome message and balance
    welcomeHeader.innerText = `Welcome, ${programMemory.currentAccount.name}!`
    balanceHeader.innerText = `Account Balance: $${programMemory.currentAccount.balance}`

    // Load transaction list
    if (programMemory.currentAccount.transactions.length > 0) {
        transactionList.innerHTML = programMemory.currentAccount.transactions.map((transaction) => {
            return `<li class="list-group-item">{ ${transaction} }</li>`
        })
    } else {
        transactionList.innerHTML = `<li class="list-group-item"> No Transactions </li>`
    }
}

function setupOnclickListeners() {
    const logoutButton = document.getElementById('logout-button')

    logoutButton.onclick = logout
}

function logout() {
    programMemory.currentAccount = undefined
    localStorage.setItem('programMemory', JSON.stringify(programMemory))
    window.location.href = "./index.html"
}


// Call functions
preload()
updateDisplay()
setupOnclickListeners()