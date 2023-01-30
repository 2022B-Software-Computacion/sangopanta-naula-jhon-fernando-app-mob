import { React, useState, useEffect } from "react";
import Post from "../components/Post";
import Eliminar from "../components/Eliminar";
import Modificar from "./Modificar";
import ModalEx2 from "./Modal2";

const ListarPosts = () => {
    const [posts, setPosts] = useState([]);

    const [postFront, setPostFront] = useState({
        idPost: 0,
        date: '',
        description: '',
        title: '',
        category: '',
        idUser: 0
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        const newPost = Object.assign({}, postFront);
        if (e.target.value){
            newPost[name] = value;
        }
        setPostFront(newPost);
    }



    const getPosts = async () => {
        try {
            const response = await fetch("http://127.0.0.1:8080/listarPosts");
            const posts = await response.json();
            setPosts(posts);
        } catch {
            console.log("Error al obtener los posts");
        }
    };

    const createPost = async (e,postNew) => {
        await fetch("http://localhost:8080/savePost", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postNew)
        })
        handleChange(e);
        getPosts();

    }

    const updatePost = async (e,idPost) => {
        await fetch(`http://localhost:8080/updatePost/${idPost}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postFront)
        })
        handleChange(e);
        getPosts();
    }



    const deletePost = async (idPost) => {
        await fetch(`http://localhost:8080/deletePost/${idPost}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        })
        getPosts();
    }

    useEffect(() => {
        getPosts();
    }
        , []);

    return (
        <div>
            <ModalEx2 
                titlePage="Crear"
                method="POST"
                handleChange={handleChange}
                createPost={(e) => createPost(e,postFront)}
                >
            </ModalEx2>
            <h1>Lista de Posts</h1>
            {posts.map((post) => (
                <Post key={post.idPost} date={post.date} description={post.description} 
                    title={post.title} category={post.category} idPost={post.idPost} idUser={post.idUser}>
                    <div className="container-delete-update">

                        <Eliminar delete={() => deletePost(post.idPost)}></Eliminar>

                        <ModalEx2
                            titlePage="Actualizar"
                            method="PUT"
                            idPost={post.idPost}
                            date={post.date}
                            description={post.description}
                            category={post.category}
                            idUser={post.idUser}
                            title={post.title}
                            handleChange={handleChange}
                            update={(e) => updatePost(e,post.idPost)}

                        ></ModalEx2>
                    </div>
                </Post>
            ))}
        </div>
    );
};

export default ListarPosts;