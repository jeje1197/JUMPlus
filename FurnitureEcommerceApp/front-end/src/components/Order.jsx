
const Order = ({ order }) => {
  return (
    <div className="">
        <h3>Completed Order</h3>
        <hr />

        <h5>Items</h5>
        <hr />
        <ul>
        {
            order.items.map((item) => {
              return <p>{item.name}</p>
            })
        }
        </ul>
        
        <p>Initial Price: ${order.priceBeforeDiscount.toFixed(2)}</p>
        <p>Discounts: ${order.discount.toFixed(2)}</p>
        <p>Subtotal: ${order.subtotal.toFixed(2)}</p>
        <p>Tax: ${order.tax.toFixed(2)}</p>
        <p>Total: ${order.total.toFixed(2)}</p>
    </div>
  )
}

export default Order
