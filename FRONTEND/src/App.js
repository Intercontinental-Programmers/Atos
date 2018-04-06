import React, { Component } from 'react';
import {
  BrowserRouter as Router, Route
} from 'react-router-dom';
import Navbar from "./components/Navbar"
import Home from './routes/Home';
import About from './routes/About';

class App extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <Router>
          <div id="content">
            <Route exact path="/" component={Home} />
            <Route path="/about" component={About} />
          </div>
        </Router>
      </div>
    );
  }
}

export default App;