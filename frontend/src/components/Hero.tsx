// import React from "react";
import { ReactNode } from "react";
import bgImage from "../assets/bg1.jpg";
import React from "react";
import * as bs from "react-icons/bs";
import * as ai from "react-icons/ai";
import * as gr from "react-icons/gr";
import { ThemeContext } from "../context/ThemeContext";
import Nav from "./Nav";

interface CardProps {
  icon: ReactNode;
  text: string;
}

const Card = ({ icon, text }: CardProps) => {
  const { theme } = React.useContext(ThemeContext);
  const lightCard =
    "px-14 py-4 text-center bg-gradient-to-br from-stone-300/50 to-stone-200/75 flex flex-col items-center justify-center rounded-md gap-y-4 active:from-stone-400/50 active:to-stone-300/50 active:scale-90 group";
  const darkCard =
    "px-14 py-4 text-center bg-gradient-to-br from-stone-700/50 to-stone-800/75 flex flex-col items-center justify-center rounded-md gap-y-4 active:from-stone-700/50 active:to-stone-600/50 active:scale-90 group";
  const lightP =
    "text-sm text-slate-700 font-raleway group-active:text-stone-50";
  const darkP =
    "text-sm text-slate-200 font-raleway group-active:text-stone-600";
  return (
    <div className={theme == "light" ? lightCard : darkCard}>
      {icon}
      <p className={theme == "light" ? lightP : darkP}>{text}</p>
    </div>
  );
};

const Hero = () => {
  const { theme } = React.useContext(ThemeContext);

  const lightContainer: string =
    "left-1/2 top-2/3 -translate-x-1/2 -translate-y-2/3 w-3/4 h-3/4 flex flex-col gap-2 m-auto bg-stone-50 justify-center items-center bg-cover bg-opacity-70 absolute p-12";

  const darkContainer: string =
    "rounded-lg left-1/2 top-2/3 -translate-x-1/2 -translate-y-2/3 w-3/4 h-3/4 flex flex-col gap-2 m-auto bg-stone-800 justify-center items-center bg-cover bg-opacity-70 absolute p-12";

  const bgImg: string =
    "pointer-events-none absolute h-full w-full object-cover -z-10 opacity-30 rounded-xl";

  const textContainer: string = "w-full m-auto";

  const cardsContainer: string =
    "m-auto h-1/2 w-full md:w-3/4 grid gap-y-6 gap-x-8 grid-cols-2 items-center";

  const lightTitle: string =
    "font-lora font-normal text-stone-600 text-3xl sm:text-4xl";

  const darkTitle: string =
    "font-lora font-normal text-stone-200 text-3xl sm:text-4xl";

  const iconStyle: string =
    theme == "light" ? "text-stone-700" : "text-stone-200";

  return (
    <>
      <Nav />
      <div className={theme == "light" ? lightContainer : darkContainer}>
        <img className={bgImg} src={bgImage} alt="Barbershop background" />
        <div className={textContainer}>
          <p className={theme == "light" ? lightTitle : darkTitle}>
            Marca tu próxima cita.
          </p>
        </div>
        <div className={cardsContainer}>
          <Card
            icon={<bs.BsCalendar size={30} className={iconStyle} />}
            text={"Disponibilidad"}
          />

          <Card
            icon={<ai.AiOutlineFileSearch size={30} className={iconStyle} />}
            text={"Agendar"}
          />

          <Card
            icon={<ai.AiOutlineBook size={30} className={iconStyle} />}
            text={"Catálogo"}
          />

          <Card
            icon={<gr.GrCloudlinux size={30} className={iconStyle} />}
            text={"Reservas"}
          />
        </div>
      </div>
    </>
  );
};

export default Hero;
