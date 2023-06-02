import { useMemo } from "react";
import { EMPLOYEE_URL, URL_BASE, USUARIO_URL } from "../../../constant/constants";
import { useGlobalUser } from "../../../contexts/user.context";
import { useHttp } from "../_base/use-http.hook";

export function useEmployeeManagerApi() {
  const [user] = useGlobalUser();
  const httpInstanceUser = useHttp(URL_BASE + USUARIO_URL);
  const httpInstanceEmployeeToken = useHttp(URL_BASE + EMPLOYEE_URL, { "x-auth-token": user.token });

  const getLogin = async ({ email, password }) => await httpInstanceUser.post("/login", {}, { auth: { username: email, password: password } });


  const createNewUser = async (newUserRequest) => await httpInstanceUser.post("/new", newUserRequest)


  const createNewEmployee = async (newEmployeeRequest) => await httpInstanceEmployeeToken.post("/new", newEmployeeRequest)


  const updateEmployee = async (updateRequest) => await httpInstanceEmployeeToken.put("", updateRequest)


  const getAllEmployeesFromUser = async (page) => await httpInstanceEmployeeToken.get(`/page/${page}`)


  const getEmployeeByIdFromUser = async (id) => await httpInstanceEmployeeToken.get(`/${id}`)


  const deleteEmployeeByIdFromUser = async (id) => await httpInstanceEmployeeToken.deleteReq(`/${id}`)



  return useMemo(
    () => ({
      getLogin,
      createNewUser,
      createNewEmployee,
      updateEmployee,
      getAllEmployeesFromUser,
      getEmployeeByIdFromUser,
      deleteEmployeeByIdFromUser
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
}
