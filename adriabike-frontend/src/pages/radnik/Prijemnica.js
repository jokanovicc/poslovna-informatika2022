import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Table, Button} from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

const Prijemnica = () => {

    const [prijemnica, setPrijemnica] = useState({
        stavkaPrometnogDokumenta: []
    });
    const { id } = useParams();

    useEffect(() => {
        fetchPrijemnice();
    }, [])

    const columns = [
        {title:"Naziv Artikla",field:"nazivArtikla"},
        {title:"Cena",field:"cena"},
        {title:"Kolicina",field:"kolicina"},
        {title:"Poreska stopa",field:"poreskaStopa"},
        {title:"Vrednost",field:"vrednost"}


    ]

    async function fetchPrijemnice() {
        try {
            const response = await ArtikliService.getPrijemniceById(id);
            console.log(response.data);
            setPrijemnica(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }


    const downloadPdf = () => {
        const doc = new jsPDF();
        let text = 'Prijemnica: ' + prijemnica.broj + "\nDatum: " + prijemnica.datumDokumenta + "\nMagacin: " + prijemnica.magacinNaziv + "\nDobaljena roba:"
        doc.text(text,11,10);
        doc.setFontSize(15);
        doc.autoTable({
            theme:"grid",
            startY: 35,
            columns:columns.map(col => ({...col,dataKey:col.field})),
            body: prijemnica.stavkaPrometnogDokumenta
        })
        doc.save('prijemnica.pdf');

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
                        <th>Набавна цена</th>
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
                                    <td>{x.nabavnaCena}</td>
                                    <td>{x.poreskaStopa} %</td>
                                    <td>{x.vrednost}</td>                                  
                                </tr>

                            )
                        })

                    

                    }
                </tbody>
            </Table>
            <button onClick={downloadPdf}>Generate PDF</button>


        </>
    )
}

export default Prijemnica