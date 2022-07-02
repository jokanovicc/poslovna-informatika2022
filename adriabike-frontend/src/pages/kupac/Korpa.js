import React,{useEffect, useState} from 'react'
import { KupovinaService } from '../../services/KupovinaService';
import { Button, Table} from 'react-bootstrap';
import Swal from 'sweetalert2';


const Korpa = () => {

    const [stavke, setStavke] = useState([]);
    const{ukupnaCena} = 0;
    
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
    }

    async function zavrsiKupovinu(){
        try {
            await KupovinaService.createFaktura();
            Swal.fire('Успешно',`Успешно потврђена поруџбеница!`,'success').then(function() {
                window.location.assign("/user-faktura")
            });

        } catch (e) {
            console.error("Error while getting api")
        }
    
        
    }


    



    return (
        <>
        <h2 className='text-center'>Ваша корпа</h2>

        <hr />


        <Table style={{ textAlign: "center", marginTop:"2vh", fontSize:"20px" }} bordered striped>
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
                        <td className='text-center' colSpan={5}>Празна!</td>
                    </tr> :
                    stavke.map((s) => {
                        return (
                            <tr key={s.id}>
                                <td>{s.nazivArtikla}</td>
                                <td>{s.kolicina}</td>
                                <td>{s.cena}</td>
                                <td><Button onClick={() => removeFromCart(s.id)} variant="outline-danger">Избаци</Button></td>
                            </tr>

                        )
                    })



                }
            </tbody>
        </Table>
        {
            stavke.length > 0 &&
            <Button style={{width:"100%", width: "100%", backgroundColor: "#2A2F4D", borderColor: "#2A2F4D"}} onClick={() => zavrsiKupovinu()}>Заврши куповину</Button>

        }



    </>
    )
}

export default Korpa