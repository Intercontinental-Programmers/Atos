import React from "react";
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Card, { CardActions, CardContent, CardMedia } from 'material-ui/Card';
import Button from 'material-ui/Button';
import Typography from 'material-ui/Typography';
import Grid from 'material-ui/Grid';
import Paper from 'material-ui/Paper';

const styles = {
	card: {
		maxWidth: '600px',
	},
	media: {
		height: 200,
	},
};

const divStyle = {
	marginTop: '2em',
	float:'left',
	width:'400px',
  };
  const divStyle2 = {
	width:'600px',
  };

function Data(props) {
	const { classes } = props;
	return (
		<div>
			<Grid container spacing={0} >
                <Grid item xs />
                    <Grid item xs={6}  style={divStyle2}>
                        <Paper className={classes.paper}>
		<Card className={classes.card} style={divStyle}>
			<CardMedia
				className={classes.media}
				image=""
				title="Contemplative Reptile"
			/>
			<CardContent>
				<Typography gutterBottom variant="headline" component="h2">
				{props.name} {props.surname}
          </Typography>
				<Typography component="p">
				Email : {props.email}<br/> Position: {props.position}<br/>Website: {props.website}
				<br/>Language: {props.languages}<br/>Level: {props.level}

          </Typography>
			</CardContent>
			<CardActions>
				<Button size="small" color="primary">
					Add to list
          </Button>
				<Button size="small" color="primary">
					Show more info
          </Button>
			</CardActions>
		</Card>
		</Paper>
        </Grid>
     <Grid item xs />
    </Grid>
		</div >
	);
}

Data.propTypes = {
	name: PropTypes.string.isRequired,
	classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Data);