import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import Home from "./pages/Home";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";
import Footer from "./components/Footer";
import HomeCustomer from "./pages/kupac/HomeCustomer";
import Magacini from "./pages/radnik/Magacini";
import MagacinskaKartica from "./pages/radnik/MagacinskaKartica";

function App() {
  return (
    <Router>
      <Navbar />
      <Container style={{ marginTop: 35 }}>
        <Routes>
          <Route path="/" element={<Login/>} />
          <Route path="/home-customer" element={<HomeCustomer/>} />
          <Route path="/warehouse" element={<Magacini/>} />
          <Route path="/card/:id" element={<MagacinskaKartica/>} />


        </Routes>
      </Container>
      <Footer/>
    </Router>
  );
}

export default App;
