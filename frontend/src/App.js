import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navbar from "./layout/Navbar";
import Home from "./pages/Home"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddCourse from "./users/AddCourse";
import EditCourse from "./users/EditCourse";
function App() {
  return (
    <div className="App">
        <Router>
            <Navbar />

            <Routes>
                <Route exact path="/" element={<Home />} />
                <Route exact path="/AddCourse" element={<AddCourse />} />
                <Route exact path="/EditCourse/:id" element={<EditCourse />} />
            </Routes>
        </Router>
    </div>
  );
}

export default App;
