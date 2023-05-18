import './App.css';
import Footer from './components/Footer';
import Header from './components/Header';
import Home from './components/Home';

import { BrowserRouter, Routes, Route } from 'react-router-dom'


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />

        <Routes>
          <Route exact path="/" element={ <Home /> }/>


        </ Routes>

        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
