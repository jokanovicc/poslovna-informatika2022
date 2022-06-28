import React, { useState } from 'react'
import { Button, Card, Col, Container, Form, Row, Table } from "react-bootstrap";
import { MagacinService } from '../../services/MagacinService';
import { useParams } from 'react-router-dom';

const AnalitikaKartice = (props) => {

    const [prometMagacinskeKartice, setPrometMagacinskeKartice] = useState([]);
    const [datum, setDatum] = useState({
        startDate: "",
        endDate: ""
    })

    



    async function fetchAnalitike() {
        try {
            const response = await MagacinService.getAnalitikaBetweenDatesKartica(datum.startDate, datum.endDate, props.kartica);
            setPrometMagacinskeKartice(response.data);
        } catch (error) {
            console.error(`Greška prilikom dobavljanja analitika : ${error}`);
        }
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setDatum({ ...datum, [name]: val });
    };






    return (
        <>
                <Row>
                    <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                        <h2>Промет картице </h2>
                        <hr />
                        <Form>
                            <Form.Group>
                                <Form.Label>Стартни датум</Form.Label>
                                <Form.Control
                                    max="2022-12-12"
                                    min="2019-12-12"
                                    required
                                    type="date"
                                    name="startDate"
                                    value={datum.startDate}
                                    onChange={handleFormInputChange("startDate")}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Завршни датум</Form.Label>
                                <Form.Control
                                    max="2022-12-12"
                                    min="2019-12-12"
                                    required
                                    type="date"
                                    name="endDate"
                                    value={datum.endDate}
                                    onChange={handleFormInputChange("endDate")}
                                />
                            </Form.Group>


                            <Button style={{marginTop:"2vh"}}  onClick={fetchAnalitike}>
                                Прегледај
                            </Button>
                        </Form>
                    </Col>
                </Row>

            <Table style={{marginTop:"5vh"}} bordered striped>
                        <thead className='thead-dark'>
                            <tr>
                                <th>Цена </th>
                                <th>Количина </th>
                                <th>Вредност</th>
                                <th>Смер</th>

                            </tr>
                        </thead>
                        <tbody>
                            {prometMagacinskeKartice.length === 0 ?
                                <tr>
                                    <td>{prometMagacinskeKartice.length}Није додан ниједан!</td>
                                </tr> :
                                prometMagacinskeKartice.map((r) => {
                                    return (
                                        <tr key={r.id}>
                                            <td>{r.cena}</td>
                                            <td>{r.kolicina}</td>
                                            <td>{r.vrednost}</td>
                                            <td>{r.smer}</td>

                                        </tr>
                                    )
                                })

                            }
                        </tbody>
                    </Table>

                    </>

    )
}

export default AnalitikaKartice;