import React, {useEffect, useState} from "react";
import "./GameQuizz.css"
import {getCookie, setCookie} from "./service/CookieService";
import {compareStrings} from "./service/StringService";
import {Question, QUESTIONS} from "./data/questions";


interface GameLoginProps {
    onDone: () => void;
}

const findQuestionById = (id: string) => QUESTIONS.find(question => question.id === id)

const findIndexById = (id: string) => QUESTIONS.findIndex(question => question.id === id)

const API_URL = "https://script.google.com/macros/s/AKfycbyRmtD7qfsSj1UHvZn11UGa0XxiQAcWy6sbHr1XfvDRbTCl2qQaHmt54T2wMuv7JgU/exec";

const GameQuizz: React.FC<GameLoginProps> = ({onDone}) => {
    const [question, setQuestion] = useState<Question>(findQuestionById(getCookie() || "") || QUESTIONS[0]);
    const [value, setValue] = useState("");
    const [isError, setError] = useState(false)

    useEffect(() => {
        setValue("")
        setError(false);
        setCookie(question.id);
    }, [question])

    const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            fetch(`${API_URL}?id=${question.id}&attempt=${value}`,{method: 'GET'})
            if (question.answers.find(answer => compareStrings(answer, value, question.tolerance))) {
                const currentIndex = findIndexById(question.id)
                if(currentIndex + 1 === QUESTIONS.length) {
                    onDone()
                }
                setQuestion(QUESTIONS[currentIndex + 1])
            } else {
                setError(true);
            }
        }
    }

    return (
        <div className="game-2-container">
            <img className="game-2-clue" src={question.image} alt={question.id}/>
            <div className="game-2-form">
                <input className={`game-2-input ${isError && "error"}`}
                       autoFocus
                       type="text"
                       value={value}
                       onKeyDown={handleKeyDown}
                       onChange={event => setValue(event.target.value)}/>
                <div>{question.language}</div>
            </div>
        </div>
    );
}

export default GameQuizz;
