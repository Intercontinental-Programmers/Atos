import React from "react";
import Data from "./Data";
import Cards, { Card } from 'react-swipe-card'
import './style.css'
import cookie from 'react-cookies'

var list = [];

function DataList(props) {

  function add(c) {
    list.push(c)
    console.log(list);
  };

  function rm() {
    console.log(':C');
  }

  function post() {
    console.log(list)

    let token = cookie.load('token')
    var request = new XMLHttpRequest();
    request.open('POST', 'http://localhost:5000/api/developers/test', true);
    request.setRequestHeader('Content-Type', 'text/plain');
    request.setRequestHeader('Authorization', 'Bearer ' + token);
    request.send(JSON.stringify(list));
    console.log(request)
    console.log(request.text)
    if (window) window.location.href = "/choose"
    return true;

  }


  return (

    <Cards onEnd={post}
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