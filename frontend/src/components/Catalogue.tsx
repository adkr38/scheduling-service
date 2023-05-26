import React from "react";
import { Activity } from "../types/Activity.ts";
import { ThemeContext } from "../context/ThemeContext";
import backendUtils from "../utils/backendUtils";
import LoadingScreen from "./LoadingScreen.tsx";
import ErrorScreen from "./ErrorScreen.tsx";
import ComponentNav from "./ComponentNav.tsx";

const CatalogueActivity = ({
  theme,
  activityProps,
}: {
  theme: string;
  activityProps: Activity;
}): JSX.Element => {
  const lightCard =
    "h-60 w-60 px-[3.2rem] sm:px-14 py-6 text-center bg-gradient-to-br from-stone-300/75 to-stone-200/75 shadow-stone-400 shadow-sm flex flex-col items-center justify-center rounded-lg gap-y-4 active:from-stone-400/50 active:to-stone-300/50 active:scale-90 active:shadow-inner active:shadow-stone-400 hover:scale-110 hover:rounded-2xl active:duration-300 duration-500";

  const darkCard =
    "h-60 w-60 px-[3.2rem] sm:px-14 py-6 text-center bg-gradient-to-br from-neutral-600/75 to-stone-800/75 shadow-zinc-800 shadow-sm flex flex-col items-center justify-center rounded-lg gap-y-4 active:from-zinc-700/50 active:to-zinc-900/50 active:scale-90 active:shadow-inner active:shadow-zinc-900 hover:scale-110 hover:rounded-2xl active:duration-300 duration-500";

  const pStyling =
    theme == "light"
      ? "text-sm text-slate-700 font-raleway"
      : "text-sm text-slate-200 font-raleway";

  const [displayImage, setDisplayImage] = React.useState<boolean>(true);
  const imageStyling = "rounded-md h-full w-full object-cover";

  const handleButtonClick = () => {
    setDisplayImage(!displayImage);
  };

  return (
    <button onClick={handleButtonClick}>
      <div className={theme == "light" ? lightCard : darkCard}>
        {displayImage ? (
          <img src={activityProps.imageUrl} className={imageStyling} />
        ) : (
          <div className="flex items-center justify-center flex-col">
            <p className={pStyling}>{activityProps.activity}</p>

            <p className={pStyling}>{activityProps.price}€</p>
          </div>
        )}
      </div>
    </button>
  );
};

const Catalogue = (): JSX.Element => {
  const { theme } = React.useContext(ThemeContext);
  const [loading, setLoading] = React.useState<boolean>(true);
  const [error, setError] = React.useState<string>("");
  const [activities, setActivities] = React.useState<Array<Activity>>([]);

  const bgStyling: string =
    theme === "light"
      ? "grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8 items-center w-full p-20"
      : "grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8 items-center w-full p-20";

  React.useEffect(() => {
    const fetchActivities = async () => {
      try {
        const activityResponse = await backendUtils.getCatalogue();
        if (activityResponse.statusCode === 200) {
          setActivities(activityResponse.content);
        } else {
          setError(activityResponse.message);
        }
      } catch (error) {
        setError(error as string);
      } finally {
        setLoading(false);
      }
    };
    fetchActivities();
  }, []);

  if (loading) {
    return <LoadingScreen />;
  } else if (error) {
    return <ErrorScreen message={error} />;
  }

  return (
    <>
      <ComponentNav />
      <div className={bgStyling}>
        {activities.map((x) => (
          <CatalogueActivity theme={theme} activityProps={x} key={x.id} />
        ))}
      </div>
    </>
  );
};

export default Catalogue;
