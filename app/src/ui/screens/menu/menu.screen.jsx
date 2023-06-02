import { useState, useEffect } from "react";
import { useEmployeeManagerApi } from "../../../hooks/api/employee-manager/use-employee-manager-api-hook";
import {
  NewEmployee,
  EmployeesFromUser,
  HeaderManager,
} from "../../components";
import { setToastWithTimout } from "../../../constant/functions";
import { X_TOAST } from "../../../constant/constants";

export function MenuScreen({ setToast }) {
  const { getAllEmployeesFromUser } = useEmployeeManagerApi();
  const [employeeList, setEmployeeList] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [pageLimit, setPageLimit] = useState(0);

  const getAllEmployeesFromUserPaged = async (page) => {
    try {
      const { data } = await getAllEmployeesFromUser(page);
      setPageNumber(data.number);
      setEmployeeList(data.content);
      setPageLimit(data.totalPages);
    } catch (e) {
      setToastWithTimout(setToast, e.response.data, X_TOAST);
    }
  };

  const handleLeftClick = async () => {
    if (pageNumber >= 1) {
      getAllEmployeesFromUserPaged(pageNumber - 1);
    }
  };

  const handleRightClick = async () => {
    if (pageNumber < pageLimit - 1) {
      getAllEmployeesFromUserPaged(pageNumber + 1);
    }
  };

  useEffect(() => () => getAllEmployeesFromUserApi(), []);

  const getAllEmployeesFromUserApi = async () => {
    try {
      const { data } = await getAllEmployeesFromUser(pageNumber);
      setPageLimit(data.totalPages);
      setEmployeeList(data.content);
    } catch (e) {
      setToastWithTimout(setToast, e.response.data.message, X_TOAST);
    }
  };

  return (
    <>
      <HeaderManager />
      <div className="menuContainer">
        <div className="subMenuContainer">
          <div className="fieldName mainFieldName">
            <p>Name</p>
            <p>Surname</p>
            <p>Email</p>
            <p>Pis</p>
          </div>
          <div className="buttonMenu mainFieldName">
            <p>Options</p>
          </div>
        </div>
        <NewEmployee
          getAllEmployeesFromUserApi={getAllEmployeesFromUserApi}
          setToast={setToast}
        />
        <EmployeesFromUser
          setToast={setToast}
          employeeList={employeeList}
          getAllEmployeesFromUserApi={getAllEmployeesFromUserApi}
        />
        <div className="pageController">
          <button onClick={() => handleLeftClick()}>←</button>
          <p>{pageNumber}</p>
          <button onClick={() => handleRightClick()}>→</button>
        </div>
      </div>
    </>
  );
}
