import ItemCard from "./ItemCard"

import './css/CartContainer.css'

const CartContainer = ({ user, setUser, setRedirect }) => {
  return (
    <div id="cart-items">
        { user ? 
            user.cart.map((item, index) => <ItemCard key={index} user={user} setUser={setUser} setRedirect={setRedirect} item={item} inCart={true}/>)
            :
            <p>No Items In Cart</p>
        }
    </div>
  )
}

export default CartContainer
