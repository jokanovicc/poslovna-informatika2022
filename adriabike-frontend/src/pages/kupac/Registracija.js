import React, { useState, useEffect } from 'react'
import { Button, Col, Container, Form, Table, Row } from 'react-bootstrap';
import { AuthenticationService } from '../../services/AuthenticationService';

const Registracija = () => {




    const [user,setUser] = useState({
        ime:"",
        prezime:"",
        adresa:"",
        username:"",
        email:"",
        lozinka:"",
        brojTelefona:"",

    })

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setUser({ ...user, [name]: val });
    }

    const sendRequest = async () => {
        await AuthenticationService.register(user);
        window.location.assign("/")
    }



    return (
        <>
            <Container>
                <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                    <h3>Регистрација на систем</h3>
                    <Form>
                        <Form.Group>
                            <Form.Label>Име</Form.Label>
                            <Form.Control
                                type="text"
                                name="ime"
                                value={user.ime}
                                onChange={handleFormInputChange("ime")}


                            />
                        </Form.Group>


                        <Form.Group>
                            <Form.Label>Презиме</Form.Label>
                            <Form.Control
                                type="text"
                                name="prezime"
                                value={user.prezime}
                                onChange={handleFormInputChange("prezime")}


                            />
                        </Form.Group>


                        <Form.Group>
                            <Form.Label>Адреса</Form.Label>
                            <Form.Control
                                type="text"
                                name="adresa"
                                value={user.adresa}
                                onChange={handleFormInputChange("adresa")}


                            />
                        </Form.Group>

                        <Row>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Имејл</Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="email"
                                        value={user.email}
                                        onChange={handleFormInputChange("email")}


                                    />
                                </Form.Group>
                            </Col>
                            <Col>

                                <Form.Group>
                                    <Form.Label>Број телефона</Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="brojTelefona"
                                        value={user.brojTelefona}
                                        onChange={handleFormInputChange("brojTelefona")}

                                    />
                                </Form.Group>

                            </Col>
                        </Row>

                        <Row>
                            <Col>
                                <Form.Group>
                                    <Form.Label>Корисничко име</Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="user"
                                        value={user.username}
                                        onChange={handleFormInputChange("username")}


                                    />
                                </Form.Group>
                            </Col>
                            <Col>

                                <Form.Group>
                                    <Form.Label>Лозинка</Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="lozinka"
                                        value={user.lozinka}
                                        onChange={handleFormInputChange("lozinka")}

                                    />
                                </Form.Group>

                            </Col>
                        </Row>



                    </Form>

                    <Button style={{ marginTop: "20px" }} variant="primary" onClick={sendRequest}>
                       Региструј се
                    </Button>
                </Col>


            </Container>

        </>)
}

export default Registracija