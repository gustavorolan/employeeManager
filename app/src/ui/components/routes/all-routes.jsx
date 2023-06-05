import React from "react";
import { Routes, Route } from "react-router-dom";
import { Login, MenuScreen, NewUser } from "../../screens";
import { RoutesProtection } from "./routes-protection.component";

export const AllRoutes = ({ setToast }) => {
  return (
    <Routes>
      <Route path="/" element={<Login setToast={setToast} />} />
      <Route path="/newUser" element={<NewUser  setToast={setToast} />} />
      <Route path="/menu" element={<RoutesProtection setToast={setToast} />}>
        <Route path="/menu" element={<MenuScreen setToast={setToast} />} />
      </Route>
    </Routes>
  );
};
