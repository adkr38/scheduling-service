import { User } from "../types/User";

async function validateSession(sessionId: string) {
  console.log("Validating ", sessionId);
  const response = await fetch("http://localhost:8080/api/validate", {
    headers: {
      Authorization: `Bearer ${sessionId}`,
    },
  });

  const data = await response.json();
  console.log(data);

  return response.ok;
}

async function logIn(
  username: string,
  password: string
): Promise<boolean | string> {
  const response = await fetch(
    `http://localhost:8080/api/login?username=${username}&password=${password}`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
  if (!response.ok) {
    const badResponse = await response.json();
    console.error(badResponse);
    return false;
  }
  const data: { access_token: string } = await response.json();
  const bearerToken: string = data.access_token;
  return bearerToken;
}

async function registerUser(user: User): Promise<boolean> {
  console.log(JSON.stringify(user));
  const response = await fetch("http://localhost:8080/api/users", {
    method: "POST",
    body: JSON.stringify(user),
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response.ok;
}

export default { validateSession, registerUser, logIn };
