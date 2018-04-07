import React, { Component } from 'react';
import axios from "axios";
import Data from "../components/Data";

class Choose extends Component {

  state = {
    data: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:5000/api/developers/test")
      .then(response => {

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
        <div>
            {this.state.data.map(c => <Data name={c.name}
                surname={c.surname} email={c.email}
                position={c.position} website={c.website}
                languages={c.languages} level={c.level} />)}
        </div>
    );
  }
}

export default Choose;