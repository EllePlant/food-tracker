// styling
//import "./styles.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";

// API calls
import { UserContextProvider } from "./APIcalls/GlobalStates";

// components
import Footer from "./components/Footer";
import Header from "./components/Header";
import { MovieData } from "./APIcalls/MovieData";


// pages
import Home from "./pages/Home";
import Movies from "./pages/Movies";
import Login from "./pages/Login";
import Person from "./pages/Person";

export default function App() {
  return (
    <UserContextProvider>
    <BrowserRouter>
      <div className="App">
        <Header />
          <Routes>
            <Route path="/" element={<Home />}/>
            <Route path="/movies" element={<Movies />}/>
            <Route path="/login" element={<Login />}/>
            <Route path="/movies/details" element={<MovieData />} />
            <Route path="/people" element={<Person />} />
          </Routes>
        <Footer />
      </div>
    </BrowserRouter>
    </UserContextProvider>
  );
}
