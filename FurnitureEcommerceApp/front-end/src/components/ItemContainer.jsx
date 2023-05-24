import React from 'react'
import ItemCard from './ItemCard'

import './css/ItemContainer.css'

const ItemContainer = ({ user, items }) => {
  return (
    <div id="featured-items">
      {items.map(
        (item, index) => <ItemCard key={index} user={user} item={item} />
      )}
    </div>
  )
}

export default ItemContainer
