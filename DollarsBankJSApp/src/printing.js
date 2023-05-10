const Colors = {
    Black: '\u001b[30m',
    Red: '\u001b[31m',
    Green: '\u001b[32m',
    Yellow: '\u001b[33m',
    Blue: '\u001b[34m',
    Magenta: '\u001b[35m',
    Cyan: '\u001b[36m',
    White: '\u001b[37m',
    Reset: '\u001b[0m'
}

const print = (string) => {
    console.log(string)
}

const colorPrint = (color, string) => {
    console.log(color+string+Colors.Reset)
}

const setColor = (color) => {
    console.log(color)
}

module.exports = {
    print, colorPrint, Colors, setColor
}