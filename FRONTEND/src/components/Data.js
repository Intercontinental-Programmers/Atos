import React from "react";
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Card, { CardActions, CardContent, CardMedia } from 'material-ui/Card';
import Button from 'material-ui/Button';
import Typography from 'material-ui/Typography';

const styles = {
	card: {
		maxWidth: 345,
	},
	media: {
		height: 200,
	},
};

function Data(props) {
	const { classes } = props;
	return (
		<div>
		<Card className={classes.card}>
			<CardMedia
				className={classes.media}
				image="/static/images/cards/contemplative-reptile.jpg"
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
		</div >
	);
}

Data.propTypes = {
	name: PropTypes.string.isRequired,
	classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Data);