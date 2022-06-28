import AxiosClient from "./clients/AxiosClient"

export const MagacinService = {
    getAll,
    getById,
    getMagacinskaKartica,
    getAnalitikaBetweenDates,
    create,
    getAnalitikaBetweenDatesKartica
}

async function getAll(){
    return await AxiosClient.get("http://localhost:8080/api/warehouse");
}

async function getById(id){
    return await AxiosClient.get(`http://localhost:8080/api/warehouse/${id}`);

}

async function getMagacinskaKartica(id){
    return await AxiosClient.get(`http://localhost:8080/api/warehouse/${id}/kartica`);
}

async function getAnalitikaBetweenDates(body){
    return await AxiosClient.post(`http://localhost:8080/api/warehouse/analytics`, body)
}

async function getAnalitikaBetweenDatesKartica(dateStart, dateEnd, kartica){
    return await AxiosClient.get(`http://localhost:8080/api/warehouse/${kartica}/analytics/${dateStart}/${dateEnd}`)
}

async function create(body){
    return await AxiosClient.post("http://localhost:8080/api/warehouse", body)
}


