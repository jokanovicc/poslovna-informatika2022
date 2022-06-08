import AxiosClient from "./clients/AxiosClient"

export const KupovinaService = {
    addToKorpa,
    getKorpa,
    removeStavka,
    createFaktura
};

async function addToKorpa(korpaItem){
    return await AxiosClient.post("http://localhost:8080/api/cart", korpaItem);
}

async function getKorpa(){
    return await AxiosClient.get("http://localhost:8080/api/cart");
}

async function removeStavka(stavkaId){
    return await AxiosClient.delete(`http://localhost:8080/api/cart/${stavkaId}`);
}

async function createFaktura(){
    return await AxiosClient.post("http://localhost:8080/api/cart/create-faktura")
}