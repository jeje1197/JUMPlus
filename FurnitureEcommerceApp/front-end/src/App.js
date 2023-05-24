import Footer from './components/Footer';
import Header from './components/Header';
import Home from './components/Home';

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from './components/Login';
import Register from './components/Register';

import './App.css';
import { useEffect, useState } from 'react';
import Shop from './components/Shop';
import SearchResults from './components/SearchResults';

function App() {
  const [user, setUser] = useState(undefined)
  const [searchValue, setSearchValue] = useState('')

  useEffect(() => {

  }, [user])

  return (
    <div className="App">
      <BrowserRouter>
        <Header user={user} setUser={setUser} setSearchValue={setSearchValue}/>

        <Routes>
          <Route exact path="/" element={ <Home user={user}/> }/>
          <Route path="/register" element={ <Register /> }/>
          <Route path="/login" element={ <Login setUser={setUser}/> }/>

          <Route path="/shop" element={ <Shop user={user}/> }/>
          <Route path="/items" element={ <SearchResults user={user} searchValue={searchValue}/> }/>

        </Routes>
 
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
