import Footer from './components/Footer';
import Header from './components/Header';
import Home from './components/Home';

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from './components/Login';
import Register from './components/Register';

import './App.css';
import { useState } from 'react';
import Shop from './components/Shop';
import Account from './components/Account';

function App() {
  const [userData, setUserData] = useState(undefined)

  return (
    <div className="App">
      <BrowserRouter>
        <Header />

        <Routes>
          <Route exact path="/" element={ <Home /> }/>
          <Route path="/register" element={ <Register /> }/>
          <Route path="/login" element={ <Login setUserData={setUserData}/> }/>

          <Route path="/shop" element={ <Shop /> }/>
          <Route path="/account" element={ <Account /> }/>
        </Routes>
 
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
