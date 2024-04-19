import React from 'react';
import './App.css';
import ImageLoader from "./ImageLoader";
import Stickynote from "./Stickynote";

function App() {
    const queryString = window.location.search;
    const displayHelp = queryString === "?tinkerbell"

    return (
        <div className="App">
            <header className="App-header">
                <ImageLoader src={"./investigation.jpg"}/>
                {displayHelp && <Stickynote text={"Pas de double-tap sur le phare gauche !!!"} author="gbe"/>}
            </header>
        </div>
    );
}

export default App;
