import React, {useState} from 'react';
import './App.css';
import SelfTimer from "./SelfTimer";
import GameLogin from "./GameLogin";
import TheEnd from "./TheEnd";
import GameQuizz from "./GameQuizz";

enum Stages {
    LOGIN, QUIZZ, DONE
}

function App() {
    const [state, setState] = useState(Stages.LOGIN);

    return (
        <div className="app-container">
            <SelfTimer triggerDate={new Date(2024, 5, 18, 20, 15, 0)}>
                {(state === Stages.LOGIN) && <GameLogin onDone={() => setState(Stages.QUIZZ)}/>}
                {(state === Stages.QUIZZ) && <GameQuizz onDone={() => setState(Stages.DONE)}/>}
                {(state === Stages.DONE) && <TheEnd/>}
            </SelfTimer>
        </div>
    );
}

export default App;
