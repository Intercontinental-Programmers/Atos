import Paper from 'material-ui/Paper';
import Grid from 'material-ui/Grid';
import React from 'react';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import IconButton from 'material-ui/IconButton';
import Input, { InputLabel, InputAdornment } from 'material-ui/Input';
import { FormControl } from 'material-ui/Form';
import Visibility from 'material-ui-icons/Visibility';
import VisibilityOff from 'material-ui-icons/VisibilityOff';
import Typography from 'material-ui/Typography';
import Icon from 'material-ui/Icon';
import Button from 'material-ui/Button';
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';


const styles = theme => ({
    root: {
        flexGrow: 1,
        paddingTop: '13%',
        display: 'flex',
        flexWrap: 'wrap',

    },
    paper: {
        padding: theme.spacing.unit * 2,
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
    margin: {
        margin: theme.spacing.unit,
    },
    withoutLabel: {
        marginTop: theme.spacing.unit * 3,
    },
    textField: {
        flexBasis: 200,
    },
    button: {
        marginTop: '2em',
        marginLeft: '0.5em'
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

class LoginComponent extends React.Component {
    state = {
        login: '',
        password: '',
        showPassword: false,
        data: []
    };

    login = () => {
        var data = {
            username: this.state.login,
            password: this.state.password,
        }

        console.log(data)

        var request = new XMLHttpRequest();
        request.open('POST', 'http://localhost:5000/api/auth/login', true);
        request.setRequestHeader('Content-Type', 'text/plain');
        request.send(JSON.stringify(data));
        console.log(request)
        console.log(request.responseText)

    }

    handleChange = prop => event => {
        this.setState({ [prop]: event.target.value });
    };

    handleMouseDownPassword = event => {
        event.preventDefault();
    };

    handleClickShowPassword = () => {
        this.setState({ showPassword: !this.state.showPassword });
    };

    render() {
        const { classes } = this.props;

        return (
            <div className={classes.root}>

                <MuiThemeProvider theme={theme}>
                    <Grid container spacing={0}>
                        <Grid item xs />
                        <Grid item xs={6}>
                            <Paper className={classes.paper}>

                                <Typography variant="display1" gutterBottom>
                                    Please enter your login and password:
                </Typography>

                                <FormControl fullWidth className={classes.margin}>
                                    <InputLabel htmlFor="login">Login</InputLabel>
                                    <Input
                                        id="login"
                                        value={this.state.login}
                                        onChange={this.handleChange('login')}
                                    />
                                </FormControl>
                                <FormControl fullWidth className={classNames(classes.margin)}>
                                    <InputLabel htmlFor="password">Password</InputLabel>
                                    <Input
                                        id="password"
                                        value={this.state.password}
                                        onChange={this.handleChange('password')}
                                        type={this.state.showPassword ? 'text' : 'password'}
                                        endAdornment={
                                            <InputAdornment position="end">
                                                <IconButton
                                                    aria-label="Toggle password visibility"
                                                    onClick={this.handleClickShowPassword}
                                                    onMouseDown={this.handleMouseDownPassword}
                                                >
                                                    {this.state.showPassword ? <VisibilityOff /> : <Visibility />}
                                                </IconButton>
                                            </InputAdornment>
                                        }
                                    />
                                </FormControl>

                                <Button className={classes.button} onClick={this.login}
                                    variant="raised" color="primary">
                                    Send&#160;&#160;
                        <Icon className={classes.rightIcon}>send</Icon>
                                </Button>
                            </Paper>
                        </Grid>
                        <Grid item xs />
                    </Grid>
                </MuiThemeProvider>
            </div>
        );
    }
}

LoginComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(LoginComponent);