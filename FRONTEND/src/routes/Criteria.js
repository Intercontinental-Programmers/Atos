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


const styles = theme => ({
  root: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  formControl: {
    margin: theme.spacing.unit,
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing.unit * 2,
  },
});

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

const languages = [
  'Python',
  'Javascript',
  'C++'
];

const levels = [
  'Junior',
  'Middle',
  'Senior'
];

const cities = [
  'Warszawa',
  'Lublin',
  'Wroclaw'
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
    level: [],
    language: [],
    city: [],
    isstudent: true,
  };


  handleChange = event => {
    this.setState({ isStudent: event.target.checked });
  };

  handleLanguageChange = event => {
    this.setState({ language: event.target.value });
  };

  handleLevelChange = event => {
    this.setState({ level: event.target.value });
  };

  handleCityChange = event => {
    this.setState({ city: event.target.value });
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
                    value={this.state.language}
                    onChange={this.handleLanguageChange}
                    input={<Input id="select-multiple-checkbox" />}
                    renderValue={selected => selected.join(', ')}
                    MenuProps={MenuProps}
                  >
                    {languages.map(language => (
                      <MenuItem key={language} value={language}>
                        <Checkbox checked={this.state.language.indexOf(language) > -1} />
                        <ListItemText primary={language} />
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <FormControl fullWidth className={classes.formControl}>
                  <InputLabel htmlFor="select-multiple-checkbox">Level</InputLabel>
                  <Select
                    multiple
                    value={this.state.level}
                    onChange={this.handleLevelChange}
                    input={<Input id="select-multiple-checkbox" />}
                    renderValue={selected => selected.join(', ')}
                    MenuProps={MenuProps}
                  >
                    {levels.map(level => (
                      <MenuItem key={level} value={level}>
                        <Checkbox checked={this.state.language.indexOf(level) > -1} />
                        <ListItemText primary={level} />
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <FormControl fullWidth className={classes.formControl}>
                  <InputLabel htmlFor="select-multiple-checkbox">City</InputLabel>
                  <Select
                    multiple
                    value={this.state.city}
                    onChange={this.handleCityChange}
                    input={<Input id="select-multiple-checkbox" />}
                    renderValue={selected => selected.join(', ')}
                    MenuProps={MenuProps}
                  >
                    {cities.map(city => (
                      <MenuItem key={city} value={city}>
                        <Checkbox checked={this.state.language.indexOf(city) > -1} />
                        <ListItemText primary={city} />
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <FormControlLabel
                  control={
                    <Checkbox
                      checked={this.state.isStudent}
                      onChange={this.handleChange}
                      value="isStudent"
                    />
                  }
                  label="Student"
                />


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