import swal from "sweetalert";

const Eliminar = (props) => {
    return (
        <button className="eliminar-user" onClick={() => {
            swal({
                title: "¿Estás seguro?",
                text: "Una vez eliminado, no podrás recuperar este usuario!",
                icon: "warning",
                buttons:true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        props.delete()
                        swal("Poof! El usuario ha sido eliminado!", {
                            icon: "success",
                        });

                    } else {
                        swal("El usuario no ha sido eliminado!");
                    }
                });
        }}>Eliminar</button>
    )
}


export default Eliminar