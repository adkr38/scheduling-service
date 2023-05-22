import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import { ThemeProvider } from "./context/ThemeContext";
import { LoginProvider } from "./context/LoginContext.tsx";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <LoginProvider>
      <ThemeProvider>
        <App />
      </ThemeProvider>
    </LoginProvider>
  </React.StrictMode>
);
