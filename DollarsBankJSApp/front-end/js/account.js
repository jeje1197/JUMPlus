var programMemory = undefined

function preload() {
    const jsonData = localStorage.getItem('programMemory')
    programMemory = JSON.parse(jsonData)

    if (!programMemory.currentAccount) {
        logout()
    }
}

function storeProgramState() {
    localStorage.setItem('programMemory', JSON.stringify(programMemory))
}

function updateDisplay() {
    const welcomeHeader = document.getElementById('account-welcome')
    const balanceHeader = document.getElementById('account-balance')
    const accountTypeHeader = document.getElementById('account-type')
    const transactionList = document.getElementById('transaction-list')

    // Load welcome message and balance
    welcomeHeader.innerText = `Welcome, ${programMemory.currentAccount.name}!`
    balanceHeader.innerText = `Account Balance: $${programMemory.currentAccount.balance}`

    // Load account type
    accountTypeHeader.innerText = `${programMemory.currentAccount.type} Account`

    // Load transaction list
    if (programMemory.currentAccount.transactions.length > 0) {
        transactionList.innerHTML = programMemory.currentAccount.transactions.map((transaction) => {
            return `<li class="list-group-item">${transaction}</li>`
        }).join('')
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
    const transferSubmitButton = document.getElementById('transfer-submit-button')
    const switchAccountButton = document.getElementById('switch-account-button')
    const logoutButton = document.getElementById('logout-button')

    depositSubmitButton.onclick = deposit
    withdrawSubmitButton.onclick = withdraw
    transferSubmitButton.onclick = transferBetweenAccounts
    switchAccountButton.onclick = switchAccount
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

function transferBetweenAccounts(event) {
    event.preventDefault()
    const fromAccount = document.getElementById('transfer-from-select-element').value
    const toAccount = document.getElementById('transfer-to-select-element').value
    const transferAmount = Number(document.getElementById('transfer-amount').value)

    let checkingAccount = undefined
    let savingsAccount = undefined

    if (programMemory.currentAccount.type === "Checking") {
        checkingAccount = programMemory.currentAccount
        savingsAccount = programMemory.currentAccount.linkedAccount
    } else {
        savingsAccount = programMemory.currentAccount
        checkingAccount = programMemory.previousAccount
    }

    if (fromAccount === toAccount) {
        alert(`Cannot transfer money to same account. ${fromAccount} -> ${toAccount}`)
        return;
    } 
    
    if (fromAccount === "Checking" && (transferAmount <= 0 || transferAmount > checkingAccount.balance)) {
        alert(`Transfer amount must be greater than $0.00 and not exceed $${programMemory.currentAccount.balance}`)
        return;
    } else if (fromAccount === "Savings" && (transferAmount <= 0 || transferAmount > savingsAccount.balance)) {
        alert(`Transfer amount must be greater than $0.00 and not exceed $${programMemory.currentAccount.balance}`)
        return;
    }

    if (fromAccount === "Checking") {
        checkingAccount.balance -= transferAmount
        savingsAccount.balance += transferAmount
        addTransaction(checkingAccount, `Transferred $${transferAmount} to Savings Account`)
        addTransaction(savingsAccount, `Received $${transferAmount} from Checking Account transfer`)
    } else {
        savingsAccount.balance -= transferAmount
        checkingAccount.balance += transferAmount
        addTransaction(savingsAccount, `Transferred $${transferAmount} to Checking Account`)
        addTransaction(checkingAccount, `Received $${transferAmount} from Savings Account tranfer`)
    }
    storeProgramState()
    alert(`Successfully transferred $${transferAmount} from ${fromAccount} account to ${toAccount} account!`)
    window.location.reload()
}

function addTransaction(account, message) {
    account.transactions.push(`[${hashCode(new String(account.accountNumber))}-${account.transactions.length + 1}] ` + message + " on " + new Date())
}

function switchAccount() {
    if (programMemory.currentAccount.type === "Checking" && programMemory.currentAccount.linkedAccount) {
        programMemory.previousAccount = programMemory.currentAccount
        programMemory.currentAccount = programMemory.currentAccount.linkedAccount
    } else if (programMemory.currentAccount.type === "Savings") {
        programMemory.currentAccount = programMemory.previousAccount
    }

    storeProgramState()
    window.location.reload()
}

function logout() {
    programMemory.currentAccount = undefined
    programMemory.previousAccount = undefined
    storeProgramState()
    window.location.href = "./index.html"
}

// Call functions
preload()
updateDisplay()
setupOnclickListeners()
