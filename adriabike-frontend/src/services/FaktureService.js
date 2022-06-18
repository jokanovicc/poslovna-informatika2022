import AxiosClient from "./clients/AxiosClient"

export const FakturaService = {
    getNepotvrdjene,
    getFaktura,
    endFaktura
};


async function getNepotvrdjene() {
    return await AxiosClient.get("http://localhost:8080/api/faktura");
}

async function getFaktura(id){
    return await AxiosClient.get(`http://localhost:8080/api/faktura/${id}`)
}

async function endFaktura(id){
    return await AxiosClient.post(`http://localhost:8080/api/faktura/${id}`)
}

