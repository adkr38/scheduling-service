// import React from "react";
import bgImage from "../assets/bg1.jpg";

const Hero = () => {
  const lightDiv: string =
    "h-screen  w-screen flex items-center m-auto bg-stone-50 justify-center bg-cover bg-opacity-70 p-8 relative";
  const darkDiv: string =
    "h-screen  w-screen flex items-center m-auto bg-stone-900 justify-center bg-cover bg-opacity-70 p-8 relative";
  const anyImg: string = "absolute h-full w-full object-cover -z-10 opacity-30";

  const lightTitle: string =
    "font-markScript text-stone-600 text-5xl absolute top-1/4";

  return (
    <div className={lightDiv}>
      <img className={anyImg} src={bgImage} alt="Barbershop background" />
      <p className={lightTitle}>Bapp</p>
    </div>
  );
};

export default Hero;
