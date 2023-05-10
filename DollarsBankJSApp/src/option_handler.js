const { print, setColor, Colors, colorPrint } = require('./printing')

const prompt = require('prompt-sync')({sigint: true})

const getInt = (min, max) => {
    const value = prompt(Colors.Green)
    setColor(Colors.Reset)
    if (!value.trim().match(/^[0-9]*$/) || value < min || value > max) {
        throw `Expected integer value between ${min} and ${max}.`
    }
    return Number(value)
}

const getPin = () => {
    const value = prompt(Colors.Green)
    setColor(Colors.Reset)
    if (!value.trim().match(/^[0-9]{4}$/)) {
        throw `Expected 4-digit number in format xxxx.`
    }
    return Number(value)
}

const getDouble = (min, max) => {
    const value = prompt(Colors.Green + "$")
    setColor(Colors.Reset)
    if (!value.trim().match(/^[0-9]+(.[0-9]+)?$/) || value < min || value > max) {
        throw `Expected number between $${min} and $${max}.`
    }
    return Number(value)
}

const getYN = () => {
    colorPrint(Colors.Blue, "Perform another transaction: (y/n)?")
    const value = prompt(Colors.Green)
    setColor(Colors.Reset)
    if (!value.trim().match(/^(y|Y|n|N)$/)) {
        print("")
        return getYN()
    }
    return value === 'y' || value === 'Y'
}

module.exports = {
    getInt, getDouble, getPin, getYN
}