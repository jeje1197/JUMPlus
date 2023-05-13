var programMemory = undefined

function preload() {
    const jsonData = localStorage.getItem('programMemory')
    programMemory = JSON.parse(jsonData)

    if (!programMemory.currentAccount) {
        window.href = "./index.html"
    }
}

function updateDisplay() {
    const welcomeHeader = document.getElementById('account-welcome')
    const balanceHeader = document.getElementById('account-balance')

    welcomeHeader.innerText = `Welcome, ${programMemory.currentAccount.name}!`
    balanceHeader.innerText = `Account Balance: $${programMemory.currentAccount.balance}`

    

}

preload()
updateDisplay()