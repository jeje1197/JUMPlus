import React from 'react'
import f1 from '../images/furniture_1.jpg'
import f2 from '../images/furniture_2.jpg'
import f3 from '../images/furniture_3.jpg'

const Home = () => {
  const carouselImages = [f1, f2, f3]


  return (
    <div id="home">

      <div id="carouselExampleIndicators" className="carousel slide" data-ride="carousel">
        <ol className="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" className="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div className="carousel-inner">
          {
            carouselImages.map((image, index) => {
              return (
                <div key={index} className={`carousel-item ${index === 0 ? "active" : ""}`}>
                  <img className="d-block w-100" src={image} alt={`Slide ${index}`}/>
                </div>
              )
            })
          }
        </div>
        <button className="carousel-control-prev" href="#carouselExampleIndicators" data-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        </button>
        <button className="carousel-control-next" href="#carouselExampleIndicators" data-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
        </button>
      </div>

    </div>
  )
}

export default Home
