import React, {useState} from "react";
import './GameLogin.css';
import GameLoginHelp from "./GameLoginHelp";

interface GameLoginProps {
    onDone: () => void;
}

const GameLogin: React.FC<GameLoginProps> = ({onDone}) => {
    const [value, setValue] = useState("");
    const [displayHelp, setDisplayHelp] = useState(false)

    const onClick = () => {
        if (value === "Danger") {
            onDone();
        }
    }


    return (
        <div className="game-1-container">
            <div className="game-1-text">Ah Ah Ah, you didn't say the magic word !</div>
            <img className="game-1-nedry" src="./nedry.png" alt="tink"/>
            <input className="game-1-code"
                   type="text"
                   value={value}
                   onChange={event => setValue(event.target.value)}/>
            <button onClick={onClick}>Ca ne peut être que ca !</button>
            <div className="game-1-help" onClick={() => setDisplayHelp(true)}>Help !!!!</div>
            {displayHelp && (
                <div className="game-1-help-container">
                    <GameLoginHelp/>
                    <button onClick={() => setDisplayHelp(false)}>Stop</button>
                </div>
            )}
        </div>
    );
}

export default GameLogin;
