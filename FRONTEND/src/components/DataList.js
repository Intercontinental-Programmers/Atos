import React from "react";
import Data from "./Data";

function DataList(props) {
  return (

  	//mapa iteruje po każdym itemie
    <div>
      {props.data.map(c => <Data key={c.id} name={c.name} description={c.description} />)}
     </div> 
  ); 
} 

export default DataList;