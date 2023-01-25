import React from "react";
import {NavLink} from "react-router-dom"
import "../styles/NavBar.css"

const NavBar = () => {
    return (
        <div className="container">
            <ul>
                <li>
                    <NavLink to="/" className="nav-link"> Home</NavLink>
                </li>
                <li>
                    <NavLink to="/posts" className="nav-link"> Posts</NavLink>
                </li>
                <li>
                    <NavLink to="/users" className="nav-link"> Users</NavLink>
                </li>
            </ul>
        </div>
    )
}

export default NavBar