import React from "react";
import { ThemeContext } from "../context/ThemeContext";

const LoadingScreen = () => {
  const { theme } = React.useContext(ThemeContext);
  const bgStyling: string =
    theme == "light"
      ? "flex items-center justify-center h-screen w-screen "
      : "flex items-center justify-center h-screen w-screen ";

  return (
    <div className={bgStyling}>
      <div className="flex items-center space-x-4">
        <svg
          className="w-6 h-6 animate-spin text-gray-600"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
        >
          <circle
            className="opacity-25"
            cx="12"
            cy="12"
            r="10"
            stroke="currentColor"
            strokeWidth="4"
          ></circle>
          <path
            className="opacity-75"
            fill="currentColor"
            d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 0012 20c4.411 0 8-3.589 8-8h-4a4.001 4.001 0 01-3.873-3.116L8 9h8V7H8a6 6 0 100 12h4v-2.109A7.965 7.965 0 0012 17a7.965 7.965 0 00-7.999-7.818L4 9z"
          ></path>
        </svg>
        <span className={theme == "light" ? "text-gray-600" : "text-gray-200"}>
          Cargando...
        </span>
      </div>
    </div>
  );
};

export default LoadingScreen;
