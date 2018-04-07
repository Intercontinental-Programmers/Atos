import React, { Component } from 'react';
import DataList from "../components/DataList";
import Criteria from "../components/Criteria";
import Student from "../components/Student";
import Listcity from "../components/Listcity";
import axios from "axios";
import Paper from 'material-ui/Paper';
import Grid from 'material-ui/Grid';


const styles = theme => ({
});

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
                            <Grid container spacing={0}>
                        <Grid item xs />
                        <Grid item xs={6}>
                            <Paper >
        <Student/>                     
        <Criteria/>
        </Paper>
                        </Grid>
                        <Grid item xs />
                    </Grid>

      </div>
    );
  }
}

export default Home;