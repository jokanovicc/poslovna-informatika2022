import React, { useEffect, useState } from 'react'
import { FakturaService } from '../../services/FaktureService';
import { useParams } from 'react-router-dom';
import {  Table, Button } from 'react-bootstrap';
import { UserService } from '../../services/UserService';

const Faktura = () => {
    

    const [faktura, setFaktura] = useState({stavkaFakture:[]});
    const [user, setUser] = useState({});

    const { id } = useParams();

    useEffect(() => {
        fetchFaktura();
        fetchKupac();

    }, [])


    async function fetchFaktura() {
        try {
            const response = await FakturaService.getFaktura(id)
            console.log(response.data);
            setFaktura(response.data);
        } catch (e) {
            console.error("Error while getting api" + e)
        }
    }

    async function fetchKupac() {
        try {
            const response = await UserService.getUser(faktura.kupac);
            console.log(response.data);
            setUser(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }

    async function zavrsiFakturu(){
        try {
            const response = await FakturaService.endFaktura(id);
            console.log(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }




    return (
        <>
            <div class="card">
                <h5 class="card-header">Фактура бр. {faktura.brojFakture}</h5>
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <h5 class="card-title">Статус фактуре: {faktura.statusFakture}</h5>
                            <div class="card-text">
                                <ul>
                                    <li>Укупан износ: <b>{faktura.ukupanIznos}.00 din</b></li>
                                    <li>Датум издавања: <b>{faktura.datumIzdavanja}</b></li>
                                    <li>Укупна основица: <b>{faktura.ukupnaOsnovica}</b></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <h5 class="card-title">Купац: {user.ime} {user.prezime}</h5>
                            <div class="card-text">
                                <ul>
                                    <li>Адреса: <b>{user.adresa}</b></li>
                                    <li>Број телефона: <b>{user.brojTelefona}</b></li>
                                    <li>Мејл: <b>{user.email}</b></li>
                                </ul>
                            </div>
                        </div>

                    </div>

                </div>
                <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Назив артикал</th>
                        <th>Количина</th>
                        <th>Јединична цена</th>
                        <th>Проценат ПДВ</th>
                        <th>Износ ПДВ</th>
                        <th>Основица</th>
                        <th>Рабат</th>
                        <th>Укупно</th>
                    </tr>
                </thead>
                <tbody>
                    {faktura.stavkaFakture.length === 0 ?
                        <tr>
                            <td className='text-center' colSpan={5}> Нема фактура!</td>
                        </tr> :
                        faktura.stavkaFakture.map((f) => {
                            return (
                                <tr key={f.id}>
                                    <td>{f.nazivArtikla}</td>
                                    <td>{f.kolicina}</td>
                                    <td>{f.jedinicnaCena}</td>
                                    <td>{f.procenatPDV}</td>
                                    <td>{f.iznosPDV}</td>
                                    <td>{f.osnovica}</td>
                                    <td>{f.rabat}</td>
                                    <td>{f.ukupno}</td>    
                                                                
                                </tr>

                            )
                        })

                    

                    }
                </tbody>
            </Table>
            <Button style={{ margin: "10px" }} onClick={zavrsiFakturu} >
                        Креирај пријемницу
            </Button>
            </div>

        </>
    )
}

export default Faktura