import { React, useEffect, useState } from "react";
import "../styles/User.css"
import { Table } from 'react-bootstrap';





const User = (props) => {


    return (
        <div className="container-user">
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Lastname</th>
                        <th>Age</th>
                        <th>Email</th>
                        <th>Posts</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{props.idUser}</td>
                        <td>{props.name}</td>
                        <td>{props.lastname}</td>
                        <td>{props.age}</td>
                        <td>{props.email}</td>
                        <td>{props.posts}</td>
                    </tr>
                </tbody>
            </Table>

            <div>{props.children}</div>

        </div>

    );
}

export default User;