import { Role } from "./Role";
import { Appointment } from "./Appointment";

export type User = {
  id: number;
  name: string;
  username: string;
  password: string;
  roles: Array<Role>;
  appointments: Array<Appointment>;
};
