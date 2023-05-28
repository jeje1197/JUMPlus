import Order from "./Order"

const OrderContainer = ({ user }) => {
  return (
    <div id="order-container">
      { user ?
          user.orders.map((order) => {
            return (
              <Order key={order.orderId} order={order}/>
            )       
          })
          :
          <h3>No Orders</h3>
      }
    </div>
  )
}

export default OrderContainer
