import React, { useState } from "react";
import {
  GoogleLoginResponse,
  GoogleLoginResponseOffline,
  GoogleLogin,
} from "react-google-login";
import FacebookLogin from "react-facebook-login/dist/facebook-login-render-props";
import { ThemeContext } from "../context/ThemeContext";
import ComponentNav from "./ComponentNav";
import * as ai from "react-icons/ai";
import * as bi from "react-icons/bi";
import bgImage from "../assets/bg2.jpg";

const GoogleLoginComponent = (props: { theme: string }) => {
  const responseGoogle = (response: any) => {
    // TODO: Handle Facebook login response
  };

  const buttonStyle =
    props.theme == "light"
      ? "bg-gradient-to-br from-stone-50/50 to-stone-50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-sm flex flex-row items-center gap-4"
      : "bg-gradient-to-br from-stone-400 to-stone-700/50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-md flex flex-row items-center gap-4 text-stone-300 ";

  const iconStyle =
    props.theme == "light"
      ? "text-md text-stone-600"
      : "text-md text-stone-200";

  return (
    <GoogleLogin
      clientId="<your-google-client-id>"
      onSuccess={responseGoogle}
      onFailure={responseGoogle}
      cookiePolicy={"single_host_origin"}
      render={(renderProps) => (
        <button
          onClick={renderProps.onClick}
          disabled={renderProps.disabled}
          className={buttonStyle}
        >
          <ai.AiOutlineGoogle className={iconStyle}></ai.AiOutlineGoogle>
          Login con Google
        </button>
      )}
    />
  );
};

const FacebookLoginComponent = (props: { theme: string }) => {
  const responseFacebook = (response: any) => {
    // TODO: Handle Facebook login response
  };

  const buttonStyle =
    props.theme == "light"
      ? "bg-gradient-to-br from-stone-50/50 to-stone-50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-sm flex flex-row items-center gap-4"
      : "bg-gradient-to-br from-stone-400 to-stone-700/50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-md flex flex-row items-center gap-4 text-stone-300 ";

  const iconStyle =
    props.theme == "light"
      ? "text-md text-stone-600"
      : "text-md text-stone-200";

  return (
    <FacebookLogin
      appId="<your-facebook-app-id>"
      autoLoad={false}
      onClick={() => console.log("Facebook button clicked")}
      callback={responseFacebook}
      render={(renderProps) => (
        <button
          onClick={renderProps.onClick}
          disabled={renderProps.disabled}
          className={buttonStyle}
        >
          <ai.AiFillFacebook className={iconStyle}></ai.AiFillFacebook>
          Login con Facebook
        </button>
      )}
    />
  );
};

