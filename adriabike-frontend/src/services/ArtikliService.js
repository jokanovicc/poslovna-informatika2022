import AxiosClient from "./clients/AxiosClient"

export const ArtikliService = {
    getAll,
    dobaviRobu,
    getPoreske,
    getPrijemnice,
    getPrijemniceById,
    getCenovici,
    getById,
    update,
    getListBox,
    poskupi,
    fetchCenovnik,
    korigujCenu
}

async function getAll(pageNumber){
    const options = {
        params: {
            page: pageNumber ? pageNumber : 0
        }
    }
    return await AxiosClient.get("http://localhost:8080/api/articles", options);
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

async function poskupi(body){
    return await AxiosClient.post("http://localhost:8080/api/articles/poskupljenje", body);
}

async function korigujCenu(body){
    return await AxiosClient.post("http://localhost:8080/api/articles/korigovanje-cene", body);
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

async function getListBox(){
    return await AxiosClient.get("http://localhost:8080/api/articles/listbox");
}

async function fetchCenovnik(id){
    return await AxiosClient.get(`http://localhost:8080/api/articles/${id}/cenovnik`);
}