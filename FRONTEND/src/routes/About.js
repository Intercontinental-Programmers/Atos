import React, { Component } from 'react';
import DataList from "../components/DataList";
import axios from "axios";

class Home extends Component {

  state = {
    data: []
  };

  componentDidMount() {
    axios
      .get("http://156.17.72.33:5000/api/developers")
      .then(response => {

        // create an array of contacts only with relevant data
        const newData = response.data.map(c => {
          return {
            name: c.name,
            surname: c.surname,
            email: c.email,
            position: c.position,
            website: c.website,
            languages: c.languages,
            level: c.level,
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
      <div>
        <DataList data={this.state.data} />

      </div>
    );
  }
}

export default Home;