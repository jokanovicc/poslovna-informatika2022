import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import Home from "./pages/Home";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";

function App() {
  return (
    <Router>
      <Navbar />
      <Container style={{ marginTop: 25 }}>
        <Routes>
          <Route path="/" element={<Login/>} />
        </Routes>
      </Container>
    </Router>
  );
}

export default App;
