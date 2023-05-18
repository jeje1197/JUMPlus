import React from 'react'

const Carousel = ({ images }) => {
  return (
    <div id="carouselExampleIndicators" className="carousel slide" data-ride="carousel">
        <div className="carousel-inner">
          {
            images.map((image, index) => {
              return (
                <div key={index} className={`carousel-item ${index === 0 ? "active" : ""}`}>
                  <img className="d-block w-100 carousel-image" src={image} alt={`Slide ${index}`}/>
                </div>
              )
            })
          }
        </div>
        <button className="carousel-control-prev" data-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        </button>
        <button className="carousel-control-next" data-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
        </button>
      </div>
  )
}

export default Carousel
