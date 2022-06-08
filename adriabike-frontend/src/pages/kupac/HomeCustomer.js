import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Container, Card, Button, Row, Col, CardDeck } from "react-bootstrap";
import { KupovinaService } from '../../services/KupovinaService';

const HomeCustomer = () => {
  const [artikli, setArtikli] = useState([]);
  const[korpaItem, setKorpaItem] = useState({
    artikalId:0,
    kolicina:0
  })


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

  async function addToKorpa(id){
    let kolicina = prompt("Унесите количину", 0);
    korpaItem.artikalId = Number(id);
    korpaItem.kolicina = Number(kolicina);
    await KupovinaService.addToKorpa(korpaItem);


  }


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
                    <Button onClick={() => addToKorpa(id)} style={{width:"100%", backgroundColor:"#2A2F4D",borderColor:"#2A2F4D"}} variant="primary">Корпа</Button>
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