import React, { useState, useEffect } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Button, Col, Container, Form, Table, Row, Modal } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import './../../radnik.css'



const DodavanjeRobe = () => {



    const [sugestije, setSugestije] = useState([]);
    const [artikli, setArtikli] = useState([]);

    const [idArtikla, setIdArtikla] = useState();


    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => {
        setShow(true);
    }

    const [dobavljanjeRobe, setDobavljanjeRobe] = useState({
        naziv: "",
        opis: "",
        pakovanje: 1,
        slika: "",
        poreskaId: 0,
        cena: 0,
        kolicina: 0,
        magacinId: 0,
        artikalId:0
    })


    const [text, setText] = useState();

    const [listaNoveRoba, setListNoveRobe] = useState([]);


    const [poreskaLista, setPoreskaLista] = useState([]);
    const { id } = useParams();


    useEffect(() => {
        fetchPoreske();
        loadArtikli();


    }, [])






    const onChangeHandler = (text) => {
        let matches = []

        if (text.length > 0) {
            matches = artikli.filter(a => {
                const regex = new RegExp(`${text}`, "gi");
                return a.naziv.match(regex)

            })

        }
        console.log(matches);
        setSugestije(matches);
        setText(text);
    }



    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setDobavljanjeRobe({ ...dobavljanjeRobe, [name]: val });
    }

    async function loadArtikli() {
        try {
            const response = await ArtikliService.getListBox();
            setArtikli(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }


    const fetchPoreske = async () => {
        try {
            const response = await ArtikliService.getPoreske();
            setPoreskaLista(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }

    const addToList = async () => {
        console.log(dobavljanjeRobe);
        dobavljanjeRobe.magacinId = id;
        const newList = listaNoveRoba.concat(dobavljanjeRobe);
        setListNoveRobe(newList);
        cleanField();

    }

    const sendRequest = async () => {
        console.log(listaNoveRoba);
        if(listaNoveRoba.length > 0){
            await ArtikliService.dobaviRobu(listaNoveRoba);
            Swal.fire('???????? ???? ?????????????? ??????????????!', '?????????????????? ???????? ????????????????????...', "success").then(
                () => window.location.assign("/prijemnice")
            );
        }else{
            alert("Lista je prazna!")
        }

    }

    function cleanField() {
        setDobavljanjeRobe({
            naziv: "",
            opis: "",
            pakovanje: 1,
            slika: "",
            poreskaId: 0,
            cena: 0,
            kolicina: 0,
            magacinId: 1
        })
    }

    const onSuggestHandler = (text) => {
        setIdArtikla(text.id);
        setText(text.naziv + text.id);
        setArtikli(artikli.filter((a)=> a.id != text.id));
        setSugestije([]);
    }

    async function sendRequest2(){

        if(idArtikla != null){
            dobavljanjeRobe.magacinId = id;
            dobavljanjeRobe.artikalId = idArtikla;
            dobavljanjeRobe.naziv = text;
            const newList = listaNoveRoba.concat(dobavljanjeRobe);
            setListNoveRobe(newList);
            cleanField();
        }else{
            alert("Prazno!")
        }



    }




    return (
        <>
            <Container>
                <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                    <h3>???????? ???????? ?? ????????????</h3>
                    <Form>
                        <Form.Group>
                            <Form.Label>??????????</Form.Label>
                            <input type="text"
                                onBlur={() => {
                                    setTimeout(() => {
                                        setSugestije([])
                                    }, 100)
                                }}
                                className='col-md-12 input'
                                onChange={e => onChangeHandler(e.target.value)}
                                value={text} />
                            {sugestije && sugestije.map((s, i) =>
                                <div className='autocomplete' onClick={() => onSuggestHandler(s)} style={{ cursor: "pointer", borderLeft: "1px solid grey", borderRight: "1px solid black", borderBottom: "1px solid black" }} key={i}>{s.naziv}</div>)}
                        </Form.Group>
                        <Button style={{marginBottom:"20px", marginTop:"10px"}} variant="success" onClick={() => handleShow()}>
        ???????? ????????
      </Button>







                        <Row>
                            <Col>

                                <Form.Group>
                                    <Form.Label>????????????????</Form.Label>
                                    <Form.Control
                                        type="number"
                                        name="kolicina"
                                        value={dobavljanjeRobe.kolicina}
                                        onChange={handleFormInputChange("kolicina")}

                                    />
                                </Form.Group>

                            </Col>
                        </Row>



                    </Form>


                    <br />
                    <h5>?????????????????? ??????????????</h5>
                    <Table bordered striped>
                        <thead className='thead-dark'>
                            <tr>
                                <th>?????????? </th>
                                <th>???????????????? </th>

                            </tr>
                        </thead>
                        <tbody>
                            {listaNoveRoba.length === 0 ?
                                <tr>
                                    <td>{listaNoveRoba.length}???????? ?????????? ??????????????!</td>
                                </tr> :
                                listaNoveRoba.map((r) => {
                                    return (
                                        <tr key={r.naziv}>
                                            <td>{r.naziv}</td>
                                            <td>{r.kolicina}</td>

                                        </tr>
                                    )
                                })

                            }
                        </tbody>
                    </Table>


                    <Button style={{ marginLeft: "20px" }} onClick={sendRequest2}>
                        ??????????
                    </Button>
                    <Button style={{ marginLeft: "20px" }} onClick={sendRequest}>
                        ?????????????? ????????????????????
                    </Button>
                </Col>


                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>???????? ???????? ???????? ?? ????????????</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                                    <Form.Group>
                                        <Form.Label>??????????</Form.Label>
                                        <Form.Control
                                            type="text"
                                            name="naziv"
                                            value={dobavljanjeRobe.naziv}
                                            onChange={handleFormInputChange("naziv")}


                                        />
                                    </Form.Group>


                                    <Form.Group>
                                        <Form.Label>????????</Form.Label>
                                        <Form.Control
                                            as="textarea" rows={3}
                                            type="text"
                                            name="opis"
                                            value={dobavljanjeRobe.opis}
                                            onChange={handleFormInputChange("opis")}


                                        />
                                    </Form.Group>


                                    <Form.Group>
                                        <Form.Label>URL ???? ??????????</Form.Label>
                                        <Form.Control
                                            type="text"
                                            name="slika"
                                            value={dobavljanjeRobe.slika}
                                            onChange={handleFormInputChange("slika")}


                                        />
                                    </Form.Group>

                                    <Form.Group>
                                        <Form.Label>???????????????? ????????</Form.Label>
                                        <Form.Control
                                            type="number"
                                            name="cena"
                                            value={dobavljanjeRobe.cena}
                                            onChange={handleFormInputChange("cena")}


                                        />
                                    </Form.Group>

                                    <Form.Group>
                                        <Form.Label>????????????????</Form.Label>
                                        <Form.Control
                                            type="number"
                                            name="kolicina"
                                            value={dobavljanjeRobe.kolicina}
                                            onChange={handleFormInputChange("kolicina")}

                                        />
                                    </Form.Group>

                                    <Form.Group>
                                        <Form.Label>?????????????? ??????????????????</Form.Label>
                                        <Form.Select value={dobavljanjeRobe.poreskaId} onChange={handleFormInputChange("poreskaId")}>
                                            {poreskaLista.map(opt => (
                                                <option value={opt.id}>{opt.naziv}</option>
                                            ))}
                                        </Form.Select>
                                    </Form.Group>

                                </Form>
                    </Modal.Body>


                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={() => addToList()}>
                            Save Changes
                        </Button>
                    </Modal.Footer>
                </Modal>


            </Container>

        </>)



}

export default DodavanjeRobe