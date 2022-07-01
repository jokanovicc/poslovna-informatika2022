import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom';
import { ArtikliService } from '../../services/ArtikliService';
import { Table, Button, Form, Modal } from 'react-bootstrap';


const KorigovanjeCenovnika = () => {

    const [cenovnik, setCenovnik] = useState({
        stavkeCenovnikaDTO: []
    })

    const [stavkeCenovnikaDTO, setStavkeCenovnikaDTO] = useState([]);

    const [korigovanje, setKorigovanje] = useState({
        novaCena: 0,
        stavkaId: 0
    })
    const [show, setShow] = useState(false);

    const { id } = useParams();

    const [idStavka, setIdStavke] = useState();

    useEffect(() => {
        fetchCenovnik()

    }, [])

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setKorigovanje({ ...korigovanje, [name]: val });
    }


    async function fetchCenovnik() {
        try {
            const response = await ArtikliService.fetchCenovnik(id);
            setCenovnik(response.data);
            setStavkeCenovnikaDTO(response.data.stavkeCenovnikaDTO)
            console.log(stavkeCenovnikaDTO);
        } catch (e) {
            console.error("Error while getting api")
        }

    }

    const handleShow = (id,cena) => {
        korigovanje.novaCena = cena;
        setIdStavke(id);
        setShow(true);
    }
    const handleClose = () => setShow(false);


    async function unos() {
        korigovanje.stavkaId = idStavka;
        if(korigovanje.novaCena < 1000 || korigovanje.novaCena == null){
            alert("Погрешан унос")
        }else{
            await ArtikliService.korigujCenu(korigovanje);
            handleClose();
            fetchCenovnik();
        }





    }

    return (
        <>
            <p>Ценовник ће важити од датума <b>{cenovnik.stupioNaSnagu}</b></p>
            <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Назив артикла</th>
                        <th>Цена</th>
                        <th>Измена цене</th>
                    </tr>
                </thead>
                <tbody>
                    {cenovnik.stavkeCenovnikaDTO.length === 0 ?
                        <tr>
                            <td className='text-center' colSpan={5}> x</td>
                        </tr> :
                        cenovnik.stavkeCenovnikaDTO.map((x) => {
                            return (
                                <tr key={x.id}>
                                    <td>{x.nazivArtikla}</td>
                                    <td>{x.cena}</td>
                                    <td style={{width:"13%"}}>
                                        <Button variant="primary" onClick={() => handleShow(x.id, x.cena)}>
                                            Коригуј
                                        </Button>
                                    </td>

                                </tr>

                            )
                        })


                    }
                </tbody>
            </Table>


            <Modal show={show} onHide={handleClose}>
                <Modal.Body>

                    <Form>
                        <Form.Group>
                            <Form.Label>Молимо вас унесите кориговану цену</Form.Label>
                            <Form.Control
                                type="text"
                                name="novaCena"
                                value={korigovanje.novaCena}
                                onChange={handleFormInputChange("novaCena")}


                            />
                        </Form.Group>

                    </Form>
                </Modal.Body>


                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Одустани
                    </Button>
                    <Button variant="primary" onClick={() => unos()}>
                        Унеси
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default KorigovanjeCenovnika