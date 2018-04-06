import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Grid from 'material-ui/Grid';
import LoginComponent from '../components/LoginComponent';

const styles = theme => ({
    root: {
        flexGrow: 1,
        paddingTop: '13%'
    },
    paper: {
        padding: theme.spacing.unit * 2,
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
});

function Login(props) {
    const { classes } = props;

    return (
        <div className={classes.root}>
            <Grid container spacing={0}>
                <Grid item xs />
                <Grid item xs={6}>
                    <Paper className={classes.paper}>
                        <LoginComponent />
                    </Paper>
                </Grid>
                <Grid item xs />
            </Grid>
        </div>
    );
}

Login.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Login);