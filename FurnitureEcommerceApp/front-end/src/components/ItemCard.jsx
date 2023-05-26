import { useNavigate } from 'react-router-dom'
import { FurnitureApi } from '../data/FurnitureApi'
import './css/ItemCard.css'

const ItemCard = ({ user, setUser, item, inCart, setRedirect }) => {
    const navigate = useNavigate()
    const { image, name, price } = item

    const addToCart = async () => {
        if (!user) {
            setRedirect({
                shouldRedirect: true,
                toURL: window.location.href
            })
            alert("You must login first.")
            navigate('/login')
            return
        }
        user.cart.push(item)
        setUser({...user})
        await FurnitureApi.updateUserById(user.id, user)
    }

    const removeFromCart = async () => {
        if (!user) {
            alert("You must login first.")
            navigate('/login')
            return
        }
        
        let indexFound = -1
        for (let i = 0; i < user.cart.length; i++) {
            if (user.cart[i].name === item.name) {
                indexFound = i
                break
            }
        }
        user.cart.splice(indexFound, 1)
        setUser({...user})
        await FurnitureApi.updateUserById(user.id, user)
    }

    return (
        <div className="card item-card" style={{width: "18rem"}}>
            <div className="card-body">
                <h4 className="card-title">{name}</h4>
                <img className="card-img-top" src={image} alt="Card caption"/>
                <h5 className="card-text">${price}</h5>
                { inCart ?
                    <button className="btn btn-primary" onClick={removeFromCart}>Remove From Cart</button>
                    :
                    <button className="btn btn-primary" onClick={addToCart}>Add To Cart</button>
                }
            </div>
        </div>
  )
}

export default ItemCard
