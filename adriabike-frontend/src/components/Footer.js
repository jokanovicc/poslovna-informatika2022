import {Col, Container, Navbar} from "react-bootstrap";


const Footer = () => {

    let fullYear = new Date().getFullYear();

    return(

    <Navbar fixed="bottom" variant="dark">
        <Container>
            <Col lg={12} className="text-center text-muted">
                <div>{fullYear-1}-{fullYear} Adria Bike Store</div>
            </Col>
        </Container>
    </Navbar>);



}
export default Footer;