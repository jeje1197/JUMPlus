import { useNavigate } from 'react-router-dom'
import CartContainer from './CartContainer'

import './css/Cart.css'
import { useEffect } from 'react'

const Cart = ({ user, setUser, setRedirect }) => {
  const navigate = useNavigate()

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

  if (user) {
    calculatePrices()
  }

  const clearCart = async () => {
    user.cart = []
    setUser({...user})
  }

  const completeOrder = async () => {
    user.orders.push({
      order_id: user.orders.length + 1,
      items: user.cart,
      discounts,
      subtotal,
      tax,
      total
    })
    user.cart = []
    setUser()
  }

  useEffect(() => {
    if (!user) {
      setRedirect({
        shouldRedirect: true,
        toURL: "/cart"
      })
      alert("You must login first.")
      navigate('/login')
    }
  }, [user, navigate, setRedirect])
    
  return (
      <div id="cart">
        <h3>My Cart</h3>
        <hr />

        {user && <CartContainer user={user} setUser={setUser} setRedirect={setRedirect}/>}
        <hr />
        
        <div id="purchase-items">
          <h3>Checkout Items</h3>
          <>Number of items: {user?.cart?.length}</>
          <p>Discounts: ${discounts}</p>
          <p>Subtotal: ${subtotal}</p>
          <p>Tax: ${tax}</p>
          <p>Total: ${total}</p>

          <div id="cart-buttons">
            <button className="btn btn-primary" onClick={clearCart}>Clear Cart</button>
            <button className="btn btn-primary" onClick={completeOrder}>Complete Order</button>
          </div>
        </div>
      </div>
  )
}

export default Cart
