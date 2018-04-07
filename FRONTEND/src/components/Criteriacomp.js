import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import ListSubheader from 'material-ui/List/ListSubheader';
import List, { ListItem, ListItemSecondaryAction, ListItemIcon, ListItemText } from 'material-ui/List';
import Collapse from 'material-ui/transitions/Collapse';
import InboxIcon from 'material-ui-icons/MoveToInbox';
import DraftsIcon from 'material-ui-icons/Drafts';
import SendIcon from 'material-ui-icons/Send';
import ExpandLess from 'material-ui-icons/ExpandLess';
import ExpandMore from 'material-ui-icons/ExpandMore';
import StarBorder from 'material-ui-icons/StarBorder';
import Checkbox from 'material-ui/Checkbox';
import Lanlist from "../components/Lanlist";
import Lvllist from "../components/Lvllist";
import Listcity from "../components/Listcity";


const styles = theme => ({
  root: {
    width:'70%',
  }
});

class Criteriacomp extends React.Component {
  state = { open: false };

  handleClick = () => {
    this.setState({ open: !this.state.open });
  };

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <List divider
          component="nav"
          subheader={<ListSubheader component="div">Search options</ListSubheader>}
        >
          <ListItem button onClick={this.handleClick}>
            <ListItemText inset primary="Languages" />
            {this.state.open ? <ExpandLess /> : <ExpandMore />}
          </ListItem>
          <Collapse in={this.state.open}>
            <List component="div" disablePadding>
              <ListItem button className={classes.nested}>
                <Lanlist/>
              </ListItem>
            </List>
          </Collapse>
        </List>

        <List
          component="nav"
          subheader={<ListSubheader component="div">Search options</ListSubheader>}
        >
          <ListItem button onClick={this.handleClick}>
            <ListItemText inset primary="Level" />
            {this.state.open ? <ExpandLess /> : <ExpandMore />}
          </ListItem>
          <Collapse in={this.state.open}>
            <List component="div" disablePadding>
              <ListItem button className={classes.nested}>
                <Lvllist/>
              </ListItem>
            </List>
          </Collapse>
        </List>
        <List
          component="nav"
          subheader={<ListSubheader component="div">Search options</ListSubheader>}
        >
          <ListItem button onClick={this.handleClick}>
            <ListItemText inset primary="City" />
            {this.state.open ? <ExpandLess /> : <ExpandMore />}
          </ListItem>
          <Collapse in={this.state.open} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              <ListItem button className={classes.nested}>
                <Listcity/>
              </ListItem>
            </List>
          </Collapse>
        </List>
      </div>
    );
  }
}

Criteriacomp.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Criteriacomp);