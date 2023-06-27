import { Link, useNavigate } from 'react-router-dom'

import './css/Header.css'

const Header = ({ user, setUser, setSearchValue, setRedirect }) => {
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

  // If user is signed in, go directly to page
  // Otherwise require user to sign in, then redirect
  // to page
  const handleRedirectToLogin = (toURL) => {
    if (!user) {
      setRedirect({
        shouldRedirect: true,
        toURL
      })
      alert("You must login first.")
      navigate("/login")
    } else {
      navigate(toURL)
    }
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
                <Link className="nav-link"
                  onClick={(event) => {
                    event.preventDefault();
                    handleRedirectToLogin("/cart")
                  }}
                >My Cart {user ? `(${user.cart.length})` : null}</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link"
                  onClick={(event) => {
                    event.preventDefault();
                    handleRedirectToLogin("/orders")
                  }}
                >My Orders</Link>
              </li>
            </ul>

            <div id="search-and-login">
              <form className="d-flex form-inline my-2 my-lg-0" onSubmit={search}>
                <input id="search-box"className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                <button className="btn btn-outline-success" type="submit">Search</button>
              </form>
            </div>
            {!user ? 
                (
                  <>
                    <button id="login-button" className="btn btn-primary"
                    onClick={goToLoginPage}>Sign In</button>
                    <button id="sign-in-button" className="btn btn-primary"
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
      </nav>
    </div>
  )
}

export default Header
