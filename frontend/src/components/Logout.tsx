import React from "react";
import { LoginContext } from "../context/LoginContext";
import * as io5 from "react-icons/io5";

const Logout = (props: { username: string; theme: string }) => {
  const { toggleLogged } = React.useContext(LoginContext);
  const [isExiting, setIsExiting] = React.useState<boolean>(false);

  function LogoutPopup(props: { username: string; theme: string }) {
    function handleLogout(): void {
      toggleLogged("", "");
      setIsExiting(false);
    }

    function handleExit() {
      setIsExiting(false);
    }

    function handleBackgroundClick(e: React.MouseEvent<HTMLDivElement>) {
      const target = e.target as HTMLDivElement;
      if (target.classList.contains("popupBg")) {
        setIsExiting(false);
      }
    }

    const containerStyling: string =
      "popupBg z-30 text-center w-full h-full fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2";

    const wrapperStyling: string =
      props.theme == "light"
        ? "backdrop-filter backdrop-blur-sm px-6 flex flex-col gap-6 items-center justify-center absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/2 h-1/2 bg-gradient-to-br from-stone-300/75 to-stone-400/50 text-center rounded-lg shadow-sm shadow-slate-500"
        : "backdrop-filter backdrop-blur-sm  px-6 flex flex-col gap-6 items-center justify-center absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/2 h-1/2 bg-gradient-to-br from-stone-800/75 to-stone-700/50 text-center rounded-lg shadow-sm shadow-slate-900";

    const pStyling: string =
      props.theme == "light"
        ? "font-raleway font-sm text-md text-slate-700"
        : "font-raleway font-sm text-md text-slate-200";

    const buttonStyling: string =
      props.theme == "light"
        ? "bg-gradient-to-br from-stone-50/50 to-stone-50 rounded-md px-4 py-1 font-raleway shadow-stone-700 shadow-sm flex flex-row items-center gap-4 active:scale-95"
        : "bg-gradient-to-br from-stone-400 to-stone-700/50 rounded-md px-4 py-1 font-raleway shadow-stone-700 shadow-md flex flex-row items-center gap-4 text-stone-300  active:scale-95";

    return (
      <div
        className={containerStyling}
        onClick={(e) => handleBackgroundClick(e)}
      >
        <div className={wrapperStyling}>
          <p className={pStyling}>Seguro que quere salir de su cuenta?</p>
          <div className="flex flex-col sm:flex-row justify-center items-center gap-10">
            <button className={buttonStyling} onClick={handleLogout}>
              Sí, Log out.
            </button>
            <button className={buttonStyling} onClick={handleExit}>
              Atrás
            </button>
          </div>
        </div>
      </div>
    );
  }
  const iconStyling: string =
    props.theme == "light"
      ? "text-2xl text-stone-700"
      : "text-2xl text-stone-200";
  return (
    <>
      {isExiting && (
        <LogoutPopup username={props.username} theme={props.theme} />
      )}
      <button>
        <io5.IoExitOutline
          className={iconStyling}
          onClick={() => setIsExiting(true)}
        ></io5.IoExitOutline>
      </button>
    </>
  );
};

export default Logout;
