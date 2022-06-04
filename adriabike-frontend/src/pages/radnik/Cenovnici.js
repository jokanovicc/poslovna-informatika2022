import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Table, Button } from 'react-bootstrap';

const Cenovnici = () => {

    const [dtoCenovnika, setDtoCenovnika] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPagesCount, settotalPagesCount] = useState(0);




    useEffect(() => {
        fetchCenovnici();
    },[])


    async function fetchCenovnici() {
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
        setPage(page + 1)
        fetchCenovnici();
    }


    function previousPage() {
        setPage(page - 1)
        fetchCenovnici();
    }



    return (
        <>
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
                <span style={{ marginRight: "20px" }} className='margin-md'>Страница {page + 1} од {totalPagesCount}</span>
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
        </>
    )
}

export default Cenovnici