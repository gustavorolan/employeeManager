import { useState } from "react";
import { useEmployeeManagerApi } from "../../../hooks/api/employee-manager/use-employee-manager-api-hook";
import "../../screens/menu/menu.screen.css";
import { setToastWithTimout } from "../../../constant/functions";
import { ERROR_REQUEST_MESSAGE_NULL_FIELD, X_TOAST } from "../../../constant/constants";

export const NewEmployee = ({ getAllEmployeesFromUserApi, setToast }) => {
  const { createNewEmployee } = useEmployeeManagerApi();

  const [newUserRequest, setNewUserRequest] = useState({});

  const handleChange = ({ value, name }) => {
    setNewUserRequest({ ...newUserRequest, [name]: value });
  };

  const updateHandler = async () => {
    try {
      await createNewEmployee({ ...newUserRequest });
      await getAllEmployeesFromUserApi();
    } catch (e) {
        setToastWithTimout(setToast, ERROR_REQUEST_MESSAGE_NULL_FIELD, X_TOAST);
    }
  };

  return (
    <div
      className="subMenuContainer"
      onChange={(Event) => handleChange(Event.target)}
    >
      <div className="fieldName">
        <input type="text" name="name" placeholder="Name" />
        <input type="text" name="surname" placeholder="Surname" />
        <input type="text" name="email" placeholder="Email" />
        <input type="text" name="pisNumber" placeholder="Pis" />
      </div>
      <div className="buttonMenu">
        <button onClick={() => updateHandler()}>☑️</button>
      </div>
    </div>
  );
};
