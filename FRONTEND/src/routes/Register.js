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
import Grid from 'material-ui/Grid';
import Paper from 'material-ui/Paper';

const styles = theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        flexGrow: 1,
        paddingTop: '10%',
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
        marginTop: '1em',
        marginLeft: '0.5em'
    },
    paper: {
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

class Register extends React.Component {
    state = {
        login: '',
        password: '',
        email: '',
        company: '',
    };

    register = () => {
        var data = {
            username: this.state.login,
            password: this.state.password,
            email: this.state.email,
            companyName: this.state.company
        }
    
        console.log(data)
    
        var request = new XMLHttpRequest();
        request.open('POST', 'http://localhost/api/auth/register', true);
        request.setRequestHeader('Content-Type', 'text/plain');
        request.send(JSON.stringify(data));
    
        console.log(request)
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
                <Grid container spacing={0}>
                    <Grid item xs />
                    <Grid item xs={6}>
                        <Paper className={classes.paper}>
                            <Typography variant="display1" gutterBottom>
                                Please enter data:
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
                                <InputLabel htmlFor="adornment-password">Password</InputLabel>
                                <Input
                                    id="adornment-password"
                                    type={this.state.showPassword ? 'text' : 'password'}
                                    value={this.state.password}
                                    onChange={this.handleChange('password')}
                                    endAdornment={
                                        <InputAdornment position="end">
                                            <IconButton
                                                id="password"
                                                value={this.state.password}
                                                onChange={this.handleChange('password')}
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
                            <FormControl fullWidth className={classes.margin}>
                                <InputLabel htmlFor="email">Email</InputLabel>
                                <Input
                                    id="email"
                                    value={this.state.email}
                                    onChange={this.handleChange('email')}
                                />
                            </FormControl>
                            <FormControl fullWidth className={classes.margin}>
                                <InputLabel htmlFor="company">Company Name</InputLabel>
                                <Input
                                    id="company"
                                    value={this.state.company}
                                    onChange={this.handleChange('company')}
                                />
                            </FormControl>
                            <MuiThemeProvider theme={theme}>
                                <Button className={classes.button} onClick={this.register} variant="raised" color="primary">
                                    Send&#160;&#160;
                <Icon className={classes.rightIcon}>send</Icon>
                                </Button>
                            </MuiThemeProvider>
                        </Paper>
                    </Grid>
                    <Grid item xs />
                </Grid>
            </div>
        );
    }
}

Register.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Register);