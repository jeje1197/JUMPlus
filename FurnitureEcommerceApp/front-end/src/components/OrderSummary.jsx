import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const OrderSummary = ({ user, setRedirect }) => {
  const navigate = useNavigate()
  let order = undefined

  if (!user) {
    if (!user) {
      setRedirect({
        shouldRedirect: true,
        toURL: "/cart"
      })
      alert("You must login first.")
      navigate('/login')
    }
  }

  const getOrder = () => {
    const queryParameters = new URLSearchParams(window.location.search)
    const orderId = queryParameters.get("id")

    for (let userOrder of user.orders) {
      if (userOrder.orderId === orderId) {
        order = userOrder
        break;
      }
    }
  }
  getOrder()


  return (
    <div id="order-summary">
      {

      }
    </div>
  )
}

export default OrderSummary
