import React, { useEffect, useState } from 'react'
import { PoreskeService } from '../../services/PoreskeServices';
import { Table, Button, Modal, Form } from 'react-bootstrap';

const PoreskeKategorije = () => {



  const [kategorije, setKategorije] = useState([]);

  const [poreskaStopa, setPoreskaStopa] = useState({
    id: 0,
    naziv: "",
    procenatPDV: 0,
    datumStupanja: ""
  });

  const [kategorija, setKategorija] = useState({
    naziv: ""

  });


  const [show, setShow] = useState(false);

  const [show2, setShow2] = useState(false);
  const [id, setId] = useState(0);
  const handleClose = () => setShow(false);

  const handleShow = (id) => {
    setId(id);
    setShow(true);
  }

  const handleClose2 = () => setShow2(false);
  const handleShow2 = () => {
    setShow2(true);
  }


  useEffect(() => {
    fetchKategorija();
  }, [])

  async function fetchKategorija() {
    try {
      const response = await PoreskeService.getAll();
      console.log(response.data);
      setKategorije(response.data);
    } catch (e) {
      console.error("Error while getting api")
    }
  }


  const handleFormInputChange = (name) => (event) => {
    const val = event.target.value;
    setPoreskaStopa({ ...poreskaStopa, [name]: val });
  };

  const handleFormInputChangeKategorija = (name) => (event) => {
    const val = event.target.value;
    setKategorija({ ...kategorija, [name]: val });
  };

  async function sendRequest() {
    if(poreskaStopa.datumStupanja !== "" || poreskaStopa.naziv !== "" || poreskaStopa.procenatPDV > 5){
      return await PoreskeService.createStopa(poreskaStopa, id).then(() => window.location.reload());
    }else{
      alert("Молим те попуни сва поља")
    }

  }

  async function sendRequestKategorija() {
    if(kategorija.naziv !== ""){
      const response = await PoreskeService.create(kategorija);
      console.log(response.data);
      handleShow(response.data.id);
    }else{
      alert("Молим те попуни сва поља")
    }
  }




  return (
    <>
    <h2>Пореске категорије</h2>
      <Button style={{marginBottom:"20px"}} variant="primary" onClick={() => handleShow2()}>
        Додај нову пореску категорију
      </Button>
      <hr/>

      {kategorije.length === 0 ?
        <p>Prazno</p> :
        kategorije.map((k) => {
          return (
            <>
              <p>Пореска Категорија: <b>{k.naziv}</b></p>
              <Button variant="success" onClick={() => handleShow(k.id)}>
                Додај пореску стопу
              </Button>
              <Table bordered striped>
                <thead className='thead-dark'>
                  <tr>
                    <th>Пореска стопа</th>
                    <th>Датум ступања на снагу</th>
                  </tr>
                </thead>
                <tbody>
                  {k.poreskaStopaDTO.length === 0 ?
                    <tr>
                      <td className='text-center' colSpan={5}> x</td>
                    </tr> :
                    k.poreskaStopaDTO.map((x) => {
                      return (
                        <tr key={x.id}>
                          <td>{x.procenatPDV}</td>
                          <td>{x.datumStupanja}</td>

                        </tr>

                      )
                    })


                  }
                </tbody>
              </Table>



              <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                  <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                  <Form>
                    <Form.Group>
                      <Form.Label>PDV</Form.Label>
                      <Form.Control
                        type="number"
                        name="procenatPDV"
                        value={poreskaStopa.procenatPDV}
                        onChange={handleFormInputChange("procenatPDV")}


                      />
                    </Form.Group>

                    <Form.Group>
                      <Form.Label>PDV</Form.Label>
                      <Form.Control
                        type="date"
                        name="datumStupanja"
                        value={poreskaStopa.datumStupanja}
                        onChange={handleFormInputChange("datumStupanja")}


                      />
                    </Form.Group>

                  </Form>
                </Modal.Body>


                <Modal.Footer>
                  <Button variant="primary" onClick={() => sendRequest()}>
                    Save Changes
                  </Button>
                </Modal.Footer>
              </Modal>


              <Modal show={show2} onHide={handleClose2}>
                <Modal.Header closeButton>
                  <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                  <Form>

                    <Form.Group>
                      <Form.Label>naziv</Form.Label>
                      <Form.Control
                        type="text"
                        name="naziv"
                        value={kategorija.naziv}
                        onChange={handleFormInputChangeKategorija("naziv")}


                      />
                    </Form.Group>

                  </Form>
                </Modal.Body>


                <Modal.Footer>
                  <Button variant="secondary" onClick={handleClose2}>
                    Close
                  </Button>
                  <Button variant="primary" onClick={() => sendRequestKategorija()}>
                    Save Changes
                  </Button>
                </Modal.Footer>
              </Modal>




            </>

          )
        })


      }
    </>
  )
}

export default PoreskeKategorije