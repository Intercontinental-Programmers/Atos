import React, { Component } from 'react';
import {
  BrowserRouter as Router, Route
} from 'react-router-dom';
import Navbar from "./components/Navbar"
import Footer from "./components/Footer"
import Home from './routes/Home';
import Tinder from './routes/Tinder';
import Login from './routes/Login';
import './style.css';
import Register from './routes/Register';


class App extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <Router>
          <div id="content">
            <Route exact path="/" component={Home} />
            <Route path="/tinder" component={Tinder} />
            <Route path="/login" component={Login} />
            <Route path="/register" component={Register} />
          </div>
        </Router>
        <Footer />
      </div>
    );
  }
}

export default App;