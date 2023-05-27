import { User } from "../types/User";
import { AppointmentDTO } from "../types/Appointment";

async function postAppointment(sessionId: string, dto: AppointmentDTO) {
  const response = await fetch("http://localhost:8080/api/appointment", {
    method: "POST",
    body: JSON.stringify(dto),
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${sessionId}`,
    },
  });
  return response;
}

async function getCatalogue() {
  const response = await fetch("http://localhost:8080/api/activity/all", {
    headers: {
      "Content-Type": "application/json",
    },
  });

  const data = await response.json();
  return data;
}

async function validateSession(sessionId: string) {
  const response = await fetch("http://localhost:8080/api/validate", {
    headers: {
      Authorization: `Bearer ${sessionId}`,
    },
  });

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
  const response = await fetch("http://localhost:8080/api/users", {
    method: "POST",
    body: JSON.stringify(user),
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response.ok;
}

export default {
  validateSession,
  registerUser,
  logIn,
  getCatalogue,
  postAppointment,
};
