import jwtDecode from "jwt-decode";
import AxiosClient from "./clients/AxiosClient";

export const UserService = {
    getUserInfo,
    updateProfile

};

async function getUserInfo() {
  return await AxiosClient.get("http://localhost:8080/api/auth/user-info");
}

async function updateProfile(body) {
  return await AxiosClient.post("http://localhost:8080/api/auth/update", body);
}


