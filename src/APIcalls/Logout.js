import { API_URL} from "./GlobalStates";
import { initData } from "./GlobalStates";



export default function Logout(data, setError, updateData) {
  const url = `${API_URL}/user/logout`;
 
  return fetch(url, {
    method: "POST", 
   headers: {
     "Content-Type": "application/json",
   },
   body: JSON.stringify({ refreshToken:data.refreshToken }),
 })
   .then((res) =>
     res.json().then((res) => {
      if(res.error){
        setError(res)
      } else {
        updateData(initData);
      }})
   )
};


