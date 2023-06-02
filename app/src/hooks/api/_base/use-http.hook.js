import { useGlobalUser } from "../../../contexts/user.context";
import { useAxios } from "./use-axios.hook";

export function useHttp(baseURL, headers) {
  const instance = useAxios(baseURL, headers);
  const [, setUser] = useGlobalUser();

  async function get(url, headers) {
    try {
      const response = await instance.get(url, headers);
      return response;
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
        throw error;
      }
    }
  }
  const post = async (url, data, headers) => {
    return await instance.post(url, data, headers);
  };

  const put = async (url, data, headers) => {
    return await instance.put(url, data, headers);
  };

  const deleteReq = async (url, data, headers) => {
    return await instance.delete(url, data, headers);
  };

  async function request(config) {
    return await instance.request(config);
  }


  return {
    deleteReq,
    get,
    post,
    put,
    request,
  };
}
