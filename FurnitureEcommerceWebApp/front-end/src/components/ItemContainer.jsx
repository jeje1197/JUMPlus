import ItemCard from './ItemCard'

import './css/ItemContainer.css'

const ItemContainer = ({ user, setUser, items, setRedirect }) => {
  const cartTracker = new Set();

  // Store all items in cart in a HashMap
  if (user) {
    for (let item of user.cart) {
      cartTracker.add(item.item_id)
    }
  }

  return (
    <div id="featured-items">
      {items.length !== 0 ?
        items.map((item) => <ItemCard key={item.item_id} user={user} setUser={setUser} setRedirect={setRedirect}
          item={item} inCart={cartTracker.has(item.item_id)}/>
        )
        :
        <h5>No Items Found</h5>
      }
    </div>
  )
}

export default ItemContainer
