import React, {useState} from 'react';
import './App.css';
import SelfTimer from "./SelfTimer";
import GameLogin from "./GameLogin";
import GamePuzzle from "./GamePuzzle";
import GameLoginHelp from "./GameLoginHelp";

enum Stages {
    LOGIN, PUZZLE, DONE
}

function App() {
    const [state, setState] = useState(Stages.LOGIN);

    return (
        <div className="app-container">
            <SelfTimer triggerDate={new Date(2024, 4, 18, 12, 0, 0)}>
                <div className={"mouais"}>
                    {(state === Stages.LOGIN) && <GameLogin onDone={() => setState(Stages.PUZZLE)}/>}
                    {(state === Stages.PUZZLE) && <GamePuzzle onDone={() => setState(Stages.DONE)}/>}
                </div>
            </SelfTimer>
        </div>
    );
}

export default App;
