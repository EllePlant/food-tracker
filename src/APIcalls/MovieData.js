import { useSearchParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { AgGridReact } from 'ag-grid-react';
import "ag-grid-community/styles/ag-grid.css"
import "ag-grid-community/styles/ag-theme-balham.css"
import { API_URL } from './GlobalStates';
import { Container, Col, Row } from 'reactstrap';


function openTab(tabName, elmnt, color) {
  // Hide all elements with class="tabcontent" by default */
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Remove the background color of all tablinks/buttons
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }

  // // Show the specific tab content

  var element = document.getElementById(tabName);
  if (element) {
    element.style.display = "block";
    elmnt.style.backgroundColor = color;
  }

  
}

export function MovieData(){
  const navigate = useNavigate();

  const gridOptions = {
    defaultColDef: {
        resizable: true,
        suppressSizeToFit: true,
        minWidth:192,
        sortable:true
    },
  }

  /* Create actor list */
  const columns = [
    { headerName: "Role", field: "category"},
    { headerName: "Name", field: "name" },
    { headerName: "Characters", field: "characters"}
  ];




  
  
  // Get the element with id="defaultOpen" and click on it
  useEffect(() => {
    document.getElementById("defaultOpen").click();
  }, []);

  


  /* Get movie details through API */
  const [searchParams] = useSearchParams();
  const key = searchParams.get("key");
  const [movie, setMovie] = useState({});
  const [error, setError] = useState('')

  useEffect(() => {
      fetch(`${API_URL}/movies/data/${key}`)
      .then((res) =>
      res.json().then((res) => {
        if(res.error){
          setError(res)
        } else {
        setMovie(res);
        }
      }) 
    )
  }, [key])

  

  if(error.error){
    return (
    <div>
      <h1>Something went wrong</h1>
      <p>{error.message}</p>
    </div>
    )
  } else {
    /* Extract individual ratings */
    const imdbRating = movie.ratings && movie.ratings.find(r => r.source === 'Internet Movie Database');
    const rottenRating = movie.ratings && movie.ratings.find(r => r.source === 'Rotten Tomatoes');
    const metaRating = movie.ratings && movie.ratings.find(r => r.source === 'Metacritic');

    return (
      <Container>
      <div className="page">
      <section className="features_box">
      <div className="page_title"><h2>{movie.title}</h2></div>
      <div className="features">
        <Row>
          <Col sm={4}>
          <img className='features__box' src={movie.poster} alt="Movie poster" />
          </Col>
          <Col sm={8}>
          
          <div className='tab_box'>
          <button className="tablink" onClick={(event) => openTab('Details', event.target, '#d4c295')} id="defaultOpen">Details</button>
          <button className="tablink" onClick={(event) => openTab('Ratings', event.target, '#d4c295')}>Ratings</button>
          <button className="tablink" onClick={(event) => openTab('People', event.target, '#d4c295')}>People</button>
            <div id="Details" className="tabcontent">
              <p><strong>Released in: </strong>{movie.year}</p>
              <p><strong>Genres: </strong>{movie.genres && (movie.genres.join(", "))}</p>
              <p><strong>Country: </strong>{movie.country}</p>
              <p><strong>Box Office: </strong>{movie.boxoffice && (`$${movie.boxoffice.toLocaleString()}`)}</p>
              <p>{movie.plot}</p>
            </div>
            <div id="Ratings" className="tabcontent">
              <p><strong>IMdB: </strong>
                {imdbRating && (imdbRating.value ? imdbRating.value + "/10" : 'N/A')}</p>
              <p><strong>Rotten Tomatoes: </strong>
                {rottenRating && (rottenRating.value ? rottenRating.value + "%" : 'N/A')}</p>
              <p><strong>Metacritic: </strong>
                {metaRating && (metaRating.value ? metaRating.value + "/100" : 'N/A')}</p>
            </div>
            <div id="People" className="tabcontent">

            <div
                className="ag-theme-material ag-theme-people"
                id="peopleLibrary"
                style={{ height: "19vw", maxWidth:"39vw"}}
                >
              <AgGridReact
                columnDefs={columns}
                rowData={movie.principals}
                pagination={false}
                paginationPageSize={7}
                onRowClicked={row => navigate(`/people?key=${row.data.id}`) } 
                gridOptions={gridOptions}
                />
            </div>
            </div>
            </div>
            </Col>
            </Row>
          </div>
          </section>
          </div>
        </Container>
      );
  }
}


    