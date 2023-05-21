import { Role } from "./Role";
import { Appointment } from "./Appointment";

export type User = {
  name: string;
  username: string;
  password: string;
  roles: Array<Role>;
  appointments: Array<Appointment>;
};
