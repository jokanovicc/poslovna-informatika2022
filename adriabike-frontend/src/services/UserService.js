import jwtDecode from "jwt-decode";
import AxiosClient from "./clients/AxiosClient";

export const UserService = {
    getUser

};

async function getUser(id) {
  return await AxiosClient.get(`http://localhost:8080/api/user/${id}`)
}


