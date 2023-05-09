// import React from "react";
import { ReactNode, useContext } from "react";
import React from "react";
import * as bs from "react-icons/bs";
import * as ai from "react-icons/ai";
import * as gr from "react-icons/gr";
import { ThemeContext } from "../context/ThemeContext";
import { Link } from "react-router-dom";

const Nav = () => {
  const [loggedIn, setLoggedIn] = React.useState(false);

  const lightButton =
    "text-stone-600 font-raleway text-md border border-stone-900 bg-stone-50 rounded-md px-4 py-0.5 active:bg-stone-200";

  const darkButton =
    "text-stone-200 font-raleway text-md border border-stone-50 bg-stone-800 rounded-md px-4 py-0.5 active:bg-stone-600";

  const lightIcon = "text-2xl text-stone-600";
  const darkIcon = "text-2xl text-stone-200";

  const { theme, toggleTheme } = React.useContext(ThemeContext);

  return (
    <nav className="right-16 sm:mr-10 flex flex-row items-center gap-x-4 absolute">
      <a
        className={theme == "light" ? lightIcon : darkIcon}
        href="mailto:adkr38@gmail.com"
      >
        <ai.AiOutlineMail></ai.AiOutlineMail>
      </a>
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
      <a
        className={(theme == "light" ? lightIcon : darkIcon) + " -scale-x-100"}
        href="tel:+34694470488"
      >
        <ai.AiOutlinePhone></ai.AiOutlinePhone>
      </a>
      <Link to="/login">
        <button className={theme == "light" ? lightButton : darkButton}>
          {loggedIn ? "Log out" : "Log in"}
        </button>
      </Link>
    </nav>
  );
};

export default Nav;