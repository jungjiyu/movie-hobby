import React, { Component } from 'react';
import { BrowserRouter as Router , Route } from "react-router-dom";
import  Navigation from "./components/shared/Navigation";
import  Footer from "./components/shared/Footer";

import Home from './components/Home';
import Inception from './components/Inception';
import EternalSunshine from './components/eternalSunshine';
import ShutterIsland from './components/ShutterIsland';
import './App.css';

class App extends Component {
  render() {
    return (
      <Router>  
        
        <div>
        <Navigation/>
        <Route exact path="/" component={Home} />
        <Route path="/inception" component={Inception} />
        <Route path="/eternalSunshine" component={EternalSunshine} />        
        <Route path="/shutterIsland" component={ShutterIsland} />
        <Footer/>
        </div>
      </Router>
    );
  }
}

export default App;
