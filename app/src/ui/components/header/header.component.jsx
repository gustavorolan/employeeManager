import React from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalUser } from "../../../contexts/user.context";
import "./header.component.css";
import img from "../../../assets/cissLogo.png";

export const HeaderManager = () => {
  const navigate = useNavigate();
  const [, setUser] = useGlobalUser();

  const handleLogout = () => {
    setUser({});
    navigate("/");
  };

  return (
    <header className="headerContainer">
      <div>
        <img src={img} alt="Ciss Logo" />
        <button onClick={handleLogout}>Logout</button>
      </div>
    </header>
  );
};
