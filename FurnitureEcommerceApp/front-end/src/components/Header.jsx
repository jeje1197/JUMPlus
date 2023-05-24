import { Link, useNavigate } from 'react-router-dom'

import './css/Header.css'

const Header = ({ user, setUser }) => {
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
                <Link className="nav-link active" aria-current="page" to="">Home</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link">Link</Link>
              </li>
              <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle" href="/" id="navbarDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                  Dropdown
                </a>
                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li><button className="dropdown-item">Action</button></li>
                  <li><button className="dropdown-item">Another action</button></li>
                  <li><hr className="dropdown-divider" /></li>
                  <li><button className="dropdown-item">Something else here</button></li>
                </ul>
              </li>
            </ul>
            <div id="search-and-login">
              <form className="d-flex">
                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
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
