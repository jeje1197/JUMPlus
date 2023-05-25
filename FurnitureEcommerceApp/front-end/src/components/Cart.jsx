import CartContainer from './CartContainer'

import './css/Cart.css'

const Cart = ({ user, setUser, setRedirect }) => {
  let subtotal = 0
  let discounts = 0
  const taxRate = 0.10
  let tax = 0
  let total = 0


  const calculatePrices = () => {
    subtotal = 0
    for (let item of user.cart) {
      subtotal += item.price
    }
    subtotal -= discounts
    
    const tax = subtotal * taxRate
    total = subtotal + tax
  }
  calculatePrices()

  const clearCart = async () => {
    user.cart = []
    setUser(user)
  }

  const completeOrder = async () => {

  }
    
  return (
    <div id="cart">
      <h3>My Cart</h3>
      <hr />

      <CartContainer user={user} setUser={setUser} setRedirect={setRedirect}/>
      <hr />
      
      <div id="purchase-items">
        <h3>Checkout Items</h3>
        <>Number of items: {user.cart.length}</>
        <p>Discounts: ${discounts}</p>
        <p>Subtotal: ${subtotal}</p>
        <p>Tax: ${tax}</p>
        <p>Total: ${total}</p>

        <div>
          <button className="btn btn-primary" onClick={clearCart}>Clear Cart</button>
          <button className="btn btn-primary" onClick={completeOrder}>Complete Order</button>
        </div>
      </div>
    </div>
  )
}

export default Cart
