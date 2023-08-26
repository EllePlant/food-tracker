import "ag-grid-community/styles/ag-grid.css"
import "ag-grid-community/styles/ag-theme-balham.css"
import Chart from "chart.js/auto";
import { CategoryScale } from "chart.js";
import { API_URL } from "./GlobalStates";

Chart.register(CategoryScale);


export default function PersonData(imdbID, setPerson, data){
    const url = `${API_URL}/people/${imdbID}`;
    
    if(data.bearerToken){
        return fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${data.bearerToken}`
        },
    })
        .then((res) => res.json().then(person => setPerson(person)))
    }
};




