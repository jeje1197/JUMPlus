import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const OrderSummary = ({ user, setRedirect }) => {
  const navigate = useNavigate()
  let orderId = -1
  let order = undefined

  const getOrder = () => {
    const queryParameters = new URLSearchParams(window.location.search)
    orderId = Number(queryParameters.get("id"))

    if (!user) {
      return
    }

    for (let userOrder of user.orders) {
      if (userOrder.orderId === orderId) {
        order = userOrder
        return
      }
    }
  }
  getOrder()

  useEffect(() => {
    if (!user) {
      setRedirect({
        shouldRedirect: true,
        toURL: "/order-summary?id=" + orderId
      })
      alert("You must login first.")
      navigate('/login')
      return
    }
  }, [user, navigate, setRedirect, orderId])

  return (
    <div id="order-summary">
      { 
        order ?
          <>
            <h3>Completed Order</h3>
            <hr />

            <h5>Items</h5>
            <hr />
            <ul>
            {
              order.items.map((item, index) => {
                return <p key={index}>{item.name}</p>
              })
            }
            </ul>
            
            <p>Initial Price: ${order.priceBeforeDiscount.toFixed(2)}</p>
            <p>Discounts: ${order.discount.toFixed(2)}</p>
            <p>Subtotal: ${order.subtotal.toFixed(2)}</p>
            <p>Tax: ${order.tax.toFixed(2)}</p>
            <p>Total: ${order.total.toFixed(2)}</p>
          </>
          :
          <h3>No Order Found</h3>
      }
    </div>
  )
}

export default OrderSummary
