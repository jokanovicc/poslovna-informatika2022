import React, { useState, useEffect } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Button, Col, Container, Form, Table, Row } from 'react-bootstrap';
import { useParams } from 'react-router-dom';



const DodavanjeRobe = () => {


    const [dobavljanjeRobe, setDobavljanjeRobe] = useState({
        naziv: "",
        opis: "",
        pakovanje: 1,
        slika: "",
        poreskaId: 0,
        cena: 0,
        kolicina: 0,
        magacinId: 0
    })

    const [listaNoveRoba, setListNoveRobe] = useState([]);

    const [poreskaLista, setPoreskaLista] = useState([]);
    const { id } = useParams();


    useEffect(() => {
        fetchPoreske();


    }, [])



    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setDobavljanjeRobe({ ...dobavljanjeRobe, [name]: val });
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
        await ArtikliService.dobaviRobu(listaNoveRoba);
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




    return (
        <>
            <Container>
                <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                    <h3>Унос робе у систем</h3>
                    <Form>
                        <Form.Group>
                            <Form.Label>Назив</Form.Label>
                            <Form.Control
                                type="text"
                                name="naziv"
                                value={dobavljanjeRobe.naziv}
                                onChange={handleFormInputChange("naziv")}


                            />
                        </Form.Group>


                        <Form.Group>
                            <Form.Label>Опис</Form.Label>
                            <Form.Control
                                as="textarea" rows={3}
                                type="text"
                                name="opis"
                                value={dobavljanjeRobe.opis}
                                onChange={handleFormInputChange("opis")}


                            />
                        </Form.Group>


                        <Form.Group>
                            <Form.Label>URL до слике</Form.Label>
                            <Form.Control
                                type="text"
                                name="slika"
                                value={dobavljanjeRobe.slika}
                                onChange={handleFormInputChange("slika")}


                            />
                        </Form.Group>

                        <Row>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Цена</Form.Label>
                                    <Form.Control
                                        type="number"
                                        max="1000"
                                        name="cena"
                                        value={dobavljanjeRobe.cena}
                                        onChange={handleFormInputChange("cena")}


                                    />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Количина</Form.Label>
                                    <Form.Control
                                        type="number"
                                        name="kolicina"
                                        value={dobavljanjeRobe.kolicina}
                                        onChange={handleFormInputChange("kolicina")}

                                    />
                                </Form.Group>
                            </Col>
                        </Row>



                        <Form.Group>
                            <Form.Label>Пореска групација</Form.Label>
                            <Form.Select value={dobavljanjeRobe.poreskaId} onChange={handleFormInputChange("poreskaId")}>
                                {poreskaLista.map(opt => (
                                    <option value={opt.id}>{opt.naziv}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>




                    </Form>

                    <Button style={{ margin: "20px" }} onClick={addToList}>
                        Унеси артикал
                    </Button>

                    <br />
                    <h5>Добављени артикли</h5>
                    <Table bordered striped>
                        <thead className='thead-dark'>
                            <tr>
                                <th>Назив </th>
                                <th>Цена </th>
                                <th>Количина </th>

                            </tr>
                        </thead>
                        <tbody>
                            {listaNoveRoba.length === 0 ?
                                <tr>
                                    <td>{listaNoveRoba.length}= No projects added!</td>
                                </tr> :
                                listaNoveRoba.map((r) => {
                                    return (
                                        <tr key={r.naziv}>
                                            <td>{r.naziv}</td>
                                            <td>{r.cena}</td>
                                            <td>{r.kolicina}</td>

                                        </tr>
                                    )
                                })

                            }
                        </tbody>
                    </Table>


                    <Button style={{ marginLeft: "20px" }} onClick={sendRequest}>
                        Креирај пријемницу
                    </Button>
                </Col>


            </Container>

        </>)



}

export default DodavanjeRobe