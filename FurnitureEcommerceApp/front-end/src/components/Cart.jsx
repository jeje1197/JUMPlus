import { useNavigate } from 'react-router-dom'
import CartContainer from './CartContainer'

import './css/Cart.css'
import { useEffect } from 'react'
import { FurnitureApi } from '../data/FurnitureApi'

const Cart = ({ user, setUser, setRedirect }) => {
  const navigate = useNavigate()

  let priceBeforeDiscount = 0
  let subtotal = 0
  const discountRate = -0.10
  let discount = 0
  const taxRate = 0.10
  let tax = 0
  let total = 0


  const calculatePrices = () => {
    // Calculate total price for all items
    for (let item of user.cart) {
      priceBeforeDiscount += item.price
    }

    // Apply 10% discount if price exceeds $2000
    if (priceBeforeDiscount > 2000) {
      discount = priceBeforeDiscount * discountRate
    }
    subtotal = priceBeforeDiscount + discount
    
    // Calculate and apply tax
    tax = subtotal * taxRate
    total = subtotal + tax
  }

  if (user) {
    calculatePrices()
  }

  const clearCart = async () => {
    user.cart = []
    setUser({...user})
    await FurnitureApi.updateUserById(user.id, user)
  }

  const completeOrder = async () => {
    if (user.cart.length === 0) {
      alert("Cannot complete order. No items in cart.")
      return
    }
    const order = {
      orderId: user.orders.length + 1,
      date:new Date(),
      items: user.cart,
      priceBeforeDiscount,
      discount,
      subtotal,
      tax,
      total
    }

    user.orders.push(order)
    user.cart = []
    setUser({...user})

    const endpoint = '/order-summary?id=' + order.orderId
    navigate(endpoint)
    await FurnitureApi.updateUserById(user.id, user)
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
          <p>Number of items: {user?.cart?.length}</p>
          <p>Initial Price: ${priceBeforeDiscount.toFixed(2)}</p>
          <p>Discounts: ${discount.toFixed(2)}</p>
          <p>Subtotal: ${subtotal.toFixed(2)}</p>
          <p>Tax: ${tax.toFixed(2)}</p>
          <p>Total: ${total.toFixed(2)}</p>

          <div id="cart-buttons">
            <button className="btn btn-primary" onClick={clearCart}>Clear Cart</button>
            <button className="btn btn-primary" onClick={completeOrder}>Complete Order</button>
          </div>
        </div>
      </div>
  )
}

export default Cart
