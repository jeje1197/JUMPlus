import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

import './css/OrderSummary.css'

import confetti from 'https://cdn.skypack.dev/canvas-confetti';

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



  const myIntervalId = setInterval(() => {
    confetti()
    if (window.location.pathname !== "/order-summary") {
      clearInterval(myIntervalId)
    }
  }, 5000)

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
          <h3>Thank You </h3>
          <h3>Completed Order</h3>
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
              <tr key={order.orderId}>
                <th scope="row">{order.orderId}</th>
                <td>
                  <ul>
                    {
                      order.items.map((item) => <li key={item.item_id}>{item.name}</li>)
                    }
                  </ul>
                </td>
                <td>${order.priceBeforeDiscount.toFixed(2)}</td>
                <td>${order.discount.toFixed(2)}</td>
                <td>${order.subtotal.toFixed(2)}</td>
                <td>${order.tax.toFixed(2)}</td>
                <td>${order.total.toFixed(2)}</td>
              </tr>
            </tbody>
          </table>
          </>
          :
          <h3>No Order Found</h3>
      }
    </div>
  )
}

export default OrderSummary
