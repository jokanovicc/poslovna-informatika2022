import React from "react";
import { Button, Nav, Navbar,NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import { TokenService } from "../services/TokenService";
import { AuthenticationService } from "../services/AuthenticationService.js";

const NavBar = () => {
  const role = AuthenticationService.getRole();
  return (
    <Navbar style={{
      padding: "20px", backgroundColor: "#2A2F4D", boxShadow: " 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 5px 0 rgba(0,0,0,0.20)"
    }}
      className="navbar navbar-dark"
    >
      <Navbar.Brand as={Link} to="/" style={{ position: "absolute", left: "50%", transform: "translateX(-50%)", fontFamily: "'Montserrat'" }}>
        A D R I A
      </Navbar.Brand>
      <Nav className="mr-auto">
        {role == "ROLE_KUPAC" &&
          <>
            <Nav.Link onClick={() => window.location.assign("/cart")}>
              Корпа
            </Nav.Link>
            <Nav.Link onClick={() => window.location.assign("/user-faktura")}>
              Наруџбенице
            </Nav.Link>
          </>

        }
      </Nav>

      <Nav className="ms-auto">

        {TokenService.getToken() ? (
          <>
                <NavDropdown title="Профил" id="navbarScrollingDropdown">
                <NavDropdown.Item onClick={() => window.location.assign("/profile")}>Приказ профила</NavDropdown.Item>
              </NavDropdown>
        <Button style={{marginLeft:"2vw"}} onClick={() => AuthenticationService.logout()}>Log out</Button>
        </>
        ) : (
          <Nav.Link as={Link} to="/">
          </Nav.Link>
        )}
      </Nav>

    </Navbar>
  );
};

export default NavBar;