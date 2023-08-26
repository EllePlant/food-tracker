import { Link, useSearchParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { AgGridReact } from 'ag-grid-react';
import "ag-grid-community/styles/ag-grid.css"
import "ag-grid-community/styles/ag-theme-balham.css"
import BarChart  from '../components/BarChart';
import PersonData from '../APIcalls/PersonData';
import { useAPI } from '../APIcalls/GlobalStates';
import { Container } from 'reactstrap';

function makeChart(person, setChartData){
    const chartData = {
      labels: ["0-1", "1-2", "2-3", "3-4", "4-5", "5-6", "6-7", "7-8", "8-9", "9-10"],
      datasets: [
        {
          label: "IMDb Rating",
          data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          backgroundColor: "rgba(75,192,192,0.4)"
        },
      ]
    };
    const roles = person.roles;
    for (let i = 0; i < roles.length; i++) {
      const rating = roles[i].imdbRating;
      if (rating >= 0 && rating <= 10) {
        const index = Math.floor(rating);
        chartData.datasets[0].data[index]++;
      }
    }

    return (setChartData(chartData));
  }
  
  
export default function Person(){
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const imdbID = searchParams.get("key");
    const [person, setPerson] = useState({});
    const { data } = useAPI();
    const [chartData, setChartData] = useState({})


  /* Create person list */
  const columns = [
    { headerName: "Movie", field: "movieName", minWidth:200 },
    { headerName: "Role", field: "category" },
    { headerName: "Characters", field: "characters"},
    { headerName: "Rating", field: "imdbRating"}
  ];

  const gridOptions = {
    defaultColDef: {
        resizable: true,
        suppressSizeToFit: true,
        sortable:true
        
    },
  }


  /* Get person details through API */
  useEffect(() => {
    PersonData(imdbID, setPerson, data);
  }, [imdbID, data]);

  /* Update chart data */
  useEffect(() => {
    if (person.roles) {
      makeChart(person, setChartData);
    }
  }, [person]);

  
  return (
    <Container>
    {person.name ?  (
    <div className="page">
    <section className="features_box">
    <div className="page_title"><h2>{person.name}</h2></div>
    <div className="page_subtitle">{person.birthYear ? person.birthYear : "birth"}-{person.deathYear ? person.deathYear : "present"}</div>
    <div className="features">
    <div
      className="ag-theme-material ag-theme-cinematica"
      id="personLibrary"
      style={{ height: "20vw",
      paddingBottom: "2vw", 
      paddingRight:"2vw"}}
    >
    <AgGridReact
    columnDefs={columns}
    rowData={person.roles}
    pagination={false}
    paginationPageSize={7}
    onRowClicked={row => 
      navigate(`/movies/details?key=${row.data.movieId}`) 
    } 
    gridOptions={gridOptions}
    />
    </div>
    <div>
    {chartData.labels && <BarChart chartData={chartData} />}
    </div>
    </div>
    </section>
    </div>
  ) : (
    
    <div className="page">
    <section className="features_box">
    <div className="features">
    This content is for authorised users only. Please <Link to={"/login"}>login</Link> to access.
    </div>
    </section>
    </div>
    
  )}

  </Container>
  )
}