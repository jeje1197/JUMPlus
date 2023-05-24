import { FurnitureApi } from '../data/FurnitureApi'
import { useNavigate } from 'react-router-dom'

import './css/Login.css'

const Login = ({ setUser }) => {
  const navigate = useNavigate()

  const handleLogin = async (event) => {
    event.preventDefault()

    const username = document.getElementById('login-username').value
    const password = document.getElementById('login-password').value

    const user = await FurnitureApi.login(username, password)
    if (user) {
      alert("Successfully logged in!")
      navigate('/shop')
    } else {
      alert("Incorrect username or password")
    }
  }

  return (
    <div id="login">
      <form onSubmit={handleLogin}>
      <fieldset>
        <legend>Login</legend>
        <label htmlFor="login-username">Username</label>
        <input type="text" id="login-username" name="uname" /><br /><br />
        <label htmlFor="login-password">Password</label>
        <input type="password" id="login-password" name="pw"/><br /><br />
        <input type="submit" value="Submit"/>
      </fieldset>
    </form>
    </div>
  )
}

export default Login
