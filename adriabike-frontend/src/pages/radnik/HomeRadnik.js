import React from 'react'
import {Button, Card, Container, Jumbotron} from "react-bootstrap";
import logo from "../logo.png"

const HomeRadnik = () => {
    return (
        <>
            <Container>
            <div className="jumbotron text-center bg-white" style={{padding:"25px", boxShadow:" 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 2px 0 rgba(0,0,0,0.12)"}}>
                <img src={logo} className="App-logo" alt="logo" />
                <h2>A D R I A bikestore</h2>
                <h5><i>радник</i></h5>

            </div>


            <Card style={{marginTop:"5vh", boxShadow:" 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 2px 0 rgba(0,0,0,0.12)"}} className={"border border-blue karta"}>
                <Card.Header><h3>Битни линкови</h3></Card.Header>
                <Card.Body>
                <ul style={{fontSize:"22px"}}>
                    <li><a href="warehouse">Магацини</a></li>
                    <li><a href="warehouse">Наруџбенице</a></li>
                    <li><a href="warehouse">Ценовници</a></li>


                </ul>
                </Card.Body>
            </Card>
        </Container>
        </>
    )
}

export default HomeRadnik