import React from "react";
import Data from "../components/Data";

function Choose() {

    console.log(Data.data)

    return (
        <div>
            {Data.data.map(c => <Data name={c.name}
                surname={c.surname} email={c.email}
                position={c.position} website={c.website}
                languages={c.languages} level={c.level} />)}
        </div>
    );
}

export default Choose;