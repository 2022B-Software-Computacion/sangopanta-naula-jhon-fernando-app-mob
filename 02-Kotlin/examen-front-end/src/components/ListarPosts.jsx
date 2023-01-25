import { React, useState, useEffect } from "react";
import Post from "../components/Post";
import Eliminar from "../components/Eliminar";
import Modificar from "./Modificar";
import ModalEx2 from "./Modal2";

const ListarPosts = () => {
    const [posts, setPosts] = useState([]);

    const getPosts = async () => {
        try {
            const response = await fetch("http://127.0.0.1:8080/listarPosts");
            const posts = await response.json();
            setPosts(posts);
        } catch {
            console.log("Error al obtener los posts");
        }

    };

    const deletePost = async (idPost) => {
        await fetch(`http://localhost:8080/deletePost/${idPost}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        })
    }

    useEffect(() => {
        getPosts();
    }
        , []);

    return (
        <div>
            <h1>Lista de Posts</h1>
            {posts.map((post) => (
                <Post key={post.idPost} date={post.date} description={post.description} 
                    title={post.title} category={post.category} idPost={post.idPost} idUser={post.idUser}>
                    <div className="container-delete-update">

                        <Eliminar delete={() => deleteUser(user.idUser)}></Eliminar>

                        <ModalEx2
                            titlePage="Actualizar"
                            method="PUT"
                            idPost={post.idPost}
                            date={post.date}
                            description={post.description}
                            category={post.category}
                            idUser={post.idUser}

                        ></ModalEx2>
                    </div>
                </Post>
            ))}
        </div>
    );
};

export default ListarPosts;