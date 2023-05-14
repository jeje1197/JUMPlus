var programMemory = undefined

function preload() {
    const jsonData = localStorage.getItem('programMemory')
    programMemory = JSON.parse(jsonData)

    if (!programMemory.currentAccount) {
        window.location.href = "./index.html"
    }
}

function storeProgramState() {
    localStorage.setItem('programMemory', JSON.stringify(programMemory))
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
            return `<li class="list-group-item">${transaction}</li>`
        })
    } else {
        transactionList.innerHTML = `<li class="list-group-item"> No Transactions </li>`
    }

    // Load customer information in modal
    const customerNameDisplay = document.getElementById('customer-name')
    const customerEmailDisplay = document.getElementById('customer-email')
    const customerUsernameDisplay = document.getElementById('customer-username')

    customerNameDisplay.innerText = programMemory.currentAccount.name
    customerEmailDisplay.innerText = programMemory.currentAccount.email
    customerUsernameDisplay.innerText = programMemory.currentAccount.username
}

function setupOnclickListeners() {
    const depositSubmitButton = document.getElementById('deposit-submit-button')
    const withdrawSubmitButton = document.getElementById('withdraw-submit-button')
    const logoutButton = document.getElementById('logout-button')

    depositSubmitButton.onclick = deposit
    withdrawSubmitButton.onclick = withdraw
    logoutButton.onclick = logout
}

function deposit(event) {
    event.preventDefault()
    const depositAmount = Number(document.getElementById('deposit-amount').value)
    
    if (depositAmount <= 0) {
        alert('Cannot make deposit. Deposit amount must be greater than $0.00')
    } else {
        programMemory.currentAccount.balance += depositAmount
        addTransaction(programMemory.currentAccount, `Deposit $${depositAmount}`)
        storeProgramState()
        alert(`Successfully deposited ${depositAmount}!`)
        window.location.reload()
    }
}

function withdraw(event) {
    event.preventDefault()
    const withdrawAmount = Number(document.getElementById('withdraw-amount').value)
    
    if (withdrawAmount <= 0 || withdrawAmount > programMemory.currentAccount.balance) {
        alert(`Cannot make withdrawl. Withdrawl amount must be greater than $0.00 and less than ${programMemory.currentAccount.balance}`)
    } else {
        programMemory.currentAccount.balance -= withdrawAmount
        addTransaction(programMemory.currentAccount, `Withdrew $${withdrawAmount}`)
        storeProgramState()
        alert(`Successfully deposited ${withdrawAmount}!`)
        window.location.reload()
    }
}

function addTransaction(account, message) {
    account.transactions.push(`[${hashCode(new String(account.accountNumber))}-${account.transactions.length + 1}] ` + message + " on " + new Date())
}

function logout() {
    programMemory.currentAccount = undefined
    storeProgramState()
    window.location.href = "./index.html"
}

// Call functions
preload()
updateDisplay()
setupOnclickListeners()
