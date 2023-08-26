import { API_URL } from "./GlobalStates";
import LoginUser from "./LoginUser";

export default function RegisterUser(email, password, setResMsg, updateData, navigate) {
  const url = `${API_URL}/user/register`;
 
  return fetch(url, {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
   headers: {
     "Content-Type": "application/json",
   },
   body: JSON.stringify({  email: email, password: password }),
 })
 .then((res) =>
  res.json().then((res) => {
    setResMsg(res.message)
    if(!res.error){
      LoginUser(email, password, setResMsg, updateData, navigate);
    }
  }))  
};

