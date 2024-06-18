import React, {useEffect, useState} from "react";
import "./GameQuizz.css"
import {getCookie, setCookie} from "./service/CookieService";
import {compareStrings} from "./service/StringService";


interface GameLoginProps {
    onDone: () => void;
}

interface Question {
    id: string,
    answers: string[],
    tolerance: number,
    language: string
}

const QUESTIONS: Question[] = [
    {id: "Step1", answers: ["Dites à mes amis que je m'en vais"], tolerance: 5,  language: "🇫🇷"},
    {id: "Step2", answers: ["Dodgson ! Dodgson est parmi nous !", "Dodgson ! Dodgson est parmi nous ! Dodgson est là !", "Dodgson est là !"], tolerance: 5,  language: "🇫🇷"},
    {id: "Step3", answers: ["C'est Levioooosa, pas Leviosaaa"], tolerance: 7,  language: "🇫🇷"},
    {id: "Step4", answers: ["Yippee ki-yay, pauvre con"], tolerance: 5,  language: "🇫🇷"},
    {id: "Step5", answers: ["La vie, c'est comme une boîte de chocolats, on ne sait jamais sur quoi on va tomber."], tolerance: 8,  language: "🇺🇸"},
//    {id: "Step6", answers: ["Nom de Zeus !"], tolerance: 2,  language: "🇫🇷"},
//    {id: "Step7", answers: ["Vers l'infini et au-delà !"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step8", answers: ["Sa place est dans un musée !"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step9", answers: ["Tu étais l'élu ! C'était toi !"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step10", answers: ["Mon précieux..."], tolerance: 2,  language: "🇫🇷"},
//    {id: "Step11", answers: ["Et les romains, vous êtes des romaines"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step12", answers: ["On a les droits"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step13", answers: ["Madame Gaston, non mais quelle idée"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step14", answers: ["Longue... vie... au roi !!"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step15", answers: ["Quel danger ? Moi j'aime le danger ! Je me ris du danger !", "Je me ris du danger"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step16", answers: ["C'est la fête! C'est la fête!", "C'est la fête! C'est la fête!", "C'est la fête! C'est la fête! Service Guaranti impec"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step17", answers: ["Here's Johnny!"], tolerance: 5,  language: "🇺🇸"},
//    {id: "Step18", answers: ["Supercalifragilisticexpialidocious !"], tolerance: 5,  language: "🇫🇷/🇺🇸"},
//    {id: "Step19", answers: ["Kevin !"], tolerance: 2,  language: "🇫🇷/🇺🇸"},
//    {id: "Step20", answers: ["Mister Anderson..."], tolerance: 5,  language: "🇺🇸"},
//    {id: "Step21", answers: ["Non, je ne pourrai jamais vivre sans toi"], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step22", answers: ["La vie trouve toujours un chemin."], tolerance: 5,  language: "🇫🇷"},
//    {id: "Step23", answers: ["Welcome to Jurassic Park."], tolerance: 5,  language: "🇺🇸"},
//    {id: "Step24", answers: ["Pivot!"], tolerance: 2,  language: "🇺🇸"},
//    {id: "Step25", answers: ["Troy and abed in the mooooorning !"], tolerance: 6,  language: "🇺🇸"},
//    {id: "Step26", answers: ["No god please no", "Nooooooo"], tolerance: 3,  language: "🇺🇸"},
//    {id: "Step27", answers: ["Say my name !"], tolerance: 2,  language: "🇺🇸"},
//    {id: "Step28", answers: ["Exterminate !"], tolerance: 2,  language: "🇺🇸"},
//    {id: "Step29", answers: ["I don't wanna go !"], tolerance: 5,  language: "🇺🇸"},
//    {id: "Step30", answers: ["Un diamant d'innocence !"], tolerance: 5,  language: "🇫🇷"},
]

const findQuestionById = (id: string) => QUESTIONS.find(question => question.id === id)

const findIndexById = (id: string) => QUESTIONS.findIndex(question => question.id === id)


const GameQuizz: React.FC<GameLoginProps> = ({onDone}) => {
    const [question, setQuestion] = useState<Question>(findQuestionById(getCookie() || "") || QUESTIONS[0]);
    const [value, setValue] = useState("");

    useEffect(() => {
        setValue("")
        setCookie(question.id);
    }, [question])

    const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            if (question.answers.find(answer => compareStrings(answer, value, question.tolerance))) {
                const currentIndex = findIndexById(question.id)
                if(currentIndex + 1 === QUESTIONS.length) {
                    onDone()
                }
                setQuestion(QUESTIONS[currentIndex + 1])
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
