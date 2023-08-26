import { Link } from "react-router-dom"
import { useAPI } from "../APIcalls/GlobalStates";
import Logout from "../APIcalls/Logout";
import { useState, useEffect } from "react";



// navigation links
export default function Nav() {
  const { data, updateData } = useAPI();
  const [isLoggedIn, setIsLoggedIn] = useState(!!data.email);
  const [error, setError] = useState("");

  // Update the isLoggedIn state whenever the user data changes
  useEffect(() => {
    setIsLoggedIn(!!data.email);
  }, [data]);


  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/movies">Movies</Link></li>
        {!isLoggedIn ? (
          <>
            <li><Link to="/login">Login</Link></li>
          </>
        ) : (
          <>
          <li><button 
          id="logout" 
          onClick={() =>Logout(data, setError, updateData)}
          className="nav-link-button">
            Logout
            </button></li>
          <li>Welcome {data.email}</li>
          {error}
          </>
        )}
      </ul>
    </nav>
  );
}


