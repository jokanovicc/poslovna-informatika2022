import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Table, Button} from 'react-bootstrap';
import { useParams } from 'react-router-dom';

const Prijemnica = () => {

    const [prijemnica, setPrijemnica] = useState({
        stavkaPrometnogDokumenta: []
    });
    const { id } = useParams();

    useEffect(() => {
        fetchPrijemnice();
    }, [])

    async function fetchPrijemnice() {
        try {
            const response = await ArtikliService.getPrijemniceById(id);
            console.log(response.data);
            setPrijemnica(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }
    return (
        <>
            <h2 className='text-center'>Пријемница бр. {prijemnica.broj}</h2>
            <p>
                Пријемница је креирана и укњижена дана <b>{prijemnica.datumDokumenta}</b>, а роба је примљена у магацин <b>{prijemnica.magacinNaziv}</b>. У наставку је приказана сва роба која је пристигла.
            </p>
            <hr />


            <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Назив артикла</th>
                        <th>Количина</th>
                        <th>Пореско стопа</th>
                        <th>Вредност</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        prijemnica.stavkaPrometnogDokumenta.map((x) => {
                            return (
                                <tr key={x.id}>
                                    <td>{x.nazivArtikla}</td>
                                    <td>{x.kolicina}</td>
                                    <td>{x.poreskaStopa} %</td>
                                    <td>{x.vrednost}</td>                                  
                                </tr>

                            )
                        })

                    

                    }
                </tbody>
            </Table>
        </>
    )
}

export default Prijemnica