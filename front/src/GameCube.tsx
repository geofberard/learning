import React, {useState, useEffect} from "react";
import "./GamePuzzle.css"
import CubeContainer from "./cube/components/CubeContainer";

interface GameCubeProps {
    onDone: () => void;
}

const GameCube: React.FC<GameCubeProps> = ({onDone}) => {
    const [buttonVisible, setButtonVisible] = useState(false);

    useEffect(() => {
        const timer = setTimeout(() => {
            setButtonVisible(true);
        }, 30000); // 20 seconds in milliseconds

        return () => clearTimeout(timer);
    }, []);

    return (
        <div className="game-cube-container" style={{position: "relative"}}>
            <div className="game-1-text" style={{position: "absolute", top: -220, left: "50%" , transform: "translate(-50%, 50%)"}}>Shuffle and Solve</div>
            <CubeContainer/>
            {buttonVisible && (
                <button onClick={() => onDone()} style={{position: "fixed", top: 0, transform: "translate(-50%, 50%)"}}>
                    Non, je déconne, clique ici pour continuer 😉
                </button>
            )}
        </div>
    );
}

export default GameCube;
