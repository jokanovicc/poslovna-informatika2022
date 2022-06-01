import React, { useEffect, useState } from 'react'
import { MagacinService } from '../../services/MagacinService';
import { Dropdown, DropdownButton, Table, Button} from 'react-bootstrap';

const Magacini = () => {

    const [magacini, setMagacini] = useState([]);
    const [kartice, setKartice] = useState([]);



    useEffect(() => {
        fetchMagacini();
    }, [])


    async function fetchMagacini() {
        try {
            const response = await MagacinService.getAll();
            console.log(response.data);
            setMagacini(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }

    async function fetchKartice(id) {
        try {
            const response = await MagacinService.getById(id)
            setKartice(response.data);
            console.log(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }

    return (
        <>
            <h2 className='text-center'>Магацинске картице</h2>
            
            <hr />


            <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Назив артикла</th>
                        <th>Пословна година</th>
                        <th>Локација магацина</th>
                        <th>Укупна вредност</th>
                        <th>Детаљи</th>
                    </tr>
                </thead>
                <tbody>
                    {kartice.length === 0 ?
                        <tr>
                            <td className='text-center' colSpan={5}>          <Dropdown>
                                <Dropdown.Toggle variant="success" id="dropdown-basic">
                                    Избор магацина
                                </Dropdown.Toggle>

                                <Dropdown.Menu>
                                    {
                                        magacini.map((magacin) => {
                                            return (
                                                <Dropdown.Item onClick={() => fetchKartice(magacin.id)}>{magacin.naziv} // {magacin.lokacija}</Dropdown.Item>
                                            )
                                        })
                                    }
                                </Dropdown.Menu>
                            </Dropdown></td>
                        </tr> :
                        kartice.map((k) => {
                            return (
                                <tr key={k.id}>
                                    <td>{k.nazivArtikla}</td>
                                    <td>{k.poslovnaGodina}</td>
                                    <td>{k.magacinMesto}</td>
                                    <td>{k.ukupnaVrednost}</td>
                                    <td>
                                        <a href={/card/ + k.id}>Детаљи</a>
                                    </td>    
                                                                
                                </tr>

                            )
                        })

                    

                    }
                </tbody>
            </Table>

            {kartice.length !== 0 ? <Button style={{width:"100%"}}>Наручи нову робу!</Button> : <br/>};
        </>
    )
}

export default Magacini