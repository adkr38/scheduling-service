import { User } from "./User";
import { Activity } from "./Activity";

export type Appointment = {
  id: number;
  date: string;
  activity: Activity;
  user: User;
};
