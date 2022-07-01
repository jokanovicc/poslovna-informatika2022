import React from "react";
import { Col, Container, Row } from "react-bootstrap";
import logo from "./logo.png"

// Načini deklarisanja React komponenti - svi su ekvivalenti
// Funkcionalni načini su moderniji (React >= 16.8), danas češće korišćeni
// Deklarisanje putem klase je i dalje moguće naći, ali je manje elegantno

// Funkcionalan - arrow funkcija
const Home = () => (
  <Container>
    <Row>
      <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
        <h1>Dobrodošli!</h1>
        <div className="jumbotron text-center bg-white" style={{ padding: "25px", boxShadow: " 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 2px 0 rgba(0,0,0,0.12)" }}>
                    <img src={logo} className="App-logo" alt="logo" />
                    <h2>A D R I A bikestore</h2>
                    <h5><i>радник</i></h5>

        </div>
      </Col>
    </Row>
  </Container>
);


export default Home;