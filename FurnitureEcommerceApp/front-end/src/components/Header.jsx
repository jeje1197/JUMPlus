import { Link, useNavigate } from 'react-router-dom'

import './css/Header.css'

const Header = ({ user, setUser, setSearchValue }) => {
  const navigate = useNavigate()

  const goToLoginPage = () => {
    navigate("/login")
  }

  const goToRegisterPage = () => {
    navigate("/register")
  }

  const logout = () => {
    setUser(undefined)
    navigate("/")
  }

  const search = async (event) => {
    event.preventDefault()

    const searchValue = document.getElementById('search-box').value
    const endpoint = '/items?search=' + searchValue
    setSearchValue(searchValue)
    navigate(endpoint)
  }
  
  return (
    <div id="header">
      <nav className="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">TOP Furniture</Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link" aria-current="page" to="/items">Shop</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/cart">My Cart</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/orders">My Orders</Link>
              </li>
            </ul>

            <div id="search-and-login">
              <form className="d-flex" onSubmit={search}>
                <input id="search-box"className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                <button className="btn btn-outline-success" type="submit">Search</button>
              </form>
              {!user ? 
                (
                  <>
                    <button id="login-button" className="btn btn-primary"
                    onClick={goToLoginPage}>Sign In</button>
                    <button id="login-button" className="btn btn-primary"
                    onClick={goToRegisterPage}>Sign Up</button>
                  </>
                )
                :
                (
                  <button id="login-button" className="btn btn-primary"
                  onClick={logout}>Logout</button>
                )
              }
            </div>
          </div>
        </div>
      </nav>
    </div>
  )
}

export default Header
