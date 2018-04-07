import React, { Component } from 'react';
import {
  BrowserRouter as Router, Route
} from 'react-router-dom';
import './style.css';
import Navbar from "./components/Navbar"
import Footer from "./components/Footer"
import Home from './routes/Home';
import Tinder from './routes/Tinder';
import Login from './routes/Login';
import Register from './routes/Register';
import Criteria from './routes/Criteria';
import Choose from './routes/Choose';


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
            <Route path="/criteria" component={Criteria} />
            <Route path="/choose" component={Choose} />
          </div>
        </Router>
        <Footer />
      </div>
    );
  }
}

export default App;