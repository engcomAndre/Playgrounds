import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Route, Switch, Link, NavLink } from 'react-router-dom';
  
const Header = () => (
    <header>
        <h1>Expensify</h1>
        <NavLink to="/" exact={true} activeClassName="is-active">Go Home</NavLink><br />
        <NavLink to="/create" activeClassName="is-active">Go Create</NavLink><br />
        <NavLink to="/edit" activeClassName="is-active">Go Edit</NavLink><br />
        <NavLink to="/help" activeClassName="is-active">Go Help</NavLink>
    </header>
);


export default Header;