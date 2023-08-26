import { createContext, useState } from "react";
import { useContext } from "react";

export const initData = {
    email:"",
    bearerToken: "",
    refreshToken: ""
};


export const UserContext = createContext();

export function UserContextProvider({ children }) {
    // Initialize state
    const [data, setData] = useState(initData);

    const updateData = (newData) => {
        setData(newData);
      };
  
    return (
      <UserContext.Provider value={{ data, updateData }}>
        {children}
      </UserContext.Provider>
    );
}

export function useAPI() {
    const context = useContext(UserContext);
    if (context === undefined) {
      throw new Error("Context must be used within a Provider");
    }
    return context;
  }
  
  
  
  export const API_URL = `http://sefdb02.qut.edu.au:3000`;
  