import React from "react";
import { Button, Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
import { TokenService } from "../services/TokenService";
import { AuthenticationService } from "../services/AuthenticationService.js";

const NavBar = () => {
  const role = AuthenticationService.getRole();
  return (
    <Navbar style={{padding:"20px"}}
    className="navbar navbar-dark bg-primary"
    >
      <Navbar.Brand as={Link} to="/">
        ADRIA bikes
      </Navbar.Brand>
      <Nav className="mr-auto">
        <Nav.Link as={Link} to="/tasks">
          //
        </Nav.Link>
      </Nav>

      <Nav className="mr-auto">

      {TokenService.getToken() ? (
        <Button onClick={() => AuthenticationService.logout()}>Log out</Button>
      ) : (
        <Nav.Link as={Link} to="/">
          Log in
        </Nav.Link>
      )}
      </Nav>

    </Navbar>
  );
};

export default NavBar;