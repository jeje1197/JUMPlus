import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

import './css/Orders.css'
import OrderContainer from './OrderContainer'

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
      {/* <hr /> */}
      {
        <OrderContainer user={user}/>
      }
    </div>
  )
}

export default Orders
