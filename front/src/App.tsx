import React from 'react';
import './App.css';
import CountdownTimer from "./CountdownTimer";
import Stickynote from "./Stickynote";

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <Stickynote>
                    <div className="App-title">Tooooooo Soooooooon</div>
                    <img src="stars.jpg" className="App-logo" alt="logo"/>
                    <div className="App-subtitle">Je sais que les stars ne savent pas attendre ...</div>
                    <div className="App-subtitle">Mais il va falloir patienter</div>
                    <CountdownTimer targetDate={new Date(2024, 4, 19, 12, 0, 0)}/>
                </Stickynote>
            </header>
        </div>
    );
}

export default App;
