import React, {useState} from "react";
import "./GameQuizz.css"
import {getCookie, setCookie} from "./service/CookieService";


interface GameLoginProps {
    onDone: () => void;
}

interface Question {
    id: string,
    answers: string[],
    language: string
}

const QUESTIONS: Question[] = [
    {id: "Step1", answers: ["aaaaa"], language: "🇫🇷"},
    {id: "Step2", answers: ["bbbbb"], language: "🇺🇸"},
]

const findQuestionById = (id: string) => QUESTIONS.find(question => question.id === id)


const GameQuizz: React.FC<GameLoginProps> = ({onDone}) => {
    const [question, setQuestion] = useState<Question>(findQuestionById(getCookie() || "") || QUESTIONS[0]);
    const [value, setValue] = useState("");

    const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            if (question.answers.find(answer => answer === value)) {
                setQuestion(QUESTIONS[1])
                setCookie(QUESTIONS[1].id)
            }
        }
    }

    return (
        <div className="game-2-container">
            <p>{question.id}</p>
            <input className="game-1-code"
                   autoFocus
                   type="text"
                   value={value}
                   onKeyDown={handleKeyDown}
                   onChange={event => setValue(event.target.value)}/>
            <p>{question.language}</p>
        </div>
    );
}

export default GameQuizz;
