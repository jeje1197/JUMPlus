import { FurnitureApi } from '../data/FurnitureApi'
import { useNavigate } from 'react-router-dom'

const Login = ({ setUser }) => {
  const navigate = useNavigate()

  const handleLogin = async (event) => {
    event.preventDefault()

    const username = document.getElementById("login-username").value
    const password = document.getElementById("login-password").value

    const user = await FurnitureApi.login(username, password)
    if (!user) {
      alert('Incorrect username or password')
      return
    }

    setUser(user)
    navigate('/shop')
  }
  return (
    <div id="login">
      <form action="/">
        {/* Username Input Field */}
        <div className="input-group mb-3">
          <div className="input-group-prepend">
            <span className="input-group-text">
              <i className="fas fa-user-alt" style={{ fontSize: "18px" }}></i>
            </span>
          </div>
          <input
            id="login-username"
            type="text"
            className="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            placeholder="Username"
            required
          />
        </div>

        {/* Password Input Field */}
        <div className="input-group mb-3">
          <div className="input-group-prepend">
            <span className="input-group-text">
              <i className="fas fa-lock" style={{ fontSize: "18px" }}></i>
            </span>
          </div>
          <input
            id="login-password"
            type="text"
            className="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            placeholder="Password"
            required
          />
        </div>

        <button
          type="submit"
          className="btn btn-primary"
          onClick={handleLogin}
        >
          Sign In
        </button>
      </form>
    </div>
  )
}

export default Login
