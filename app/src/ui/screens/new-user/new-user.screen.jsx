import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalUser } from "../../../contexts/user.context";
import { useEmployeeManagerApi } from "../../../hooks/api";
import "../login/login.screen.css";

export const NewUser = ({ setToast }) => {
  const [, setUser] = useGlobalUser();
  const { createNewUser, getLogin } = useEmployeeManagerApi();
  const navigate = useNavigate();
  const [newUserRequest, setNewUserRequest] = useState({});

  const handleChange = ({ value, name }) => {
    setNewUserRequest({ ...newUserRequest, [name]: value });
  };

  const handleClick = async () => {
    const { email, rePassword, password } = newUserRequest;
    try {
      if (rePassword === password) {
        await createNewUser(newUserRequest);
        const response = await getLogin({ email, password });
        setUser({ token: response.headers["x-auth-token"] });
        navigate("/menu");
      }
    } catch (e) {
      navigate("/newUser");
    }
  };

  return (
    <div className="newUser">
      <div
        className="formContainer"
        onChange={(Event) => handleChange(Event.target)}
      >
        <h1>Employee Manager</h1>
        <label htmlFor="email">Email</label>
        <input type="text" name="email" />
        <label htmlFor="password">New password</label>
        <input type="password" name="password" />
        <label htmlFor="password">Re-type your password</label>
        <input type="password" name="rePassword" />
        <button onClick={handleClick} children={"Create New User"} />
      </div>
    </div>
  );
};
