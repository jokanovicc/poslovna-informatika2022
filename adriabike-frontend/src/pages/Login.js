import React, { useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { AuthenticationService } from "../services/AuthenticationService";

const Login = () => {
  // useState je React Hook - funkcija koja dozvoljava definisanje stanja i životnog ciklusa unutar funkcija
  // Moguće ih je koristiti jedino u funkcionalnim komponentama
  // useState definiše dve vrednosti:
  // * Prva je objekat u stanju kome se može pristupati
  // * Druga je funkcija pomoću koje se taj objekat menja - promenu jedino treba raditi putem ove funkcije
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const handleFormInputChange = (name) => (event) => {
    const val = event.target.value;
    setCredentials({ ...credentials, [name]: val });
  };


  const login = async () => {
    await AuthenticationService.login(credentials);
  };


  return (
    <Container>
      <Row>
        <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center", fontSize: "21px", padding:"20px"}}>
        <h3>Пријава на систем</h3>
        <hr/>
          <Form>
            <Form.Group>
              <Form.Label>Корисничко име</Form.Label>
              <Form.Control
                type="text"
                name="username"
                value={credentials.username}
                onChange={handleFormInputChange("username")}
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Лозинка</Form.Label>
              <Form.Control
                type="password"
                name="password"
                value={credentials.password}
                onChange={handleFormInputChange("password")}
              />
            </Form.Group>
            <Button style={{marginTop:"20px"}} variant="primary" onClick={login}>
              ПРИЈАВА
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;