import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

function ModalEx(props) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);


  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        {props.title === "Actualizar" ? "Actualizar" : "Crear"}
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{ props.title === "Actualizar" ?"Update User Information"
          :"Create new User"}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form method={props.method === "PUT" ? "PUT" : "POST"}  >
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">

              <Form.Label>User Id</Form.Label>
              <Form.Control
                type="number"
                placeholder="id"
                name="id"
                defaultValue={props.idUser !== undefined ? props.idUser : ""}
                autoFocus
                onChange={(e) => props.handleChange(e)}
              />

            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                name="name"
                placeholder="name"
                defaultValue={props.name !== undefined ? props.name : ""}
                autoFocus
                onChange={(e) => props.handleChange(e)}
              />

            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Lastname</Form.Label>
              <Form.Control
                type="text"
                placeholder="Lastname"
                name="lastName"
                defaultValue={props.lastName !== undefined ? props.lastName : ""}
                onChange={(e) => props.handleChange(e)}
                autoFocus
              />

            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>age</Form.Label>
              <Form.Control
                type="numeric"
                name="age"
                placeholder="Age"
                defaultValue={props.age !== undefined ? props.age : ""}
                onChange={(e) => props.handleChange(e)}
                autoFocus
              />

            </Form.Group>
            
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                name="email"
                placeholder="name@example.com"
                defaultValue={props.email !== undefined ? props.email : ""}
                onChange={(e) => props.handleChange(e)}
                autoFocus
              />

            </Form.Group>

          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={props.title === "Actualizar"?((e) => props.update(e,props.idUser)):((e) => props.create(user))}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
export default ModalEx;