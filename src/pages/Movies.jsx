import React from "react";
import MovieLibrary from "../APIcalls/MovieLibrary";
import { useState } from 'react';
import Select from 'react-select';
import { Container, Input } from "reactstrap";
import "../styles.css";


export default function Movies() {
    /* Get search criteria */
    const inputProps = useInput("");
    const [error, setError] = useState('');

    /* Create list of reasonable years */
    const currYear = (new Date()).getFullYear();
    const years = [];
    for (let year = currYear; year >= 1990; year--) {
      years.push({ value: year.toString(), label: year.toString() });
    }

    /* Get selected value */
    const [selectedYear, setSelectedYear] = useState('');
    const handleYearChange = (selectedOption) => {
      setSelectedYear(selectedOption);
    };
    
  if(error){ 
    return <div><h1>Something went wrong</h1><p>{error.message}</p></div> 
  }else
  return(
    <Container>
    <div className="page">
      <section className="features_box" style={{ height: "39vw" }}>
      <h1 className="page_title">Movies</h1>
      <div className="features">
      <div className="input-container">
        <span className="input_labels">Title:</span>
          <Input {...inputProps} 
          placeholder="Type in here"
          className="search-input" />
        <span className="input_labels">Year:</span>
          <Select
          className="number-input"
          value={selectedYear}
          onChange={handleYearChange}
          options={years}
          isClearable={true}
          isSearchable={true}
        />
      </div>
        <MovieLibrary 
          title={inputProps.value} 
          year={selectedYear ? selectedYear.label : ''} 
          setError={setError}/>     
      </div>
    </section>
    </div>
    </Container>
  ) ;
}

/* Get typed value in title search box */
function useInput(defaultValue) {
  const [value, setValue] = useState(defaultValue);
  function onChange(e) {
    setValue(e.target.value);
  }
  return {
    value,
    onChange,
  };
}