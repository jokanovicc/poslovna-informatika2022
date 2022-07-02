import AxiosClient from "./clients/AxiosClient"

export const FakturaService = {
    getNepotvrdjene,
    getFaktura,
    endFaktura,
    fakturaByUser,
    sendPoruka
};


async function getNepotvrdjene(status) {
    const options = {
        params: {
            status: status ? status : "nepotvrdjena"
        }
    }
    return await AxiosClient.get("http://localhost:8080/api/faktura", options);
}

async function getFaktura(id){
    return await AxiosClient.get(`http://localhost:8080/api/faktura/${id}`)
}

async function fakturaByUser(){
    return await AxiosClient.get(`http://localhost:8080/api/faktura/user`)
}

async function endFaktura(id){
    return await AxiosClient.post(`http://localhost:8080/api/faktura/${id}`)
}

async function sendPoruka(poruka){
    return await AxiosClient.post("http://localhost:8080/api/faktura/send-poruka", poruka);

}

