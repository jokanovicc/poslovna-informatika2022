import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
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
import FaktureKupca from "./pages/kupac/FaktureKupca";
import Registracija from "./pages/kupac/Registracija";
import Profile from "./pages/Profile";
import UpdateArtikal from "./pages/radnik/UpdateArtikal";
import MagaciniAdmin from "./pages/admin/MagaciniAdmin";
import PoreskeKategorije from "./pages/admin/PoreskeKategorije";
import AnalitikaKartice from "./pages/radnik/AnalitikaKartice";
import KorigovanjeCenovnika from "./pages/radnik/KorigovanjeCenovnika";
import { PrivateRoute } from "./components/PrivateRoute";
import AdminGlavna from "./pages/admin/AdminGlavna";

function App() {
  return (
    <Router>
      <Navbar />
      <Container style={{ marginTop: 35 }}>
        <Switch>
          <Route exact path="/">
            <Redirect to="/login" />
          </Route>
          <Route path="/login" component={Login} />
          <PrivateRoute exact path="/home-radnik" component={HomeRadnik} roles={["ROLE_PRODAVAC"]} />

          <PrivateRoute path="/home-customer" component={HomeCustomer} roles={["ROLE_KUPAC"]}/>
          <PrivateRoute path="/warehouse" component={Magacini} roles={["ROLE_PRODAVAC"]}/>
          <PrivateRoute path="/card/:id" component={MagacinskaKartica} roles={["ROLE_PRODAVAC"]}/>
          <PrivateRoute path="/dobavljanje-robe/:id" component={DodavanjeRobe} roles={["ROLE_PRODAVAC"]} />
          <PrivateRoute path="/prijemnice" component={SvePrijemnice} roles={["ROLE_PRODAVAC"]}/>

          <PrivateRoute path="/prijemnica/:id" component={Prijemnica} roles={["ROLE_PRODAVAC"]}/>

          <PrivateRoute path="/analytic" component={AnalitikaMagacinskeKartice} roles={["ROLE_PRODAVAC"]}/>
          <PrivateRoute path="/cenovnici" component={Cenovnici} roles={["ROLE_PRODAVAC"]}/>
          <PrivateRoute path="/cart" component={Korpa} roles={["ROLE_KUPAC"]}/>
          <PrivateRoute path="/fakture" component={IzlazneFakture} roles={["ROLE_PRODAVAC", "ROLE_KUPAC"]}/>
          <Route path="/faktura/:id" component={Faktura}/>
          <PrivateRoute path="/user-faktura" component={FaktureKupca} roles={["ROLE_PRODAVAC", "ROLE_KUPAC"]}/>

          <Route path="/register" component={Registracija}/>
          <PrivateRoute path="/profile" component={Profile} roles={["ROLE_PRODAVAC", "ROLE_KUPAC", "ROLE_ADMIN"]}/>
          <PrivateRoute path="/update-artikal/:id" component={UpdateArtikal} roles={["ROLE_PRODAVAC"]}/>
          <PrivateRoute path="/admin-magacini" component={MagaciniAdmin} roles={["ROLE_ADMIN"]}/>
          <PrivateRoute path="/poreske-kategorije" component={PoreskeKategorije} roles={["ROLE_ADMIN"]}/>
          <PrivateRoute path="/korigovanje-cenovnika/:id" component={KorigovanjeCenovnika} roles={["ROLE_PRODAVAC"]}/>
          <PrivateRoute path="/admin-glavna" component={AdminGlavna} roles={["ROLE_ADMIN"]}/>

        </Switch>
      </Container>
      <Footer />
    </Router>
  );
}

export default App;
