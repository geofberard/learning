import React from "react";
import Stickynote from "./Stickynote";

const Game: React.FC = () => {
    const queryString = window.location.search;
    const displayHelp = queryString === "?tinkerbell"

    return (
        <div className="game-container">
            Hello World !
            {displayHelp && <Stickynote text={"Reviens plus tard ... 🤔"} author="gbe"/>}
        </div>
    );
}

export default Game;
