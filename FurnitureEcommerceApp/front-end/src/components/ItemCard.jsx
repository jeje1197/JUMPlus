import './css/ItemCard.css'

const ItemCard = ({ item }) => {
    const { image, name, price } = item

    return (
        <div className="card item-card" style={{width: "18rem"}}>
            <div className="card-body">
                <h4 className="card-title">{name}</h4>
                <img className="card-img-top" src={image} alt="Card caption"/>
                <p className="card-text">${price}</p>
                <button className="btn btn-primary">Add To Cart</button>
            </div>
        </div>
  )
}

export default ItemCard
