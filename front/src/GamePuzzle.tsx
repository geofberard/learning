import React, {useState} from "react";

interface GameLoginProps {
    onDone: () => void;
}

const GamePuzzle: React.FC<GameLoginProps> = ({onDone}) => {
    const [value, setValue] = useState("");

    const onClick = () => {
        if (value === "Danger") {
            onDone();
        }
    }

    return (
        <div className="game-2-container">
            <div className="game-2-text">Ah Ah Ah, you didn't say the magic word !</div>
            <img className="game-2-nedry" src="./nedry.png" alt="tink"/>
        </div>
    );
}

export default GamePuzzle;
