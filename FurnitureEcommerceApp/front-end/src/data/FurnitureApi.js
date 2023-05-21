const FurnitureApi = {
    URL: 'http://localhost:3500',

    getUsers: async () => {
        const endpoint = FurnitureApi.URL + '/users'
        const response = await fetch(endpoint)
        const users = await response.json()
        return users
    },

    addUser: async (userData) => {
        // Check if username has already been claimed
        let users = []

        await FurnitureApi.getUsers()
        .then((data) => { users = data })
        .catch((error) => console.log(error))

        for (let user of users) {
            if (userData.username === user.username){
                return false
            }
        }

        const endpoint = FurnitureApi.URL + '/users'
        const postOption = {
            method: 'POST',
            'Headers': {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        }

        await fetch(endpoint, postOption)
        return true
    },

    
}

module.exports = {
    FurnitureApi,
}