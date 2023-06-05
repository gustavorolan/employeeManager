import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useGlobalUser } from "../../../contexts/user.context";
import { useEmployeeManagerApi } from "../../../hooks/api";
import "./login.screen.css";
import { setToastWithTimout } from "../../../constant/functions";
import { X_TOAST } from "../../../constant/constants";

export function Login({ setToast }) {
  const [, setUser] = useGlobalUser();
  const { getLogin } = useEmployeeManagerApi();
  const navigate = useNavigate();
  const [login, setLogin] = useState({});

  const handleChange = ({ value, name }) => {
    setLogin({ ...login, [name]: value });
  };

  const handleClick = async () => {
    try {
      const response = await getLogin(login);
      setUser({ token: response.headers["x-auth-token"] });
      navigate("/menu");
    } catch (e) {
      setToastWithTimout(setToast, e.response.data, X_TOAST);
      navigate("/");
    }
  };

  return (
    <div className="login">
      <div
        className="formContainer"
        onChange={(Event) => handleChange(Event.target)}
      >
        <h1>Employee Manager</h1>
        <label htmlFor="email">Email</label>
        <input type="text" name="email" />
        <label htmlFor="password">Password</label>
        <input type="password" name="password" />
        <button onClick={handleClick} children={"Login"} />
        <Link className="link" to="/newUser" children={"Create New User"} />
      </div>
    </div>
  );
}
