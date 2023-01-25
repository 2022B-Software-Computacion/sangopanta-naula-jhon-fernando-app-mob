import React from "react";
import "../styles/Post.css"
import { Table } from 'react-bootstrap';

const Post = (props) => {
    return (
        <div className="container-user">
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Category</th>
                        <th>User id</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{props.idPost}</td>
                        <td>{props.title}</td>
                        <td>{props.description}</td>
                        <td>{props.date}</td>
                        <td>{props.category}</td>
                        <td>{props.idUser}</td>
                    </tr>
                </tbody>
            </Table>

            <div>{props.children}</div>

        </div>

    );
}

export default Post;