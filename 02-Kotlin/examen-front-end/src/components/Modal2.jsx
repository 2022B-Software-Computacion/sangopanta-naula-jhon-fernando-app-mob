import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

function ModalEx2(props) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);


  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        {props.titlePage === "Actualizar" ? "Actualizar" : "Crear"}
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{ props.titlePage === "Actualizar" ?"Update Post Information"
          :"Create new Post"}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form method={props.method === "PUT" ? "PUT" : "POST"}  >
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">

              <Form.Label>Post Id</Form.Label>
              <Form.Control
                type="number"
                placeholder="id"
                name="id"
                defaultValue={props.idPost !== undefined ? props.idPost : ""}
                autoFocus
                onChange={(e) => props.handleChange(e)}
              />

            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>title</Form.Label>
              <Form.Control
                type="text"
                name="title"
                placeholder="title"
                defaultValue={props.title !== undefined ? props.title : ""}
                autoFocus
                onChange={(e) => props.handleChange(e)}
              />

            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>description</Form.Label>
              <Form.Control
                type="text"
                placeholder="description"
                name="description"
                defaultValue={props.description !== undefined ? props.description : ""}
                onChange={(e) => props.handleChange(e)}
                autoFocus
              />

            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Date</Form.Label>
              <Form.Control
                type="text"
                name="date"
                placeholder="date"
                defaultValue={props.date !== undefined ? props.date : ""}
                onChange={(e) => props.handleChange(e)}
                autoFocus
              />

            </Form.Group>
            
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Category</Form.Label>
              <Form.Control
                type="text"
                name="category"
                placeholder="category"
                defaultValue={props.category !== undefined ? props.category : ""}
                onChange={(e) => props.handleChange(e)}
                autoFocus
              />

            </Form.Group>

            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>UserId</Form.Label>
              <Form.Control
                type="text"
                name="UserId"
                placeholder="userId"
                defaultValue={props.idUser !== undefined ? props.idUser : ""}
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
          <Button variant="primary" onClick={props.titlePage === "Actualizar"?((e) => props.update(e,props.idPost)):((e) => props.create(e,post))}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
export default ModalEx2;