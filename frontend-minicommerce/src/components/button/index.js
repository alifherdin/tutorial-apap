import React from "react";
import classes from "./styles.modules.css"

const Button = (props) => {
    const {action, children} = props;

    return <button onClick={action}> {children} </button>
}

export default Button;