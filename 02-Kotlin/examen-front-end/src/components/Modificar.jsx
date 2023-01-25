const Modificar = () => {
    return(
        <div>
            <button className="modificar-user" 
            onClick={() => {
                swal({
                    title: "¿Estás seguro?",
                    text: "Una vez modificado, no podrás recuperar este usuario!",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                })
                    .then((willModify) => {
                        if (willModify) {
                            swal("Poof! El usuario ha sido modificado!", {
                                icon: "success",
                            });
                        } else {
                            swal("El usuario no ha sido modificado!");
                        }
                    });
            }}

            >Modificar</button>
        </div>
    )
}

export default Modificar