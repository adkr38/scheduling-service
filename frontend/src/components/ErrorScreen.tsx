import React from "react";
import { ThemeContext } from "../context/ThemeContext";
import ComponentNav from "./ComponentNav";

const ErrorScreen = ({ message }: { message: string }) => {
  const { theme } = React.useContext(ThemeContext);
  const bgStyling: string =
    theme == "light"
      ? "flex items-center justify-center h-screen w-screen"
      : "flex items-center justify-center h-screen w-screen";

  return (
    <>
      <ComponentNav />
      <div className={bgStyling}>
        <span className={theme == "light" ? "text-gray-600" : "text-gray-200"}>
          Error descargando información : {message}
        </span>
      </div>
    </>
  );
};

export default ErrorScreen;
