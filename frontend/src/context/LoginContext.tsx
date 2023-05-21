import React from "react";
import { useCookies } from "react-cookie";
import validateSession from "../utils/backendUtils.ts";

interface LoginContextProps {
  logged: boolean;
  sessionId: string;
  username: string;
  toggleLogged: (sessionId: string, username: string) => void;
}

export const LoginContext = React.createContext<LoginContextProps>({
  logged: false,
  sessionId: "",
  username: "",
  toggleLogged: () => {},
});

export const LoginProvider: React.FC<React.PropsWithChildren<{}>> = (props) => {
  const [cookies, setCookie, removeCookie] = useCookies([
    "sessionId",
    "username",
  ]);
  const [logged, setLogged] = React.useState<boolean>(false);
  const [sessionId, setSessionId] = React.useState<string>("");
  const [username, setUsername] = React.useState<string>("");

  React.useEffect(() => {
    console.log("Testing this out!");
    const storedSessionId = cookies.sessionId;
    const storedUsername = cookies.username;
    const checkSessionValid = async () => {
      if (storedSessionId && storedUsername) {
        const sessionIsValid = await validateSession(storedSessionId);
        if (!sessionIsValid) {
          console.log("Session id isn't actually valid!");
          removeCookie("sessionId");
          removeCookie("username");
        } else {
          setLogged(true);
          setSessionId(storedSessionId);
          setUsername(storedUsername);
        }
      }
    };
    checkSessionValid();
  }, [cookies, removeCookie]);

  function toggleLogged(sessionId: string, username: string) {
    setLogged(!logged);
    setUsername(username);
    setSessionId(sessionId);
    setCookie("sessionId", sessionId);
    setCookie("username", username);
  }

  return (
    <LoginContext.Provider
      value={{ logged, sessionId, username, toggleLogged }}
    >
      {props.children}
    </LoginContext.Provider>
  );
};
