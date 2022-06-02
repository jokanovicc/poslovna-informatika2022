import React, { useEffect, useState } from 'react'
import { ArtikliService } from '../../services/ArtikliService';
import { Table, Button } from 'react-bootstrap';
import { isDisabled } from '@testing-library/user-event/dist/utils';

const SvePrijemnice = () => {

    const [prijemnice, setPrijemnice] = useState([]);

    const [page, setPage] = useState(0);
    const [totalPagesCount, settotalPagesCount] = useState(0);

    useEffect(() => {
        fetchPrijemnice();
    }, [])

    async function fetchPrijemnice() {
        try {
            const response = await ArtikliService.getPrijemnice(page);
            console.log(response.data);
            setPrijemnice(response.data.prijemnice);
            settotalPagesCount(response.data.pagesCount)
        } catch (e) {
            console.error("Error while getting api")
        }
    }


    function nextPage() {
        setPage(page + 1)
        fetchPrijemnice();
    }


    function previousPage() {
        setPage(page - 1)
        fetchPrijemnice();
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



        </>
    )
}

export default SvePrijemnice