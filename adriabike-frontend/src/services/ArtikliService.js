import AxiosClient from "./clients/AxiosClient"

export const ArtikliService = {
    getAll
}


async function getAll(){
    return await AxiosClient.get("http://localhost:8080/api/articles");
}