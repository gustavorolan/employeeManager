import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { GlobalUserProvider } from "./contexts/user.context";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <GlobalUserProvider>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </GlobalUserProvider>
  </React.StrictMode>
);
