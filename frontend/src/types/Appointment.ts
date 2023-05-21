import { User } from "./User";
import { Activity } from "./Activity";

export type Appointment = {
  date: string;
  activity: Activity;
  user: User;
};
