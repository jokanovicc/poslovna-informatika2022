import React, { useEffect, useState } from 'react'
import { FakturaService } from '../../services/FaktureService';
import { Dropdown, DropdownButton, Table, Button} from 'react-bootstrap';

const IzlazneFakture = () => {

    const [fakture, setFakture] = useState([]);
    const[status,setStatus] = useState("nepotvrdjena");
    const options = ["nepotvrdjena", "potvrdjena", "odbijena"];


    useEffect(() => {
        getFaktureNepotvrdjene(status);
    }, [])
    



    async function getFaktureNepotvrdjene(status){
        try {
            const response = await FakturaService.getNepotvrdjene(status);
            console.log(response.data);
            setFakture(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }

    }

    async function getValue(status){
        setStatus(status);
        await getFaktureNepotvrdjene(status);

    }



    return (
        <>
            <h2 className='text-center'>Излазне фактуре</h2>

            <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Избор статуса фактуре : {status}
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    {
                        options.map((o) => {
                            return (
                                <Dropdown.Item onClick={() => getValue(o)}>{o}</Dropdown.Item>
                            )
                        })
                    }
                </Dropdown.Menu>
            </Dropdown>
            
            <hr />

            <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Број фактуре</th>
                        <th>Датум издавања</th>
                        <th>Датум валуте</th>
                        <th>Укупан износ</th>
                        <th>Укупна вредност</th>
                    </tr>
                </thead>
                <tbody>
                    {fakture.length === 0 ?
                        <tr>
                            <td className='text-center' colSpan={5}> Нема фактура!</td>
                        </tr> :
                        fakture.map((f) => {
                            return (
                                <tr key={f.id}>
                                    <td>{f.brojFakture}</td>
                                    <td>{f.datumIzdavanja}</td>
                                    <td>{f.datumValute}</td>
                                    <td>{f.ukupnaOsnovica}</td>
                                    <td>
                                        <a href={/faktura/ + f.id}>Приказ фактуре</a>
                                    </td>
                                    <td></td>    
                                                                
                                </tr>

                            )
                        })

                    

                    }
                </tbody>
            </Table>

        </>
    )
}


export default IzlazneFakture