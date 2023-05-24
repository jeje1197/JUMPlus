import React from 'react'
import ItemCard from './ItemCard'

import './css/FeaturedItems.css'

const ItemContainer = ({ items }) => {
  return (
    <div id="featured-items">
      {items.map(
        (item, index) => <ItemCard key={index} item={item} />
      )}
    </div>
  )
}

export default ItemContainer
