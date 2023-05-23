import './css/ItemCard.css'

const ItemCard = ({ item }) => {
    const { image, title, description } = item

    return (
        <div className="card item-card" style={{width: "18rem"}}>
            <div className="card-body">
                <img className="card-img-top" src={image} alt="Card cap"/>
                <h5 className="card-title">{title}</h5>
                <p className="card-text">{description}</p>
                <button href="#" className="btn btn-primary">Add To Cart</button>
            </div>
        </div>
  )
}

export default ItemCard
