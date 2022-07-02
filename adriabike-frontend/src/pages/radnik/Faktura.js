import React, { useEffect, useState } from 'react'
import { FakturaService } from '../../services/FaktureService';
import { useParams } from 'react-router-dom';
import { Table, Button, Modal, Form } from 'react-bootstrap';
import { UserService } from '../../services/UserService';
import Swal from 'sweetalert2';
import { AuthenticationService } from "../../services/AuthenticationService"

const Faktura = () => {


    const [faktura, setFaktura] = useState({ stavkaFakture: [] });

    const [poruka, setPoruka] = useState({
        poruka: "",
        fakturaId: 0
    })

    const { id } = useParams();

    useEffect(() => {
        fetchFaktura();

    }, {})

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => {
      setShow(true);
    }


    async function fetchFaktura() {
        try {
            const response = await FakturaService.getFaktura(id)
            setFaktura(response.data);
        } catch (e) {
            console.error("Error while getting api" + e)
        }
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setPoruka({ ...poruka, [name]: val });
      };

    
      async function sendPoruka(){
        poruka.fakturaId = id;
        await FakturaService.sendPoruka(poruka);
        handleClose();
      }


    async function zavrsiFakturu() {
        try {
            const response = await FakturaService.endFaktura(id);
            console.log(response.data);
            if (response.data.potvrdjena == true) {
                Swal.fire("Kreirano uspesno!", 'Faktura je izdata, trazene kolicine su dostupne', 'success');
            } else {
                Swal.fire("Neuspešno", response.data.poruka, 'error').then(handleShow());
            }
        } catch (e) {
            console.error("Error while getting api")
        }
    }




    return (
        <>
            <div class="card">
                <h5 class="card-header">Фактура бр. {faktura.brojFakture}</h5>
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <h5 class="card-title">Статус фактуре: {faktura.statusFakture}</h5>
                            <div class="card-text">
                                <ul>
                                    <li>Укупан износ: <b>{faktura.ukupanIznos}.00 din</b></li>
                                    <li>Датум издавања: <b>{faktura.datumIzdavanja}</b></li>
                                    <li>Укупна основица: <b>{faktura.ukupnaOsnovica}</b></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <h5 class="card-title">Купац: {faktura.kupacImePrezime}</h5>
                            <div class="card-text">
                                <ul>
                                    <li>Адреса: <b>{faktura.kupacAdresa}</b></li>
                                    <li>Број телефона: <b>{faktura.kupacBrojTelefona}</b></li>
                                </ul>
                            </div>
                        </div>

                    </div>

                </div>
                <Table bordered striped>
                    <thead className='thead-dark'>
                        <tr>
                            <th>Назив артикал</th>
                            <th>Количина</th>
                            <th>Јединична цена</th>
                            <th>Проценат ПДВ</th>
                            <th>Износ ПДВ</th>
                            <th>Основица</th>
                            <th>Рабат</th>
                            <th>Укупно</th>
                        </tr>
                    </thead>
                    <tbody>
                        {faktura.stavkaFakture.length === 0 ?
                            <tr>
                                <td className='text-center' colSpan={5}> Нема фактура!</td>
                            </tr> :
                            faktura.stavkaFakture.map((f) => {
                                return (
                                    <tr key={f.id}>
                                        <td>{f.nazivArtikla}</td>
                                        <td>{f.kolicina}</td>
                                        <td>{f.jedinicnaCena}</td>
                                        <td>{f.procenatPDV}</td>
                                        <td>{f.iznosPDV}</td>
                                        <td>{f.osnovica}</td>
                                        <td>{f.rabat}</td>
                                        <td>{f.ukupno}</td>

                                    </tr>

                                )
                            })



                        }
                    </tbody>
                </Table>

                {
                    AuthenticationService.getRole() == "ROLE_PRODAVAC" && faktura.statusFakture == 'NEPOTVRDJENA' &&
                    <Button style={{ margin: "10px" }} onClick={zavrsiFakturu} >
                        Издај фактуру
                    </Button>
                }


            </div>


            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Порука за купца</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form>

                        <Form.Group>
                            <Form.Label>Poruka</Form.Label>
                            <Form.Control
                                type="text"
                                name="poruka"
                                value={poruka.poruka}
                                onChange={handleFormInputChange("poruka")}


                            />
                        </Form.Group>

                    </Form>
                </Modal.Body>


                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => sendPoruka()}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>

        </>
    )
}

export default Faktura