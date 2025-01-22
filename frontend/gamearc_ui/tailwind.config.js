/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./src/*.{js,ts,jsx,tsx}",
    "./*.{js,ts,jsx,tsx}",
  ],
  theme: {
    colors: {
      'gaBlue': '#49485C',
      'gaWhite': '#D9D9D9',
    },
    extend: {},
  },
  plugins: [],
}

