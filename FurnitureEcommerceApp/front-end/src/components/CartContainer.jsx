import ItemCard from "./ItemCard"

import './css/CartContainer.css'

const CartContainer = ({ user, setUser, setRedirect }) => {
  return (
    <div id="cart-items">
        { user.cart !== 0 ? 
            user.cart.map((item) => {
              return <ItemCard key={item.item_id} user={user} setUser={setUser} setRedirect={setRedirect} 
                item={item} inCart={true}/>
            })
            :
            <p>No Items In Cart</p>
        }
    </div>
  )
}

export default CartContainer
