
const ItemCard = ({ item }) => {
    const { image, title, description } = item

    return (
        <div class="card" style={{width: "18rem"}}>
            <img class="card-img-top" src={image} alt="Card cap" />
            <div class="card-body">
                <h5 class="card-title">{title}</h5>
                <p class="card-text">{description}</p>
                <button href="#" class="btn btn-primary">Order Now</button>
            </div>
        </div>
  )
}

export default ItemCard
