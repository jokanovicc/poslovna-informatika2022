import React, { useEffect, useState } from 'react'
import { FakturaService } from '../../services/FaktureService';
import { Dropdown, DropdownButton, Table, Button} from 'react-bootstrap';

const IzlazneFakture = () => {

    const [fakture, setFakture] = useState([]);


    useEffect(() => {
        getFaktureNepotvrdjene();
    }, [])
    



    async function getFaktureNepotvrdjene(){
        try {
            const response = await FakturaService.getNepotvrdjene();
            console.log(response.data);
            setFakture(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }

    }







    return (
        <>
            <h2 className='text-center'>Излазне фактуре</h2>
            
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
                                        <a href={/fakture/ + f.id}>Приказ фактуре</a>
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