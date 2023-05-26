import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

import './css/Orders.css'

const Orders = ({ user, setRedirect }) => {
  const navigate = useNavigate()

  useEffect(() => {
    if (!user) {
      setRedirect({
        shouldRedirect: true,
        toURL: "/orders"
      })
      alert("You must login first.")
      navigate('/login')
    }
  }, [user, navigate, setRedirect])
    
  return (
    <div id="orders">
        <h3>Your Orders</h3>
      {
        user.orders.map((order) => {
            return (
              <>
                <div>Order Information</div>
                <div>View Order</div>
              </>
            )
        })
      }
    </div>
  )
}

export default Orders
