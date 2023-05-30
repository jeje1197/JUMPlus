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
    setUser({ ...user })
    await FurnitureApi.updateUserById(user.id, user)
  }

  const completeOrder = async () => {
    if (user.cart.length === 0) {
      alert("Cannot complete order. No items in cart.")
      return
    }
    const order = {
      orderId: user.orders.length + 1,
      date: new Date(),
      items: user.cart,
      priceBeforeDiscount,
      discount,
      subtotal,
      tax,
      total
    }

    user.orders.push(order)
    user.cart = []
    setUser({ ...user })

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
      <h3>Cart Summary</h3>

      {user && <CartContainer user={user} setUser={setUser} setRedirect={setRedirect} />}

      <div id="purchase-items">

      
        <table className="table table-bordered">
          <thead>
            <tr>
              <th scope="col">Order #</th>
              <th scope="col">Items</th>
              <th scope="col">Pretotal</th>
              <th scope="col">Discount</th>
              <th scope="col">Subtotal</th>
              <th scope="col">Tax</th>
              <th scope="col">Total</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">{user.orders.length}</th>
              <td>
                { user.cart.length !== 0 ?
                  (<ul>
                    {
                      user.cart.map((item) => <li key={item.item_id}>{item.name}</li>)
                    }
                  </ul>)
                  :
                  (<p>None</p>)
                }
                
              </td>
              <td>${priceBeforeDiscount.toFixed(2)}</td>
              <td>${discount.toFixed(2)}</td>
              <td>${subtotal.toFixed(2)}</td>
              <td>${tax.toFixed(2)}</td>
              <td>${total.toFixed(2)}</td>
            </tr>
          </tbody>
        </table>

        <div id="cart-buttons">
          <button className="btn btn-primary" onClick={clearCart}>Clear Cart</button>
          <button className="btn btn-primary" onClick={completeOrder}>Complete Order</button>
        </div>
      </div>
    </div>
  )
}

export default Cart
