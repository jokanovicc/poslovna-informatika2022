import React, { useEffect, useState } from 'react'
import { MagacinService } from '../../services/MagacinService';
import { Button, Col, Container, Form, Table, Row } from 'react-bootstrap';
import Swal from 'sweetalert2';


const MagaciniAdmin = () => {

  const [magacini, setMagacini] = useState([]);
  const [magacin,setMagacin] = useState({
    naziv:"",
    lokacija:""
  })


    useEffect(() => {
        fetchMagacini();
    }, [])

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setMagacin({ ...magacin, [name]: val });
    }


    async function fetchMagacini() {
        try {
            const response = await MagacinService.getAll();
            console.log(response.data);
            setMagacini(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }


    const sendRequest = async () => {
        if(magacin.naziv === "" || magacin.lokacija === ""){
            alert("Молим те попуни сва поља")
        }else{
            await MagacinService.create(magacin);
            Swal.fire('Uspešno unešenea roba!', 'Očivatanje svih prijemnica...', "success").then(
                ()=>window.location.reload()
            );
        }
    }



    return (
        <>

        <Container>
                <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                    <h3>Магацини</h3>
                    <Form>
                        <Form.Group>
                            <Form.Label>Назив</Form.Label>
                            <Form.Control
                                type="text"
                                name="naziv"
                                value={magacin.naziv}
                                onChange={handleFormInputChange("naziv")}


                            />
                        </Form.Group>



                        <Form.Group>
                            <Form.Label>Локација</Form.Label>
                            <Form.Control
                                type="text"
                                name="lokacija"
                                value={magacin.lokacija}
                                onChange={handleFormInputChange("lokacija")}


                            />
                        </Form.Group>

                    </Form>


                    <Button style={{ marginLeft: "20px", marginTop:"10px" }} onClick={sendRequest}>
                        Креирај магацин
                    </Button>
                </Col>


            </Container>



        

            <hr />
            <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Назив магацина</th>
                        <th>Локација</th>
                    </tr>
                </thead>
                <tbody>
                    {magacini.length === 0 ?
                        <tr>
                            <td className='text-center' colSpan={5}>Не постоји ниједан магацин </td>
                        </tr> :
                        magacini.map((m) => {
                            return (
                                <tr key={m.id}>
                                    <td>{m.naziv}</td>
                                    <td>{m.lokacija}</td>
                                </tr>

                            )
                        })



                    }
                </tbody>
            </Table>


        </>
    )
}

export default MagaciniAdmin