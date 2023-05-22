import React from "react";
import "./index.css";
import Hero from "./components/Hero";
import Login from "./components/Login";
import Register from "./components/Register";
import { ThemeContext } from "./context/ThemeContext";
import { Routes, Route, HashRouter, Outlet, Navigate } from "react-router-dom";

function App() {
  const lightContainer: string =
    "h-screen w-screen bg-gradient-to-tr from-stone-50/10 to-stone-100/10 p-12";

  const darkContainer: string =
    "h-screen w-screen bg-gradient-to-tr from-stone-600 to-stone-900 p-12";

  const { theme } = React.useContext(ThemeContext);

  return (
    <HashRouter>
      <div className={theme == "light" ? lightContainer : darkContainer}>
        <Routes>
          <Route path="/" element={<Hero />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>

        <Outlet />
      </div>
    </HashRouter>
  );
}

export default App;
