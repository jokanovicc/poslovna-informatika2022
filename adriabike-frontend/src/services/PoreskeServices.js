import AxiosClient from "./clients/AxiosClient"

export const PoreskeService = {
    getAll,
    getById,
    create,
    createStopa
}


async function getAll(){
    return await AxiosClient.get("http://localhost:8080/api/tax")
}

async function getById(id){
    return await AxiosClient.get("http://localhost:8080/api/tax/id")
}

async function create(body){
    return await AxiosClient.post("http://localhost:8080/api/tax", body)
}

async function createStopa(body, id){
    return await AxiosClient.post(`http://localhost:8080/api/tax/${id}/stopa`, body)
}