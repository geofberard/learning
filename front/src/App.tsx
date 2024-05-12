import React from 'react';
import './App.css';
import SelfTimer from "./SelfTimer";
import Game from "./Game";

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <div className="App-fallback">
                    <SelfTimer triggerDate={new Date(2024, 4, 12, 18, 30, 0)}>
                        <Game />
                    </SelfTimer>
                </div>
            </header>
        </div>
    );
}

export default App;
