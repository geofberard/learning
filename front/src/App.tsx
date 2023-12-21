import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {fetchText} from "./service/PromiseService";

function App() {
  const [apiResult , setAPIResult] = useState("")

  const callApi = () => {
    try{
      fetchText("/api/")
          .then(setAPIResult);
    } catch (err) {
        console.log(err);
    }
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <button onClick={callApi}>Api Call</button>
        <div className="App-result">{apiResult}</div>
      </header>
    </div>
  );
}

export default App;
