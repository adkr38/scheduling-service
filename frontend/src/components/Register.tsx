import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ThemeContext } from "../context/ThemeContext";
import { LoginContext } from "../context/LoginContext";
import { User } from "../types/User";
import backendUtils from "../utils/backendUtils.ts";
import ComponentNav from "./ComponentNav";
import * as ai from "react-icons/ai";

function ManualRegister(props: { theme: string }) {
  const [username, setUsername] = useState<string>("");
  const [name, setName] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [passwordVisibile, setPasswordVisible] = useState<boolean>(false);
  const { toggleLogged } = React.useContext(LoginContext);
  const navigate = useNavigate();

  async function handleSubmit(
    e: React.FormEvent<HTMLFormElement>
  ): Promise<boolean> {
    const user: User = {
      id: null,
      username: username,
      name: name,
      password: password,
      roles: Array.of(),
      appointments: Array.of(),
    };
    const didRegister: boolean = await backendUtils.registerUser(user);

    e.preventDefault();

    if (didRegister) {
      let sessionId: boolean | string = await backendUtils.logIn(
        username,
        password
      );

      if (!sessionId) {
        console.warn("Unable to fetch sessionId");
        return false;
      }

      sessionId = sessionId as string;
      toggleLogged(sessionId, username);
      navigate("/");
      return true;
    }

    return false;
  }

  function handleNameChange(event: React.FormEvent<HTMLInputElement>) {
    setName((event.target as HTMLInputElement).value);
  }

  function handleUsernameChange(event: React.FormEvent<HTMLInputElement>) {
    setUsername((event.target as HTMLInputElement).value);
  }

  function handlePasswordChange(event: React.FormEvent<HTMLInputElement>) {
    setPassword((event.target as HTMLInputElement).value);
  }

  const pStyle =
    props.theme == "light"
      ? "text-sm text-stone-600 font-lora text-left"
      : "text-sm text-stone-200 font-lora text-left";

  const inputStyle =
    props.theme == "light"
      ? "bg-stone-200 rounded-md px-4 py-1 my-2 outline-none font-raleway w-full text-sm text-stone-600"
      : "bg-stone-600 rounded-md px-4 py-1 my-2 outline-none font-raleway w-full text-sm text-stone-200";

  const buttonStyle =
    props.theme == "light"
      ? "bg-gradient-to-br from-stone-50/50 to-stone-50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-sm flex flex-row items-center gap-4 active:scale-95"
      : "bg-gradient-to-br from-stone-400 to-stone-700/50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-md flex flex-row items-center gap-4 text-stone-300 active:scale-95";

  const eyeIconStyle =
    props.theme == "light"
      ? "absolute text-slate-800 top-1/2  right-1  -translate-x-1/2 -translate-y-1/2"
      : "absolute text-slate-200 top-1/2  right-1  -translate-x-1/2 -translate-y-1/2";

  return (
    <>
      <form
        onSubmit={handleSubmit}
        className="w-3/4 m-auto flex items-center justify-center p-8 pt-12 flex-col gap-y-12 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2"
      >
        <div className="w-full flex flex-col items-center">
          <p className={pStyle}>Tu nombre o apodo</p>
          <input
            type="text"
            onChange={handleNameChange}
            value={name}
            className={inputStyle}
          />
        </div>

        <div className="w-full flex flex-col items-center">
          <p className={pStyle}>Nombre de usuario</p>
          <input
            type="text"
            onChange={handleUsernameChange}
            value={username}
            className={inputStyle}
          />
        </div>

        <div className="w-full flex flex-col items-center text-left">
          <p className={pStyle}>Contraseña</p>
          <div className="w-full flex flex-row relative">
            <input
              type={passwordVisibile ? "text" : "password"}
              onChange={handlePasswordChange}
              value={password}
              className={inputStyle}
            />

            <button
              type="button"
              onClick={() => setPasswordVisible(!passwordVisibile)}
            >
              {passwordVisibile ? (
                <ai.AiOutlineEyeInvisible
                  className={eyeIconStyle}
                ></ai.AiOutlineEyeInvisible>
              ) : (
                <ai.AiOutlineEye className={eyeIconStyle}></ai.AiOutlineEye>
              )}
            </button>
          </div>
        </div>
        <button type="submit" className={buttonStyle}>
          Registrame
        </button>
      </form>
    </>
  );
}

function Register() {
  const { theme } = React.useContext(ThemeContext);

  return (
    <>
      <ComponentNav />
      <ManualRegister theme={theme} />
    </>
  );
}

export default Register;
