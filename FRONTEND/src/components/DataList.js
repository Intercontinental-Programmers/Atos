import React from "react";
import Data from "./Data";

function DataList(props) {
  return (

    <div>
      {props.data.map(c => <Data name={c.name} 
      surname={c.surname} email={c.email} 
      position={c.position} website={c.website} 
      languages={c.languages} level={c.level} />)}
     </div> 
  ); 
} 

export default DataList;