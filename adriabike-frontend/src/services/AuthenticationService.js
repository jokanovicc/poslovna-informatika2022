import AxiosClient from "./clients/AxiosClient";
import { TokenService } from "./TokenService";
import { Redirect, Route } from "react-router-dom";

export const AuthenticationService = {
  login,
  logout,
  getRole,
  register,
  redirect
};

async function login(userCredentials) {
  try {
    const response = await AxiosClient.post(
      "http://localhost:8080/api/auth/login",
      userCredentials
    );
    const decoded_token = TokenService.decodeToken(response.data);
    if (decoded_token) {
      TokenService.setToken(response.data);
      if (getRole() == "ROLE_KUPAC") {
        window.location.assign("/home-customer")
      }
      else if (getRole() == "ROLE_PRODAVAC"){
        window.location.assign("/home-radnik")
      }
    
      else {
        window.location.assign("/admin-glavna")
      }
    } else {
      console.error("Invalid token");
    }
  } catch (error) {
    console.error(error);
  }
}

async function register(body) {
  await AxiosClient.post(
    "http://localhost:8080/api/auth/register",
    body
  );
}

function logout() {
  TokenService.removeToken();
  window.location.assign("/");
}

function redirect(role){
  if(role == "ROLE_PRODAVAC"){
    <Redirect to={{ pathname: "/home-radnik" }}/>
  }
  
}

function getRole() {
  const token = TokenService.getToken();
  const decoded_token = token ? TokenService.decodeToken(token) : null;
  if (decoded_token) {
    return decoded_token.role.authority;
  } else {
    return null;
  }
}
