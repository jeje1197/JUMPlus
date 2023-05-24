import React from 'react'
import './css/Register.css'

const Register = () => {
  return (
    <div>
      <form>
      <fieldset>
        <legend>Create Account</legend>
        <label htmlFor="fname" style={{ marginRight: "10px" }}>First Name</label>
        <input type="text" id="fname" name="fname"/><br /><br />
        <label htmlFor="lname" style={{ marginRight: "10px" }}>Last Name</label>
        <input type="text" id="lname" name="lname"/><br /><br />
        <label htmlFor="uname" style={{ marginRight: "10px" }}>Username</label>
        <input type="text" id="uname" name="uname" /><br /><br />
        <label htmlFor="pw" style={{ marginRight: "10px" }}>Password</label>
        <input type="password" id="pw" name="pw"/><br /><br />
        <input type="submit" value="Submit"></input>
      </fieldset>
    </form>
    </div>
  )
}

export default Register
