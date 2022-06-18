import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import Home from "./pages/Home";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";
import Footer from "./components/Footer";
import HomeCustomer from "./pages/kupac/HomeCustomer";
import Magacini from "./pages/radnik/Magacini";
import MagacinskaKartica from "./pages/radnik/MagacinskaKartica";
import DodavanjeRobe from "./pages/radnik/DodavanjeRobe";
import SvePrijemnice from "./pages/radnik/SvePrijemnice";
import Prijemnica from "./pages/radnik/Prijemnica";
import AnalitikaMagacinskeKartice from "./pages/radnik/AnalitikaMagacinskeKartice";
import Cenovnici from "./pages/radnik/Cenovnici";
import HomeRadnik from "./pages/radnik/HomeRadnik";
import Korpa from "./pages/kupac/Korpa";
import IzlazneFakture from "./pages/radnik/IzlazneFakture";
import Faktura from "./pages/radnik/Faktura";

function App() {
  return (
    <Router>
      <Navbar />
      <Container style={{ marginTop: 35 }}>
        <Routes>
          <Route path="/" element={<Login/>} />
          <Route path="/home-radnik" element={<HomeRadnik/>} />

          <Route path="/home-customer" element={<HomeCustomer/>} />
          <Route path="/warehouse" element={<Magacini/>} />
          <Route path="/card/:id" element={<MagacinskaKartica/>} />
          <Route path="/dobavljanje-robe/:id" element={<DodavanjeRobe/>} />
          <Route path="/prijemnice" element={<SvePrijemnice/>} />

          <Route path="/prijemnica/:id" element={<Prijemnica/>} />

          <Route path="/analytic" element={<AnalitikaMagacinskeKartice/>} />
          <Route path="/cenovnici" element={<Cenovnici/>} />
          <Route path="/cart" element={<Korpa/>}/>
          <Route path="/fakture" element={<IzlazneFakture/>}/>
          <Route path="/fakture/:id" element={<Faktura/>}/>

        </Routes>
      </Container>
      <Footer/>
    </Router>
  );
}

export default App;
