import { useState, useEffect } from 'react';
import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/styles/ag-grid.css"
import "ag-grid-community/styles/ag-theme-material.css"
import { useNavigate } from "react-router-dom";
import { API_URL } from "./GlobalStates";
import "../styles.css";

function getMovies(callurl, setError, setRowData){
  return fetch(callurl)
  .then((response) => response.json())
  .then((movieData) => {
    if (movieData.error) {
      setError(movieData);
      console.log(movieData);
    } else {
      const movieArray = movieData.data.map((movie) => {
        return {
          title: movie.title,
          year: movie.year,
          imdbID: movie.imdbID,
          imdbRating: movie.imdbRating,
          rottenTomatoesRating: movie.rottenTomatoesRating,
          metacriticRating: movie.metacriticRating,
          classification: movie.classification,
        };
      });
      setRowData(movieArray);
    }
  });
}


export default function MovieLibrary({title, year, setError}) {
    /* Create table of results */
    const [rowData, setRowData] = useState([]);

    const gridOptions = {
      defaultColDef: {
          resizable: true,
          suppressSizeToFit: true,
          maxWidth:145,
          sortable:true
      },
    }

    const columns = [
        { headerName: "Title", field: "title", maxWidth:null},
        { headerName: "Year", field: "year", maxWidth:100},
        { headerName: "IMDB rating", field: "imdbRating", maxWidth:120 },
        { headerName: "RottenTomatoes", field: "rottenTomatoesRating"},
        { headerName: "Metacritic", field: "metacriticRating", maxWidth:120},
        { headerName: "Classification", field: "classification"},
    ];




    /* Determine API call */
    let callurl = `${API_URL}/movies/search`;
    const params = new URLSearchParams();
    if (title) {
      params.append("title", title);
    }
    if (year) {
      params.append("year", year);
    }
    if(title || year){
      callurl += "?" + params.toString();
    }


    useEffect(() => {
      getMovies(callurl, setError, setRowData)
    }, [callurl, setError]);

      const navigate = useNavigate();

    return (
        <div>
            <div
            className="ag-theme-material ag-theme-cinematica"
            id="movieLibrary"
            style={{ height: "30vw",
              width:"120%",
            }}
            >
            <AgGridReact
            columnDefs={columns}
            rowData={rowData}
            pagination={false}
            paginationPageSize={7}
            onRowClicked={row => navigate(`/movies/details?key=${row.data.imdbID}`) }
            gridOptions={gridOptions}
            />
            </div>
        </div>
         
    );
}




