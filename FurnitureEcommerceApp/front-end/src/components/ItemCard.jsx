import { useNavigate } from 'react-router-dom'
import { FurnitureApi } from '../data/FurnitureApi'
import './css/ItemCard.css'

const ItemCard = ({ user, item }) => {
    const navigate = useNavigate()
    const { image, name, price } = item

    const addToCart = async () => {
        if (!user) {
            navigate('/login')
            return
        }
        user.cart.push(item)
        await FurnitureApi.updateUserById(user.id, user)
    }

    return (
        <div className="card item-card" style={{width: "18rem"}}>
            <div className="card-body">
                <h4 className="card-title">{name}</h4>
                <img className="card-img-top" src={image} alt="Card caption"/>
                <p className="card-text">${price}</p>
                <button className="btn btn-primary" onClick={addToCart}>Add To Cart</button>
            </div>
        </div>
  )
}

export default ItemCard
