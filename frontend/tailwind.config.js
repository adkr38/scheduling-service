/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,js,jsx,tx,tsx}"],
  theme: {
    extend: {
      fontFamily: {
        lora: ["lora", "Helvetica"],
        raleway: ["raleway", "Helvetica"],
      },
    },
  },
  plugins: [],
};
