import Footer from './components/Footer';
import Header from './components/Header';
import Home from './components/Home';

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from './components/Login';
import Register from './components/Register';

import './App.css';
import { useEffect, useState } from 'react';
import SearchResults from './components/SearchResults';
import Cart from './components/Cart';
import OrderSummary from './components/OrderSummary';
import Orders from './components/Orders';

function App() {
  const [user, setUser] = useState(undefined)
  const [searchValue, setSearchValue] = useState("")
  const [redirect, setRedirect] = useState({
    shouldRedirect: false,
    toURL: ""
  })

  useEffect(() => {
  }, [user])

  return (
    <div className="App">
      <BrowserRouter>
        <Header user={user} setUser={setUser} setSearchValue={setSearchValue} setRedirect={setRedirect} />

          <Routes>
            <Route exact path="/" element={<Home user={user} setUser={setUser} setRedirect={setRedirect} />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login setUser={setUser} redirect={redirect} setRedirect={setRedirect} />} />

            <Route path="/items" element={<SearchResults user={user} setUser={setUser} setRedirect={setRedirect} searchValue={searchValue} />} />
            <Route path="/cart" element={<Cart user={user} setUser={setUser} setRedirect={setRedirect} />} />
            <Route path="/order-summary" element={<OrderSummary user={user} setRedirect={setRedirect} />} />
            <Route path="/orders" element={<Orders user={user} setRedirect={setRedirect} />} />
          </Routes>

        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
