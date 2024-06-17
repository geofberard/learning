import React, {useState} from 'react';
import './App.css';
import SelfTimer from "./SelfTimer";
import GameLogin from "./GameLogin";
import GamePuzzle from "./GamePuzzle";
import TheEnd from "./TheEnd";
import GameQuizz from "./GameQuizz";

enum Stages {
    LOGIN, QUIZZ, DONE
}

function App() {
    const [state, setState] = useState(Stages.QUIZZ);

    return (
        <div className="app-container">
            <SelfTimer triggerDate={new Date(2024, 4, 18, 12, 0, 0)}>
                {(state === Stages.LOGIN) && <GameLogin onDone={() => setState(Stages.QUIZZ)}/>}
                {(state === Stages.QUIZZ) && <GameQuizz onDone={() => setState(Stages.DONE)}/>}
                {(state === Stages.DONE) && <TheEnd/>}
            </SelfTimer>
        </div>
    );
}

export default App;
