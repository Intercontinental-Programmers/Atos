import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Home';
import Menu, { MenuItem } from 'material-ui/Menu';
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';
import cookie from 'react-cookies'

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
        flexGrow: 1,
    },
    flex: {
        flex: 1,
    },
    menuButton: {
        marginLeft: -12,
        marginRight: 20,
    },
};

class Navbar extends React.Component {
    state = {
        anchorEl: null,
    };

    event = () => {
        cookie.remove('token', { path: '/' })
    }

    handleClick = event => {
        this.setState({ anchorEl: event.currentTarget });
    };

    handleClose = () => {
        this.setState({ anchorEl: null });
    };

    goMainPage = () => {
        if (window) window.location.href = "/"
        return true;
    }

    goAbout = () => {
        if (window) window.location.href = "/about"
        return true;
    }

    render() {
        const { classes } = this.props;
        const { anchorEl } = this.state;

        return (
            <div className={classes.root}>
                <MuiThemeProvider theme={theme}>
                    <AppBar position="static" color="primary">
                        <Toolbar>
                            <IconButton className={classes.menuButton} color="inherit" aria-label="Menu" 
                                //aria-owns={anchorEl ? 'simple-menu' : null} aria-haspopup="true" onClick={this.handleClick}
                                href="/" >
                                <MenuIcon />
                            </IconButton>
                            <Typography variant="title" color="inherit" className={classes.flex}>
                                {/* React Simple App */}
                            </Typography>
                            <Button color="inherit" href="/login">Login</Button>
                            <Button color="inherit" onClick={this.event}>Logout</Button>
                            <Button color="inherit" href="/register">Register</Button>
                        </Toolbar>
                    </AppBar>
                </MuiThemeProvider>

                <Menu
                    id="simple-menu"
                    anchorEl={anchorEl}
                    open={Boolean(anchorEl)}
                    onClose={this.handleClose} >

                    <MenuItem onClick={this.goMainPage}>Main Page</MenuItem>
                    <MenuItem onClick={this.goAbout}>About</MenuItem>
                </Menu>
            </div>
        );
    }
}
Navbar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Navbar);