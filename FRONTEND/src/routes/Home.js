import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Grid from 'material-ui/Grid';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';
import cookie from 'react-cookies'

const styles = theme => ({
  root: {
    position: 'relative',
    marginTop: '13%',
    flexGrow: 1,
    padding: theme.spacing.unit * 2,
    textAlign: 'center',
    color: theme.palette.text.secondary,
  },

});

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

function Home(props) {
  let token = cookie.load('token')
  console.log(token)
  const { classes } = props;

  return (
    <div className={classes.root}>
      <MuiThemeProvider theme={theme}>
        <Grid container spacing={0}>
          <Grid item xs />
          <Grid item xs={6}>
            <Typography variant="display4" gutterBottom>
              Emploizer
          </Typography>
            <Typography variant="display2" gutterBottom>
              lets automate the jobs.
          </Typography><br/><br/>
            <Button variant="raised" size="large" href="/login" color="primary" className={classes.button}>
              Let's start
        </Button>
          </Grid>
          <Grid item xs />
        </Grid>
      </MuiThemeProvider>
    </div>
  );
}

Home.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Home);