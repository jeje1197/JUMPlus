const Shop = ({ user }) => {
    return (
        <div id="shop">
            <h3>Welcome, {user?.username}!</h3>

            <h5>Shop for all the latest items</h5>

            <div>
                <h5>Categories</h5>
            </div>
            
        </div>
    )
}

export default Shop
