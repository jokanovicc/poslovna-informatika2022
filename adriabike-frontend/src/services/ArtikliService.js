import AxiosClient from "./clients/AxiosClient"

export const ArtikliService = {
    getAll,
    dobaviRobu,
    getPoreske,
    getPrijemnice,
    getPrijemniceById
}

async function getAll(){
    return await AxiosClient.get("http://localhost:8080/api/articles");
}

async function dobaviRobu(novaRoba){
    return await AxiosClient.post("http://localhost:8080/api/articles", novaRoba);
}

async function getPoreske(){
    return await AxiosClient.get("http://localhost:8080/api/articles/poreske-kategorije");
}

async function getPrijemnice(){
    return await AxiosClient.get("http://localhost:8080/api/prijemnice");
}

async function getPrijemniceById(id){
    return await AxiosClient.get(`http://localhost:8080/api/prijemnice/${id}`);
}