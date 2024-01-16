import './App.css';
import Appbar from './components/Appbar'
import Course from './components/Course'
import AddCourse from './components/AddCourse'
import Home from './components/Home'
import editCourse from './components/editCourse'
import {BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import React from "react";

function App() {
  return (
    <div className="App">
      <Router>
        <Appbar/>
        {/*<Course/>*/}

        <Routes>
      <Route exact path="/AddCourse" element={<AddCourse />} />
            <Route exact path="/" element={<Home />} />
            <Route exact path="/editCourse:id" element={<editCourse />} />
        </Routes>
      </Router>
    </div>

  );
}

export default App;
