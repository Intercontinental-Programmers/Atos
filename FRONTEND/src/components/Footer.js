import React from 'react';
import PropTypes from 'prop-types';
import Typography from 'material-ui/Typography';
import { withStyles } from 'material-ui/styles';
import AppBar from 'material-ui/AppBar';
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';

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

const styles = {
    root: {
        position: 'fixed',
        bottom: 0,
        width: '100%',
    },
};

function Footer(props) {
    const { classes } = props;
    return (
        <div className={classes.root}>
            <MuiThemeProvider theme={theme}>
                <AppBar position="static" color="primary">
                        <Typography align="center" color="inherit" className={classes.flex}>
                            <br/>Copyright: Intercontinental Programmers<br/><br/>
                        </Typography>
                </AppBar>
            </MuiThemeProvider>
        </div>
    );
}

Footer.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Footer);
