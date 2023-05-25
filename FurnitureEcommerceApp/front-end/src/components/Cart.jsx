import CartContainer from './CartContainer'

import './css/Cart.css'

const Cart = ({ user, setUser, setRedirect }) => {
    
  return (
    <div id="cart">
      <h3>My Cart</h3>
      <hr />

      <CartContainer user={user} setUser={setUser} setRedirect={setRedirect}/>
    </div>
  )
}

export default Cart
