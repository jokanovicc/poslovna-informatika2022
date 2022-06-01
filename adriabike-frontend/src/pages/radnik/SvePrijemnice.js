import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Table, Button} from 'react-bootstrap';

const SvePrijemnice = () => {

    const [prijemnice, setPrijemnice] = useState([]);
    
    useEffect(() => {
        fetchPrijemnice();
    }, [])

    async function fetchPrijemnice() {
        try {
            const response = await ArtikliService.getPrijemnice();
            console.log(response.data);
            setPrijemnice(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }
    return (
        <>
            <h2 className='text-center'>Досадашње пријемнице</h2>
            
            <hr />


            <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Број пријемнице</th>
                        <th>Датум документа</th>
                        <th>Назив магацина</th>
                    </tr>
                </thead>
                <tbody>
                    {prijemnice.length === 0 ?
                        <tr>
                            <td className='text-center' colSpan={5}> x</td>
                        </tr> :
                        prijemnice.map((p) => {
                            return (
                                <tr key={p.id}>
                                    <td>{p.broj}</td>
                                    <td>{p.datumDokumenta}</td>
                                    <td>{p.magacinNaziv}</td>
                                    <td>
                                    <a href={/prijemnica/ + p.id}>Детаљи</a>
                                        </td>    
                                                                
                                </tr>

                            )
                        })

                    

                    }
                </tbody>
            </Table>
        </>
    )
}

export default SvePrijemnice