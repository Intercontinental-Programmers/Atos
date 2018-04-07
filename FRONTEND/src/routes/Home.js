import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Grid from 'material-ui/Grid';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';

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

function Home(props) {
  const { classes } = props;

  return (
    <div className={classes.root}>
      <Grid container spacing={0}>
        <Grid item xs />
        <Grid item xs={6}>
          <Typography variant="display4" gutterBottom>
            React App
          </Typography>
          <Typography variant="display2" gutterBottom>
            we create words.
          </Typography>
          <Button variant="raised" size="large" color="primary" href="/criteria" className={classes.button}>
          Let's Start
        </Button>
        </Grid>
        <Grid item xs />
      </Grid>
    </div>
  );
}

Home.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Home);