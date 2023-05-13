class Account {
    constructor(balance, name, email, username, password) {
        this.accountNumber = Math.floor(Math.random() * 100000);
        this.balance = balance
        this.name = name
        this.email = email
        this.username = username
        this.password = password
        this.transactions = []
    }

    getAccountNumber() {
        return this.accountNumber
    }

    getBalance() {
        return this.balance
    }

    setBalance(balance) {
        this.balance = balance
    }

    getName() {
        return this.name
    }

    getEmail() {
        return this.email
    }

    getUsername() {
        return this.username
    }

    getPassword() {
        return this.password
    }

    getTransactions() {
        return this.transactions
    }

    addTransaction(message) {
        this.transactions.push(`[${hashCode(new String(this.accountNumber))}-${this.transactions.length + 1}] ` + message + " on " + new Date())
    }

    // printTransactions() {
    //     colorPrint(Colors.Blue, "----- Recent Transactions -----")
    //     if (this.transactions.length === 0) {
    //         print("No transactions\n")
    //     }

    //     for (let i = 0; i < 5 && i < this.transactions.length; i++) {
    //         console.log(this.transactions[this.transactions.length - 1 - i])
    //     }
    //     print("")
    // }
}

function hashCode(s) {
    let h = 0;
    for(let i = 0; i < s.length; i++)
        h = Math.imul(31, h) + s.charCodeAt(i) | 0;

    return h;
}