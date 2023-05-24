import React from 'react'
import './css/Register.css'
import { FurnitureApi } from '../data/FurnitureApi'
import { useNavigate } from 'react-router-dom'

const Register = () => {
  const navigate = useNavigate()

  const handleRegister = async (event) => {
    event.preventDefault()

    const firstName = document.getElementById('register-firstname').value
    const lastName = document.getElementById('register-lastname').value
    const username = document.getElementById('register-username').value
    const password = document.getElementById('register-password').value

    const userData = {
      firstName,
      lastName,
      username,
      password,
      cart: [],
      orders: []
    }

    const result = await FurnitureApi.addUser(userData)
    if (result) {
      alert("Successfully registered account. Login to use your account.")
      navigate('/login')
    } else {
      alert("Username already exists. Please choose a different username.")
    }
  }

  return (
    <div id="register">
      <form onSubmit={handleRegister}>
      <fieldset>
        <legend>Create Account</legend>
        <label htmlFor="register-firstname">First Name</label>
        <input type="text" id="register-firstname" name="fname"/><br /><br />
        <label htmlFor="register-lastname">Last Name</label>
        <input type="text" id="register-lastname" name="lname"/><br /><br />
        <label htmlFor="register-username">Username</label>
        <input type="text" id="register-username" name="uname" /><br /><br />
        <label htmlFor="register-password">Password</label>
        <input type="password" id="register-password" name="pw"/><br /><br />
        <input type="submit" value="Submit"/>
      </fieldset>
    </form>
    </div>
  )
}

export default Register
