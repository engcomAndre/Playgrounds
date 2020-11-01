import React from "react";


const EditExpensePage = (props) => {
    console.log(props);
    return (
        <div>
            <h3>This is page EditExpensePage</h3>
            <h4>Edit a {props.match.params.id}</h4>
        </div>
    );    
};

export default EditExpensePage;

