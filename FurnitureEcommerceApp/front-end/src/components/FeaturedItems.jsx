import React from 'react'
import ItemCard from './ItemCard'

const FeaturedItems = ({ items }) => {
  return (
    <div id="featured-items">
      {
        items.map((item, index) => {
            return (
                <ItemCard key={index} item={item}/>
            )
        })
      }
    </div>
  )
}

export default FeaturedItems
