import { useEffect, useState } from 'react'
import f1 from '../images/furniture_1.jpg'
import f2 from '../images/furniture_2.jpg'
import f3 from '../images/furniture_3.jpg'
import Carousel from './Carousel'
import ItemContainer from './ItemContainer'

import './css/Home.css'
import { FurnitureApi } from '../data/FurnitureApi'

const Home = ({ user, setUser }) => {
  const carouselImages = [f1, f2, f3]
  const [featuredItems, setFeaturedItems] = useState([])

  useEffect(() => {
    const getFeaturedItems = async () => {
      setFeaturedItems(await FurnitureApi.getItems())
    }

    getFeaturedItems()
  }, [])


  return (
    <div id="home">
      <Carousel images={carouselImages}/>

      <h3>Featured Items</h3>
      <hr />
      <ItemContainer user={user} setUser={setUser} items={featuredItems} />
    </div>
  )
}

export default Home
