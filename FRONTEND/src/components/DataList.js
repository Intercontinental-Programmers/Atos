import React from "react";
import Data from "./Data";
import Cards, { Card } from 'react-swipe-card'
import './style.css'

var data = [];

function DataList(props) {

  function add(c) {
    data.push(c)
    console.log(data);
  };

  function rm() {
    console.log(':C');
  }

  return (

    <Cards onEnd={() => { if (window) window.location.href = "/choose" 
      return true; }} 
      className='master-root'>
      {props.data.map(c =>
        <Card
          onSwipeLeft={rm}
          onSwipeRight={() => add(c)}>

          <Data name={c.name}
            surname={c.surname} email={c.email}
            position={c.position} website={c.website}
            languages={c.languages} level={c.level} />
        </Card>
      )}
    </Cards>
  )
}

export default DataList;