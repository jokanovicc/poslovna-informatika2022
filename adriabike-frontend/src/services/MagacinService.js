import AxiosClient from "./clients/AxiosClient"

export const MagacinService = {
    getAll,
    getById,
    getMagacinskaKartica,
    getAnalitikaBetweenDates
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

async function getAnalitikaBetweenDates(dateStart, dateEnd){
    return await AxiosClient.get(`http://localhost:8080/api/warehouse/analytics/${dateStart}/${dateEnd}`)
}


