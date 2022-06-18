import React,{useEffect, useState} from 'react'
import { KupovinaService } from '../../services/KupovinaService';
import { Button, Table} from 'react-bootstrap';
import Swal from 'sweetalert2';


const Korpa = () => {

    const [stavke, setStavke] = useState([]);
    const[sum,setSum] = useState(0);
    
    useEffect(() => {
        fetchCenovnici();
    },[]);



    async function fetchCenovnici() {
        try {
            const response = await KupovinaService.getKorpa();
            setStavke(response.data.stavkaKorpeResponseDTOs);

        } catch (e) {
            console.error("Error while getting api")
        }

    }


    function removeFromCart(id){
        setStavke(stavke.filter(s => s.id != id))
        KupovinaService.removeStavka(id);
        console.log(sum)
    }

    async function zavrsiKupovinu(){
        await KupovinaService.createFaktura();
        
        const sum = stavke.reduce((partialSum, a) => partialSum.cena + a, 0);
        Swal.fire('Успешно',`Успешно потврђена куповина\nСума: ${sum}`,'success');
        window.location.assign("/home-customer")
    }


    



    return (
        <>
        <h2 className='text-center'>Ваша корпа</h2>

        <hr />


        <Table bordered striped>
            <thead className='thead-dark'>
                <tr>
                    <th>Назив артикла</th>
                    <th>Количина</th>
                    <th>Цена</th>
                    <th>Уклони</th>

                </tr>
            </thead>
            <tbody>
                {stavke.length === 0 ?
                    <tr>
                        <td className='text-center' colSpan={5}> x</td>
                    </tr> :
                    stavke.map((s) => {
                        return (
                            <tr key={s.id}>
                                <td>{s.nazivArtikla}</td>
                                <td>{s.kolicina}</td>
                                <td>{s.cena}</td>
                                <td><Button onClick={() => removeFromCart(s.id)}>🗑️</Button></td>
                            </tr>

                        )
                    })



                }
            </tbody>
        </Table>
        <Button onClick={()=> zavrsiKupovinu()}>Заврши куповину</Button>



    </>
    )
}

export default Korpa