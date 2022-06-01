import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Container, Card, Button, Row, Col, CardDeck } from "react-bootstrap";

const HomeCustomer = () => {
  const [artikli, setArtikli] = useState([]);


  useEffect(() => {
    fetchArtikli();
  }, [])


  async function fetchArtikli() {
    try {
      const response = await ArtikliService.getAll();
      console.log(response.data);
      setArtikli(response.data);
    } catch (e) {
      console.error("Error while getting api")
    }

  }

  const renderCard = (artikal, id) => {
    return (
      <>
        <Card style={{ width: "20rem", textAlign: "center", display: "inline-block", margin: "35px", boxShadow: " 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 5px 0 rgba(0,0,0,0.20)"}} key={id} >
          <Card.Img style={{ height: "260px", width: "310px", padding: "10px" }} className="slikaProizvoda" variant="top" src={artikal.slika} />
          <Card.Body>
            <Card.Title><h5><a href={/artikal/ + artikal.id}>{artikal.naziv}</a></h5></Card.Title>
            <Card.Text><h5>{artikal.ukupnaCena}РСД</h5></Card.Text>
            <hr />
            <Button>Купи</Button>
          </Card.Body>
        </Card>
      </>
    );

  };

  return (
    <>

      <h2 style={{textAlign:"center"}}>Тренутна на стању</h2>
      <hr/>
      <Row lg={3}>
        {artikli &&
          artikli.map((product) => {
            const { id, slika, ukupnaCena, naziv} =
              product;
            return (
              <Col className="d-flex">
                <Card className="flex-fill" key={id} style={{boxShadow:" 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 5px 0 rgba(0,0,0,0.20)"}} >
                  <Card.Img style={{maxWidth:"100%", 
height:"15vw", objectFit:"cover"}} variant="top" src={slika} />
                  <Card.Body className=" justify-content-center mt-auto">
                    <Card.Title>{naziv}</Card.Title>
                    <Card.Text>{product.opis}</Card.Text>
                    <Card.Text>{ukupnaCena}.00 RSD</Card.Text>
                    <Button style={{width:"100%", backgroundColor:"#2A2F4D",borderColor:"#2A2F4D"}} variant="primary">Корпа</Button>
                  </Card.Body>
                </Card>
              </Col>
            );
          })}
      </Row>
    </>
  )
}

export default HomeCustomer