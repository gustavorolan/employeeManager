import { useEmployeeManagerApi } from "../../../hooks/api/employee-manager/use-employee-manager-api-hook";
import { useState } from "react";
import "../../screens/menu/menu.screen.css";
import { setToastWithTimout } from "../../../constant/functions";
import { X_TOAST } from "../../../constant/constants";

export function EmployeesFromUser({
  employeeList,
  getAllEmployeesFromUserApi,
  setToast,
}) {
  const { updateEmployee, deleteEmployeeByIdFromUser } =
    useEmployeeManagerApi();
  const [idEditorActive, setIdEditorActive] = useState();

  const [updateRequest, setUpdateRequest] = useState({});

  const deleteHandler = async (id) => {
    try {
      await deleteEmployeeByIdFromUser(id);
      await getAllEmployeesFromUserApi();
    } catch (e) {
      setToastWithTimout(setToast, e.response.data, X_TOAST);
    }
  };

  const updateHandler = async (employeeId) => {
    try {
      await updateEmployee({ employeeId, ...updateRequest });
      setIdEditorActive();
      await getAllEmployeesFromUserApi();
    } catch (e) {
      setToastWithTimout(setToast, e.response.data, X_TOAST);
    }
  };

  const handleChange = ({ value, name }) => {
    setUpdateRequest({ ...updateRequest, [name]: value });
  };

  const handleEditor = (id) => {
    if (id === idEditorActive) setIdEditorActive();
    else setIdEditorActive(id);
  };

  return employeeList.map(({ id, name, surname, email, pisNumber }) => (
    <div className="subMenuContainer" key={id}>
      {idEditorActive !== id && (
        <>
          <div className="fieldName">
            <p>{name}</p>
            <p>{surname}</p>
            <p>{email}</p>
            <p>{pisNumber}</p>
          </div>
          <div className="buttonMenu">
            <button onClick={() => handleEditor(id)}>✎</button>
            <button onClick={() => deleteHandler(id)}>🗑️</button>
          </div>
        </>
      )}
      {idEditorActive === id && (
        <>
          <div
            className="fieldName"
            onChange={(Event) => handleChange(Event.target)}
          >
            <input type="text" name="name" placeholder="Name" />
            <input type="text" name="surname" placeholder="Surname" />
            <input type="text" name="email" placeholder="Email" />
            <input type="text" name="pisNumber" placeholder="Pis" />
          </div>
          <div className="buttonMenu">
            <button onClick={() => handleEditor(id)}>✎</button>
            <button onClick={() => updateHandler(id)}>☑️</button>
          </div>
        </>
      )}
    </div>
  ));
}
