import Footer from './components/Footer';
import Header from './components/Header';
import Home from './components/Home';

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from './components/Login';
import Register from './components/Register';

import './App.css';
import { useEffect, useState } from 'react';
import Shop from './components/Shop';

function App() {
  const [user, setUser] = useState(undefined)

  useEffect(() => {
    
  }, [user])

  return (
    <div className="App">
      <BrowserRouter>
        <Header user={user} setUser={setUser}/>

        <Routes>
          <Route exact path="/" element={ <Home /> }/>
          <Route path="/register" element={ <Register /> }/>
          <Route path="/login" element={ <Login setUser={setUser}/> }/>

          <Route path="/shop" element={ <Shop user={user}/> }/>
        </Routes>
 
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
