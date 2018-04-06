import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Grid from 'material-ui/Grid';
import RegisterComponent from '../components/RegisterComponent';

const styles = theme => ({
    root: {
        flexGrow: 1,
        paddingTop: '7%',
    },
    paper: {
        padding: theme.spacing.unit * 2,
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
});

function Register(props) {
    const { classes } = props;

    return (
        <div className={classes.root}>
            <Grid container spacing={0}>
                <Grid item xs />
                <Grid item xs={6}>
                    <Paper className={classes.paper}>
                        <RegisterComponent />
                    </Paper>
                </Grid>
                <Grid item xs />
            </Grid>
        </div>
    );
}

Register.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Register);