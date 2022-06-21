import AxiosClient from "./clients/AxiosClient"

export const ArtikliService = {
    getAll,
    dobaviRobu,
    getPoreske,
    getPrijemnice,
    getPrijemniceById,
    getCenovici,
    getById,
    update
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

async function update(id, body){
    return await AxiosClient.put(`http://localhost:8080/api/articles/${id}/update`, body);
}

async function getById(id){
    return await AxiosClient.get(`http://localhost:8080/api/articles/${id}`);
}

async function getPrijemnice(pageNumber){
    const options = {
        params: {
            page: pageNumber ? pageNumber : 0
        }
    }

    return await AxiosClient.get("http://localhost:8080/api/prijemnice", options);


}

async function getCenovici(pageNumber){
    const options = {
        params: {
            page: pageNumber ? pageNumber : 0
        }
    }

    return await AxiosClient.get("http://localhost:8080/api/articles/cenovnici", options);


}

async function getPrijemniceById(id){
    return await AxiosClient.get(`http://localhost:8080/api/prijemnice/${id}`);
}