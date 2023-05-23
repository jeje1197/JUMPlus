const FurnitureApi = {
    URL: 'http://localhost:3500',

    // Working
    // Return a list of users
    getUsers: async () => {
        const endpoint = FurnitureApi.URL + '/users'
        let users = []
        
        await fetch(endpoint)
        .then((response) => response.json())
        .then((json) => users = json)
        .catch((error) => console.log(error))
        
        return users
    },

    // Working - User automatically assigned id by JSON Server
    // Return true if added, otherwise false
    addUser: async (userData) => {
        // First check if username has already been claimed
        const users = await FurnitureApi.getUsers()

        for (let user of users) {
            if (userData.username === user.username){
                return false
            }
        }

        // Post user to db
        const endpoint = FurnitureApi.URL + '/users'
        const postOption = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        }

        await fetch(endpoint, postOption)
        return true
    },

    // Return user associated with credentials, otherwise undefined
    login: async (username, password) => {
        const users = await FurnitureApi.getUsers()

        for (let user of users) {
            if (username === user.username && password === user.password) {
                return user
            }
        }
        return undefined
    },

    // Gets user by id, otherwise undefined
    getUserById: async (id) => {
        const endpoint = FurnitureApi.URL + '/users/' + id
        let user = undefined
        
        await fetch(endpoint)
        .then((response) => response.json())
        .then((json) => user = json)
        .catch((error) => console.log(error))
        
        return user
    },

    // Updates user by id
    // Returns true if successful, otherwise false
    updateUserById: async (id, userData) => {
        const endpoint = FurnitureApi.URL + '/users/' + id

        const postOption = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        }

        await fetch(endpoint, postOption)
        return true
    },

    // Gets a list of orders for a user by id
    getOrders: async (id) => {
        const user = await FurnitureApi.getUserById(id)
        return user.orders
    },

    // Add an order to a users's list of orders
    addOrder: async (id, order) => {
        const user = await FurnitureApi.getUserById(id)
        if (!user) {
            return false
        }

        user.orders.push(order)
        FurnitureApi.updateUserById(id, user)
        return true
    }
}

module.exports = {
    FurnitureApi,
}