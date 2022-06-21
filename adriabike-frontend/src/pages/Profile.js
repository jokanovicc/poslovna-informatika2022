import React, { useEffect, useState } from "react";
import { Alert, Button, Form, Container, Row, Col } from "react-bootstrap";
import { UserService } from '../services/UserService';
import { Link } from "react-router-dom";

const Profile = () => {

    const [user, setUser] = useState({
        ime: "",
        prezime: "",
        adresa: "",
        username: "",
        email: "",
        lozinka: "",
        brojTelefona: "",
        novaLozinka:""
    })
    const [showSuccessAlert, setShowSuccessAlert] = useState(false);
    const [disabled, setDisabled] = useState(true);


    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setUser({ ...user, [name]: val });
    }

    useEffect(() => {
        fetchKorisnik();
    }, {});


    async function fetchKorisnik() {
        try {
            const response = await UserService.getUserInfo();
            setUser(response.data);
        } catch (error) {
            console.error(`Greška prilikom dobavljanja korisnika : ${error}`);
        }
    }

    async function editKorisnik() {
        try {
            await UserService.updateProfile(user);
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
                    Ваш налог успешно ажуриран
                </Alert>
            )}

            <Container className={"kontejner"}>
                <Row>
                    <Col md={{ span: 8, offset: 2 }} style={{ textAlign: "center" }}>

                        <h1>Ваше информације</h1>
                        <Form>
                            <Form.Group>
                                <Form.Label>Име</Form.Label>
                                <Form.Control
                                    onChange={handleFormInputChange("ime")}
                                    name="ime"
                                    value={user.ime}
                                    as="input"
                                    disabled={disabled}

                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Презиме</Form.Label>
                                <Form.Control
                                    onChange={handleFormInputChange("prezime")}
                                    name="prezime"
                                    value={user.prezime}
                                    as="input"
                                    disabled={disabled}

                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Корисничко име</Form.Label>
                                <Form.Control
                                    onChange={handleFormInputChange("username")}
                                    name="username"
                                    value={user.username}
                                    as="input"
                                    disabled={disabled}

                                />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Адреса</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="adresa"
                                    value={user.adresa}
                                    onChange={handleFormInputChange("adresa")}
                                    disabled={disabled}



                                />
                            </Form.Group>

                            <Row>
                                <Col>
                                    <Form.Group>
                                        <Form.Label>Имејл</Form.Label>
                                        <Form.Control
                                            disabled="true"
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
                                            disabled={disabled}

                                        />
                                    </Form.Group>

                                </Col>
                            </Row>

                            <Row>
                                <Col>
                                    <Form.Group>
                                        <Form.Label>Корисничко име</Form.Label>
                                        <Form.Control
                                            disabled="true"
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
                                            placeholder="Унети нову лозинку"
                                            type="text"
                                            name="lozinka"
                                            value={user.novaLozinka}
                                            onChange={handleFormInputChange("novaLozinka")}
                                            disabled={disabled}

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

export default Profile