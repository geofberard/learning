import React, {useState} from "react";
import './GameLogin.css';
import GameLoginHelp from "./GameLoginHelp";

interface GameLoginProps {
    onDone: () => void;
}

const GameLogin: React.FC<GameLoginProps> = ({onDone}) => {
    const [value, setValue] = useState("");
    const [displayHelp, setDisplayHelp] = useState(false)
    const [isError, setError] = useState(false)
    const [isOldPassword, setOldPassword] = useState(false)

    const onClick = () => {
        setOldPassword(value === "Danger");

        if (value === "not yet") {
            onDone();
        } else {
            setError(true);
        }
    }


    return (
        <div className="game-1-container">
            <div className="game-1-text">Ah Ah Ah, you didn't say the magic word !</div>
            <img className="game-1-nedry" src="./nedry.png" alt="tink"/>
            <input className={`game-1-code ${isError ? "error" : ""}`}
                   type="text"
                   value={value}
                   onChange={event => setValue(event.target.value)}/>
            <button onClick={onClick}>Ca ne peut être que ca !</button>
            <div className="error-message">
                {isOldPassword ? "Le mot de passe a changer ... mais pas la page 😉" : ""}
            </div>
            <div className="game-1-help" onClick={() => setDisplayHelp(true)}>Help !!!!</div>
            {displayHelp && (
                <div className="game-1-help-container">
                    <GameLoginHelp/>
                    <button onClick={() => setDisplayHelp(false)}>I'm done !!!</button>
                </div>
            )}
        </div>
    );
}

export default GameLogin;
