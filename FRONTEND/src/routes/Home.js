import React, { Component } from 'react';
import DataList from "../components/DataList";
import logo from './logo.svg';
import axios from "axios";
import './home.css';

class Home extends Component {

  state = {
    data: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/items")
      .then(response => {

        // create an array of contacts only with relevant data
        const newData = response.data.map(c => {
          return {
            id: c.id,
            name: c.name,
            description: c.description
          };
        });

        // create a new "State" object without mutating 
        // the original State object. 
        const newState = Object.assign({}, this.state, {
          data: newData
        });

        // store the new state object in the component's state
        this.setState(newState);
      })
      .catch(error => console.log(error));
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Data Set</h1>
        </header>

        <DataList data={this.state.data} />

      </div>
    );
  }
}

export default Home;