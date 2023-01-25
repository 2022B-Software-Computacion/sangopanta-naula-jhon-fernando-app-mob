import { useState, useEffect } from "react";
import Eliminar from "./Eliminar";
import Post from "./Post";
import User from "./User";
import "../styles/ListarUser.css"
import ModalEx from "./Modal";


const Users = () => {




    const [users, setUsers] = useState([])

    const [userFront, setUserFront] = useState({
        idUser: 0,
        name: '',
        lastName: '',
        age: 0,
        email: '',
        posts: []
    });


    const handleChange = (e) => {
        const { name, value } = e.target;
        const newUser = Object.assign({}, userFront);
        if (e.target.value) {
            newUser[name] = value;
        }
        setUserFront(newUser);
    }

    const getUsers = async () => {
        try {
            const response = await fetch('http://localhost:8080/listarUsers')
            const data = await response.json()
            setUsers(data)
        }
        catch (error) {
            console.log(error)
        }
    }

    const deleteUser = async (idUser) => {
        await fetch(`http://localhost:8080/deleteUser/${idUser}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        getUsers()
    }


    const updateUser = async (e,idUser) => {

        await fetch(`http://localhost:8080/updateUser/${idUser}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userFront)
        })
        handleChange(e); 
        getUsers()
    }


    const createUser = async (userNew) => {
        await fetch('http://localhost:8080/saveUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'cors': 'no-cors'
            },
            body: JSON.stringify(userNew)
        })
        handleChange(e);
        getUsers()
    }


    
    

    useEffect(() => {
        getUsers()
    }, [])


    return (

        <div>
            <div className="create-user">
                <ModalEx 
                    title="Crear"
                    method="POST"
                    handleChange={handleChange}
                    create={(e) => createUser(e,userFront)}
                ></ModalEx>

            </div>
            <p>Lista de usuarios </p>
            {users.map((user) => (
                <User key={user.idUser} name={user.name} lastname={user.lastName} idUser={user.idUser} 
                age={user.age} email={user.email}
                posts={user.posts.map((post) => (
                    <Post key={post.idPost} date={post.date} description={post.description} idPost={post.idPost}
                    title={post.title} idUser={post.idUser} category={post.category}></Post>                    
                    
                ))
                }>
                    <div className="container-delete-update">

                        <Eliminar delete={() => deleteUser(user.idUser) }></Eliminar>
                        
                        <ModalEx
                            title="Actualizar"
                            method="PUT"
                            idUser={user.idUser}
                            name={user.name}
                            lastName={user.lastName}
                            age={user.age}
                            email={user.email}
                            handleChange={handleChange}
                            update = {(e) => updateUser(e,user.idUser)}

                        ></ModalEx>
                    </div>

                </User>
            ))}


        </div>
    );
};


export default Users;