import React from 'react'
import ItemCard from './ItemCard'

import './css/ItemContainer.css'

const ItemContainer = ({ user, setUser, items, setRedirect }) => {
  return (
    <div id="featured-items">
      {items.map(
        (item) => <ItemCard key={item.item_id} user={user} setUser={setUser} setRedirect={setRedirect} item={item}/>
      )}
    </div>
  )
}

export default ItemContainer
