import React, { Component } from 'react';
import DataList from "../components/DataList";
import axios from "axios";
import cookie from 'react-cookies'


class Tinder extends Component {

  state = {
    data: []
  };

  componentDidMount() {
    let token = cookie.load('token')
    axios
      .get("http://localhost:5000/api/developers/test", 
      { headers: { 'Authorization': 'Bearer ' + token } }
    )
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

export default Tinder;