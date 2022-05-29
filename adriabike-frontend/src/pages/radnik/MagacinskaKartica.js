import React, { useEffect, useState } from 'react'
import { MagacinService } from '../../services/MagacinService';
import { Button, Dropdown, DropdownButton, Table } from 'react-bootstrap';
import { useParams } from 'react-router-dom';


const MagacinskaKartica = () => {

    const [kartica, setKartica] = useState({});

    const { id } = useParams();


    useEffect(() => {
        fetchMagacinskaKartica();
    }, [id])


    async function fetchMagacinskaKartica() {
        try {
            const response = await MagacinService.getMagacinskaKartica(id);
            console.log(response.data);
            setKartica(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }


    return (
        <>
            <h4 className='text-center'>Картица за артикал: {kartica.nazivArtikla} </h4>
            <h5 className='text-center'>Пословна година: {kartica.poslovnaGodina}</h5>
            <Table>
                <thead className='thead-dark'>
                    <tr>
                        <th>Почетно стање количина</th>
                        <th>Промет улаза количина</th>
                        <th>Промет излаза количина</th>
                        <th>Почетно стање вредност</th>
                        <th>Промет улаза вредност</th>
                        <th>Промет излаза вредност</th>
                        <th>Укупна вредност</th>
                        <th>Просечна цена</th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{kartica.pocetnoStanjeKolicina}</td>
                        <td>{kartica.prometUlazaKolicina}</td>
                        <td>{kartica.prometIzlazaKolicina}</td>
                        <td>{kartica.pocetnoStanjeVrednost}</td>
                        <td>{kartica.prometIzlazaVrednost}</td>
                        <td>{kartica.ukupnaVrednost}</td>
                        <td>{kartica.prosecnaCena}</td>
                    </tr>
                </tbody>
            </Table>
            <Button style={{width:"100%"}}>Креирај пријемницу</Button>
            <Button style={{width:"100%", marginTop:"25px"}}>Прегледај аналитику</Button>



        </>
    )
}

export default MagacinskaKartica;