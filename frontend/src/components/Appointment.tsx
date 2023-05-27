import backendUtils from "../utils/backendUtils";
import { LoginContext } from "../context/LoginContext";
import React from "react";
import DatePicker from "react-datepicker";
import ComponentNav from "./ComponentNav";
import "react-datepicker/dist/react-datepicker.css";
import es from "date-fns/locale/es";
import { ThemeContext } from "../context/ThemeContext";

const Appointment = () => {
  const { username, sessionId } = React.useContext(LoginContext);
  const { theme } = React.useContext(ThemeContext);
  const [selectedDate, setSelectedDate] = React.useState(new Date());
  const [selectedTime, setSelectedTime] = React.useState("09:00");
  const [selectedActivity, setSelectedActivity] = React.useState("Indeciso");
  const [loading, setLoading] = React.useState(false);
  const [responseMessage, setResponseMessage] = React.useState("");

  const pStyling =
    theme == "light"
      ? "text-sm text-slate-700 font-raleway"
      : "text-sm text-slate-200 font-raleway";

  const buttonStyling: string =
    theme == "light"
      ? "my-10 bg-gradient-to-br from-stone-50/50 to-stone-50 rounded-md px-4 py-1 font-raleway shadow-stone-700 shadow-sm flex flex-row items-center gap-4 active:scale-95 active:shadow-inner active:shadow-stone-400"
      : "my-10 bg-gradient-to-br from-stone-200/50 to-stone-500/50 rounded-md px-4 py-1 font-raleway shadow-stone-600 shadow-md flex flex-row items-center gap-4 text-stone-300  active:scale-95 active:shadow-inner active:shadow-stone-800";

  const h1Styling =
    theme == "light"
      ? "text-2xl font-lora text-stone-800"
      : "text-2xl font-lora text-stone-200";

  const handleDateChange = (date: Date) => {
    setSelectedDate(date);
  };

  const handleTimeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedTime(e.target.value);
  };

  const handleActivityChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedActivity(e.target.value);
  };

  const handleAppointmentSubmit = async () => {
    setLoading(true);
    const selectedDateTime = new Date(selectedDate);
    console.log(selectedTime);
    const [hours, minutes] = selectedTime.split(":");
    const hoursInt = parseInt(hours, 10);
    const minutesInt = parseInt(minutes, 10);

    selectedDateTime.setHours(hoursInt);
    selectedDateTime.setMinutes(minutesInt);

    const formattedDateTime = selectedDateTime.toISOString();

    const appointmentDTO = {
      date: formattedDateTime,
      username: username,
      activity: selectedActivity,
    };

    try {
      const response = await backendUtils.postAppointment(
        sessionId,
        appointmentDTO
      );
      if (response) {
        const data = await response.json();
        setLoading(false);
        setResponseMessage(data.message);
        return;
      } else {
        setLoading(false);
        setResponseMessage(
          "No se pudo conectar al servidor, asegurese de estar registrado."
        );
      }
    } catch (error) {
      setLoading(false);
      setResponseMessage(
        "No se pudo conectar al servidor, asegurese de estar registrado."
      );
    }
  };
  return (
    <>
      <ComponentNav />
      <div className="flex flex-col items-center justify-center absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2">
        <h1 className={h1Styling}>Ficha tu cita</h1>
        <div className="my-4">
          <label htmlFor="datepicker" className="block mb-2 text-center">
            <p className={pStyling}>Fecha:</p>
          </label>
          <DatePicker
            id="datepicker"
            selected={selectedDate}
            onChange={handleDateChange}
            dateFormat="MMMM d, yyyy"
            className="w-full px-4 py-2 border border-gray-300 rounded-md text-center"
            locale={es}
          />
        </div>
        <div className="flex flex-col md:flex-row items-center justify-center gap-4">
          <div className="my-4">
            <label htmlFor="timepicker" className="block mb-2 text-center">
              <p className={pStyling}>Hora:</p>
            </label>
            <select
              id="timepicker"
              value={selectedTime}
              onChange={handleTimeChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md"
            >
              <option value="09:00">9:00 AM</option>
              <option value="09:30">9:30 AM</option>
              <option value="10:00">10:00 AM</option>
              <option value="10:30">10:30 AM</option>
              <option value="11:00">11:00 AM</option>
              <option value="11:30">11:30 AM</option>
              <option value="12:00">12:00 AM</option>
              <option value="12:30">12:30 AM</option>
              <option value="15:00">15:00 AM</option>
              <option value="15:30">15:30 AM</option>
              <option value="16:00">16:00 AM</option>
              <option value="16:30">16:30 AM</option>
              <option value="17:00">17:00 AM</option>
              <option value="17:30">17:30 AM</option>
              <option value="18:00">18:00 AM</option>
              <option value="18:30">18:30 AM</option>
              <option value="19:00">19:00 AM</option>
              <option value="19:30">19:30 AM</option>
            </select>
          </div>

          <div className="my-4">
            <label className="block mb-2 text-center">
              <p className={pStyling}>Servicio:</p>
            </label>
            <select
              value={selectedActivity}
              onChange={handleActivityChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md"
            >
              <option value="Mohicano">Mohicano</option>
              <option value="Burst Fade">Burst Fade</option>
              <option value="Mullet">Mullet</option>
              <option value="Fade y diseño">Fade + Diseño</option>
              <option value="Drop Fade">Drop Fade</option>
              <option value="Low taper">Low Taper</option>
              <option value="Fade + Barba">Fade + Barba</option>
              <option value="Mid taper">Mid taper</option>
              <option value="Tratamiento waves">Tratamiento Waves</option>
              <option value="Indeciso">Indeciso</option>
            </select>
          </div>
        </div>
        <button
          onClick={handleAppointmentSubmit}
          className={buttonStyling}
          disabled={loading}
        >
          {loading ? "Registrando..." : "Registrar"}
        </button>
        {responseMessage.length > 0 && (
          <p className={pStyling}>{responseMessage}</p>
        )}
      </div>
    </>
  );
};

export default Appointment;
