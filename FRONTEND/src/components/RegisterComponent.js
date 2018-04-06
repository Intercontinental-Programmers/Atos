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
        display: 'flex',
        flexWrap: 'wrap',
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
        amount: '',
        password: '',
        weight: '',
        weightRange: '',
        showPassword: false,
    };


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

                <Typography variant="display1" gutterBottom>
                    Please enter data:
                </Typography>

                <FormControl fullWidth className={classes.margin}>
                    <InputLabel htmlFor="adornment-amount">Login</InputLabel>
                    <Input
                        id="adornment-amount"
                        value={this.state.amount}
                        onChange={this.handleChange('amount')}
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
                    <InputLabel htmlFor="adornment-amount">Email</InputLabel>
                    <Input
                        id="adornment-amount"
                        value={this.state.amount}
                        onChange={this.handleChange('amount')}
                    />
                </FormControl>
                <FormControl fullWidth className={classes.margin}>
                    <InputLabel htmlFor="adornment-amount">Company name</InputLabel>
                    <Input
                        id="adornment-amount"
                        value={this.state.amount}
                        onChange={this.handleChange('amount')}
                    />
                </FormControl>
                <MuiThemeProvider theme={theme}>
                    <Button className={classes.button} variant="raised" color="primary">
                        Send&#160;&#160;
                <Icon className={classes.rightIcon}>send</Icon>
                    </Button>
                </MuiThemeProvider>
            </div>
        );
    }
}

LoginComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(LoginComponent);