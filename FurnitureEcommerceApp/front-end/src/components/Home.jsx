import f1 from '../images/furniture_1.jpg'
import f2 from '../images/furniture_2.jpg'
import f3 from '../images/furniture_3.jpg'
import Carousel from './Carousel'
import FeaturedItems from './FeaturedItems'

import './Home.css'

const Home = () => {
  const carouselImages = [f1, f2, f3]
  const featuredItems = [
    {
      image: "...",
      title: "Title",
      description: "Description",
      
    },
    {
      image: "...",
      title: "Title",
      description: "Description",
      
    }
  ]

  return (
    <div id="home">
      <Carousel images={carouselImages}/>

      <FeaturedItems items={featuredItems} />
      

    </div>
  )
}

export default Home
