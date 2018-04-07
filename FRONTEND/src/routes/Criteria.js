import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Input, { InputLabel } from 'material-ui/Input';
import { MenuItem } from 'material-ui/Menu';
import { FormControl } from 'material-ui/Form';
import Select from 'material-ui/Select';
import Checkbox from 'material-ui/Checkbox';
import { ListItemText } from 'material-ui/List';
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import { FormControlLabel } from 'material-ui/Form';
import Grid from 'material-ui/Grid';
import Icon from 'material-ui/Icon';
import Button from 'material-ui/Button';
import cookie from 'react-cookies'

const styles = theme => ({
  root: {
    display: 'flex',
    flexWrap: 'wrap',
    marginTop: '20%',
  },

  formControl: {
    margin: theme.spacing.unit,
    minWidth: 120,
    marginTop: '3%',
    marginBottom: '3%',
  },
  selectEmpty: {
    marginTop: theme.spacing.unit * 2,
  },
  button: {
    marginTop: '2em',
    marginLeft: '10em',
    marginBottom:'3em',
},
    
});
const divStyle = {
  marginLeft: '2em',
};

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

const lang = [
  'Python',
  'Javascript',
  'Cpp',
  'Csharp',
  'Ruby',
  'Typescript',
  'C',
  'Java'
];

const lvl = [
  'Junior',
  'Regular',
  'Senior'
];

const theme = createMuiTheme({
  palette: {
    primary: {
      main: '#222',
    },
    secondary: {
      light: '#0066ff',
      main: '#0044ff',
      contrastText: '#ffcc00',
    },
  },
});

class Criteria extends React.Component {
  state = {
    levels: [],
    languages: [],
    cities: [],
    student: true,
  };

  post = () => {
    var data = {
      levels: this.state.levels,
      languages: this.state.languages,
      cities: [this.state.cities],
      student: this.state.student,
    }

    console.log(data)

    let token = cookie.load('token')
    console.log(token)

    var request = new XMLHttpRequest();
    request.open('POST', 'http://localhost:5000/api/developers', true);
    request.setRequestHeader('Content-Type', 'text/plain');
    request.setRequestHeader('Authorization', 'Bearer ' + token);
    request.send(JSON.stringify(data));
    console.log(request)
    console.log(request.text)
  }

  handleChange = event => {
    this.setState({ student: event.target.checked });
  };

  handleLanguageChange = event => {
    this.setState({ languages: event.target.value });
  };

  handleLevelChange = event => {
    this.setState({ levels: event.target.value });
  };

  handleCityChange = event => {
    this.setState({ cities: event.target.value });
  };


  render() {
    const { classes } = this.props;

    return (
      <MuiThemeProvider theme={theme}>
        <Grid container spacing={0}>
          <Grid item xs />
          <Grid item xs={6}>
            <Paper className={classes.paper}>
              <form className={classes.root} autoComplete="off">


                <FormControl fullWidth className={classes.formControl}>
                  <InputLabel htmlFor="select-multiple-checkbox">Language</InputLabel>
                  <Select
                    multiple
                    value={this.state.languages}
                    onChange={this.handleLanguageChange}
                    input={<Input id="select-multiple-checkbox" />}
                    renderValue={selected => selected.join(', ')}
                    MenuProps={MenuProps}
                  >
                    {lang.map(languages => (
                      <MenuItem key={languages} value={languages}>
                        <Checkbox checked={this.state.languages.indexOf(languages) > -1} />
                        <ListItemText primary={languages} />
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <FormControl fullWidth className={classes.formControl}>
                  <InputLabel htmlFor="select-multiple-checkbox">Level</InputLabel>
                  <Select
                    multiple
                    value={this.state.levels}
                    onChange={this.handleLevelChange}
                    input={<Input id="select-multiple-checkbox" />}
                    renderValue={selected => selected.join(', ')}
                    MenuProps={MenuProps}
                  >
                    {lvl.map(levels => (
                      <MenuItem key={levels} value={levels}>
                        <Checkbox checked={this.state.languages.indexOf(levels) > -1} />
                        <ListItemText primary={levels} />
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <FormControl fullWidth className={classes.margin}>
                  <InputLabel htmlFor="cities">City</InputLabel>
                  <Input
                    id="cities"
                    value={this.state.cities}
                    onChange={this.handleCityChange}
                  />
                </FormControl>

                <FormControlLabel
                  control={
                    <Checkbox
                    style={divStyle}
                      color="primary"
                      checked={this.state.student}
                      onChange={this.handleChange}
                      value="student"
                    />} label="Student" />

                <Button className={classes.button} onClick={this.post}
                  variant="raised" color="primary">
                  Send&#160;&#160;
                        <Icon className={classes.rightIcon}>send</Icon>
                </Button>

              </form>
            </Paper>
          </Grid>
          <Grid item xs />
        </Grid>
      </MuiThemeProvider>
    );
  }
}

Criteria.propTypes = {
  classes: PropTypes.object.isRequired,
};


export default withStyles(styles)(Criteria);