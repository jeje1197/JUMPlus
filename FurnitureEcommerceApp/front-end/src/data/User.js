const UserManager = {
    createNewUser: (username, password) => {
        return {
            username,
            password,
            cart: [],
            orders: []
        }
    }
}

module.exports = {
    UserManager
}