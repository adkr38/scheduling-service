import React from "react";
import * as bs from "react-icons/bs";
import * as ai from "react-icons/ai";
import { ThemeContext } from "../context/ThemeContext";
import { Link } from "react-router-dom";

const ComponentNav = () => {
  const lightIcon = "text-2xl text-stone-600";
  const darkIcon = "text-2xl text-stone-200";

  const { theme, toggleTheme } = React.useContext(ThemeContext);

  return (
    <nav className="right-16 sm:mr-10 flex flex-row items-center justify-center gap-x-4 absolute">
      <Link to="/">
        <ai.AiOutlineHome
          className={theme == "light" ? lightIcon : darkIcon}
        ></ai.AiOutlineHome>
      </Link>
      <button
        className={
          theme == "light"
            ? "text-xl text-stone-600 pl-1"
            : "text-xl text-stone-200 pl-1"
        }
        onClick={toggleTheme}
      >
        <bs.BsMoon></bs.BsMoon>
      </button>
    </nav>
  );
};

export default ComponentNav;
