import React, { Component } from 'react';
import {
  BrowserRouter as Router, Route
} from 'react-router-dom';
import Navbar from "./components/Navbar"
import Footer from "./components/Footer"
import Home from './routes/Home';
import About from './routes/About';
import Login from './routes/Login';

class App extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <Router>
          <div id="content">
            <Route exact path="/" component={Home} />
            <Route path="/about" component={About} />
            <Route path="/login" component={Login} />
          </div>
        </Router>
        <Footer />
      </div>
    );
  }
}

export default App;