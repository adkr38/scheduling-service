import React from "react";
import "./index.css";
import Hero from "./components/Hero";
import Login from "./components/Login";
import Register from "./components/Register";
import Catalogue from "./components/Catalogue";
import Appointment from "./components/Appointment";
import { ThemeContext } from "./context/ThemeContext";
import { Routes, Route, HashRouter, Outlet, Navigate } from "react-router-dom";

function App() {
  const lightContainer: string =
    "min-h-screen w-full bg-gradient-to-tr from-stone-50/10 to-stone-100/10 p-12";

  const darkContainer: string =
    "min-h-screen w-full bg-gradient-to-tr from-stone-600 to-stone-900 p-12";

  const { theme } = React.useContext(ThemeContext);

  return (
    <HashRouter>
      <div className={theme == "light" ? lightContainer : darkContainer}>
        <Routes>
          <Route path="/" element={<Hero />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/catalogue" element={<Catalogue />} />
          <Route path="/appointment" element={<Appointment />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>

        <Outlet />
      </div>
    </HashRouter>
  );
}

export default App;
