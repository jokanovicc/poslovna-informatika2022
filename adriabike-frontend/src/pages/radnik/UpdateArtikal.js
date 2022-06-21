import React, { useEffect, useState } from "react";
import { Alert, Button, Form, Container, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useParams } from 'react-router-dom';
import { ArtikliService } from "../../services/ArtikliService";

const UpdateArtikal = () => {

    const [artikal, setArtikal] = useState({
        naziv: "",
        opis: "",
        pakovanje: "",
        slika: ""

    })
    const { id } = useParams();
    const [showSuccessAlert, setShowSuccessAlert] = useState(false);
    const [disabled, setDisabled] = useState(true);


    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setArtikal({ ...artikal, [name]: val });
    }

    useEffect(() => {
        fetchArtikal();
    }, {});


    async function fetchArtikal() {
        try {
            const response = await ArtikliService.getById(id);
            setArtikal(response.data);
        } catch (error) {
            console.error(`Greška prilikom dobavljanja korisnika : ${error}`);
        }
    }

    async function editKorisnik() {
        try {
            await ArtikliService.update(id, artikal);
            setShowSuccessAlert(true);
        } catch (error) {
            console.error(`Greška prilikom аžuriranja stanja korisnika: ${error}`);
        }
    }


    function handleGameClick() {
        setDisabled(!disabled);
    }


    return (
        <>
            {showSuccessAlert && (
                <Alert
                    variant="success"
                    onClose={() => setShowSuccessAlert(false)}
                    dismissible
                >
                    Артикал успешно ажуриран
                </Alert>
            )}

            <Container className={"kontejner"}>
                <Row>
                    <Col md={{ span: 8, offset: 2 }} style={{ textAlign: "center" }}>

                        <h3>Измена артикла</h3>
                        <Form>
                            <Form.Group>
                                <Form.Label>Назив</Form.Label>
                                <Form.Control
                                    onChange={handleFormInputChange("naziv")}
                                    name="naziv"
                                    value={artikal.naziv}
                                    as="input"
                                    disabled={disabled}

                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Опис</Form.Label>
                                <Form.Control
                                    onChange={handleFormInputChange("opis")}
                                    name="opis"
                                    value={artikal.opis}
                                    as="input"
                                    disabled={disabled}

                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Слика</Form.Label>
                                <Form.Control
                                    onChange={handleFormInputChange("slika")}
                                    name="slika"
                                    value={artikal.slika}
                                    as="input"
                                    disabled={disabled}

                                />
                            </Form.Group>

                            <Row>
                                <Col>
                                    <Form.Group>
                                        <Form.Label>Паковање</Form.Label>
                                        <Form.Control
                                            disabled="true"
                                            type="number"
                                            name="pakovanje"
                                            value={artikal.pakovanje}
                                            onChange={handleFormInputChange("pakovanje")}


                                        />
                                    </Form.Group>
                                </Col>
                                <Col>

                                    <Form.Group>
                                        <Form.Label>Цена по последњем ценовнику</Form.Label>
                                        <Form.Control
                                            disabled="true"
                                            type="text"
                                            name="ukupnaCena"
                                            value={artikal.ukupnaCena}
                                            onChange={handleFormInputChange("ukupnaCena")}

                                        />
                                    </Form.Group>

                                </Col>
                            </Row>

                            <hr />
                            <Button onClick={handleGameClick}>
                                Отвори поља
                            </Button>
                            <br/>
                            {
                                disabled == false &&
                                <Button style={{marginTop:"10px", width:"100%"}} variant="primary" onClick={() => editKorisnik()}>
                                    Сачувај измене
                                </Button>
                            }

                        </Form>
                    </Col>
                </Row>
            </Container>

        </>
    )
}

export default UpdateArtikal