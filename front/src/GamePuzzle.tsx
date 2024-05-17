import React from "react";
import "./GamePuzzle.css"
import Frame from "./Frame";
import DigitalClock from "./DigitalClock";


interface GameLoginProps {
    onDone: () => void;
}

const GamePuzzle: React.FC<GameLoginProps> = ({onDone}) => {

    return (
        <div className="game-2-container">
            <div>Coming soon ... but when ? 🤔</div>
            <DigitalClock onDone={onDone}/>
            <Frame/>
        </div>
    );
}

export default GamePuzzle;