function ManualRegister(props: { theme: string }) {
  const [username, setUsername] = useState<string>("");
  const [mail, setMail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  function handleUsernameChange(event: React.FormEvent<HTMLInputElement>) {
    setUsername((event.target as HTMLInputElement).value);
  }

  function handleMailChange(event: React.FormEvent<HTMLInputElement>) {
    setMail((event.target as HTMLInputElement).value);
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
      ? "bg-stone-50 rounded-md px-4 py-0.5 my-2 outline-none font-raleway text-md text-left"
      : "bg-stone-600 rounded-md px-4 py-0.5 my-2 outline-none font-raleway text-md text-left";

  return (
    <>
      <div>
        <p className={pStyle}>Correo electrónico</p>
        <input
          type="text"
          onChange={handleMailChange}
          value={mail}
          className={inputStyle}
        />
      </div>
      <div>
        <p className={pStyle}>Nombre de usuario</p>
        <input
          type="text"
          onChange={handleUsernameChange}
          value={username}
          className={inputStyle}
        />
      </div>

      <div>
        <p className={pStyle}>Contraseña</p>
        <input
          type="text"
          onChange={handlePasswordChange}
          value={password}
          className={inputStyle}
        />
      </div>
    </>
  );
}

function ManualLogin(props: { theme: string }) {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

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
      ? "bg-stone-50 rounded-md px-4 py-0.5 my-2 outline-none font-raleway sm:text-md w-full text-stone-600"
      : "bg-stone-600 rounded-md px-4 py-0.5 my-2 outline-none font-raleway sm:text-md w-full text-stone-200";

  const buttonStyle =
    props.theme == "light"
      ? "bg-gradient-to-br from-stone-50/50 to-stone-50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-sm flex flex-row items-center gap-4"
      : "bg-gradient-to-br from-stone-400 to-stone-700/50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-md flex flex-row items-center gap-4 text-stone-300 ";

  return (
    <div className="m-auto h-full flex flex-col items-center justify-center gap-10 w-full absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
      <form className="w-3/4 m-auto flex items-center justify-center p-8 pt-12 flex-col gap-y-12 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
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
          <input
            type="text"
            onChange={handlePasswordChange}
            value={password}
            className={inputStyle}
          />
        </div>
        <button type="submit" className={buttonStyle}>
          Login
        </button>
      </form>
    </div>
  );
}

function LoginGoBack(props: {
  theme: string;
  setManualLogin: (state: boolean) => void;
}) {
  const iconStyle =
    props.theme == "light"
      ? "text-2xl text-slate-600"
      : "text-2xl text-slate-200";
  return (
    <div className="flex flex-row items-center justify-center absolute right-10 top-6 gap-x-6">
      <button className="z-50" onClick={() => props.setManualLogin(false)}>
        <bi.BiArrowBack className={iconStyle}></bi.BiArrowBack>
      </button>
    </div>
  );
}

function Login() {
  const { theme } = React.useContext(ThemeContext);
  const [manualLogin, setManualLogin] = useState<boolean>(false);

  const titleStyle =
    theme == "light"
      ? "text-xl text-stone-600 font-lora mb-2"
      : "text-xl text-stone-200 font-lora mb-2";

  const containerStyle =
    theme == "light"
      ? "absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 bg-gradient-to-tr from-stone-200/50 to-stone-300/50 w-3/4 h-3/4 sm:w-1/2 sm:h-2/3 rounded-md"
      : "absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 bg-gradient-to-tr from-stone-600 to-stone-700 w-3/4 h-3/4 sm:w-1/2 sm:h-2/3 rounded-md ";

  const buttonStyle =
    theme == "light"
      ? "bg-gradient-to-br from-stone-50/50 to-stone-50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-sm flex flex-row items-center gap-4"
      : "bg-gradient-to-br from-stone-400 to-stone-700/50 rounded-md px-4 py-1 font-lora shadow-stone-700 shadow-md flex flex-row items-center gap-4 text-stone-300 ";

  const iconStyle =
    theme == "light" ? "text-md text-stone-600" : "text-md text-stone-200";

  const bgImg: string =
    "pointer-events-none absolute h-full w-full object-cover -z-10 opacity-20 rounded-xl";

  return (
    <>
      <ComponentNav />
      <div className={containerStyle}>
        <img className={bgImg} src={bgImage} alt="Barbershop background" />
        {manualLogin && (
          <LoginGoBack theme={theme} setManualLogin={setManualLogin} />
        )}
        <form className="w-full m-auto flex items-center justify-center p-8 pt-12 flex-col gap-y-12 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
          {manualLogin ? (
            <ManualLogin theme={theme} />
          ) : (
            <>
              <p className={titleStyle}>Acceso</p>
              <button
                className={buttonStyle}
                onClick={() => setManualLogin(!manualLogin)}
              >
                <ai.AiOutlineUser className={iconStyle}></ai.AiOutlineUser>
                Login con mi cuenta
              </button>
              <GoogleLoginComponent theme={theme} />
              <FacebookLoginComponent theme={theme} />
            </>
          )}
        </form>
      </div>
    </>
  );
}

export default Login;
