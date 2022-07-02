import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Table, Button, Form, Modal } from 'react-bootstrap';

const Cenovnici = () => {

    const [dtoCenovnika, setDtoCenovnika] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPagesCount, settotalPagesCount] = useState(0);

    const [noviCenovnik, setNoviCenovnik] = useState({
        cenovnikId: "",
        poskupljenje: true,
        procenat: 0,
        datum: ""
    })

    const [show, setShow] = useState(false);




    useEffect(() => {
        fetchCenovnici();
    }, [])


    const handleClose = () => setShow(false);
    const handleShow = () => {
        setShow(true);
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setNoviCenovnik({ ...noviCenovnik, [name]: val });
    };


    async function fetchCenovnici(page) {
        try {
            const response = await ArtikliService.getCenovici(page);
            console.log(response.data);
            setDtoCenovnika(response.data.cenovnici);
            settotalPagesCount(response.data.pagesCount)
        } catch (e) {
            console.error("Error while getting api")
        }

    }



    function nextPage() {
        fetchCenovnici(page+1);
        setPage(page+1)
    }


    function previousPage() {
        fetchCenovnici(page - 1);
        setPage(page - 1)

    }

    
    async function sendRequest(){

        try {
            const response =  await ArtikliService.poskupi(noviCenovnik)
            window.location.assign(`/korigovanje-cenovnika/${response.data.id}`)
        } catch (e) {
            console.error("Error while getting api")
        }

    }



    return (
        <>
            <Button variant="success" onClick={() => handleShow()}>
                Додај нову цену
            </Button>
            {dtoCenovnika.length === 0 ?
                <p>Prazno</p> :
                dtoCenovnika.map((p) => {
                    return (
                        <>
                            <p>Cenovnik važi od dana {p.stupioNaSnagu}</p>
                            <Table bordered striped>
                                <thead className='thead-dark'>
                                    <tr>
                                        <th>Назив артикла</th>
                                        <th>Цена</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {p.stavkeCenovnikaDTO.length === 0 ?
                                        <tr>
                                            <td className='text-center' colSpan={5}> x</td>
                                        </tr> :
                                        p.stavkeCenovnikaDTO.map((x) => {
                                            return (
                                                <tr key={x.id}>
                                                    <td>{x.nazivArtikla}</td>
                                                    <td>{x.cena}</td>

                                                </tr>

                                            )
                                        })


                                    }
                                </tbody>
                            </Table>
                        </>

                    )
                })



            }
            <div>
                <span style={{marginRight:"20px"}} className='margin-md'>Страница {page + 1} од {totalPagesCount}</span>
                <button className='margin-sm' onClick={previousPage} disabled={page <= 0}>
                    Претходна
                </button>
                <button className='margin-sm'>
                    {page + 1}
                </button>

                <button className='margin-sm' disabled={page + 1 >= totalPagesCount} onClick={nextPage}>
                    Следећа
                </button>


             
            </div>


            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form>

                        <Form.Group>
                            <Form.Label>Степен поскупљења</Form.Label>
                            <Form.Control
                                type="number"
                                name="procenat"
                                value={noviCenovnik.procenat}
                                onChange={handleFormInputChange("procenat")}


                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Датум почетка</Form.Label>
                            <Form.Control
                                type="date"
                                name="datum"
                                value={noviCenovnik.datum}
                                onChange={handleFormInputChange("datum")}


                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Поскупљење</Form.Label>
                            <Form.Select value={noviCenovnik.poskupljenje} onChange={handleFormInputChange("poskupljenje")}>
                                <option value="">Начин</option>
                                <option value="true">Поскупљење</option>
                                <option value="false">Појефтињење</option>
                            </Form.Select>
                        </Form.Group>

                    </Form>
                </Modal.Body>


                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => sendRequest()}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default Cenovnici