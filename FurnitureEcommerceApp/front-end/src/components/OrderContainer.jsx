
const OrderContainer = ({ user }) => {
  return (
    <div id="order-container">
      {user ?
        <table className="table table-bordered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Items</th>
              <th scope="col">Pretotal</th>
              <th scope="col">Discount</th>
              <th scope="col">Subtotal</th>
              <th scope="col">Tax</th>
              <th scope="col">Total</th>
            </tr>
          </thead>
          <tbody>
            {
              user.orders.map((order) => {
                return (
                  <tr key={order.orderId}>
                    <th scope="row">{ order.orderId }</th>
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
                )
              })
            }
          </tbody>
        </table>
        :
        <h3>No Orders</h3>
      }
    </div>
  )
}

export default OrderContainer
