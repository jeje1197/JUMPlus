import React from 'react'
import ItemCard from './ItemCard'

import './FeaturedItems.css'

const FeaturedItems = ({ items }) => {
  return (
    <div id="featured-items">
      {items.map(
        (item, index) => <ItemCard key={index} item={item} />
      )}
    </div>
  )
}

export default FeaturedItems
