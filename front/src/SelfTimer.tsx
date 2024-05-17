import React, {PropsWithChildren, useEffect, useState} from "react";
import CountdownTimer from "./CountdownTimer";

interface SelfTimerProps {
    triggerDate: Date;
}

const SelfTimer: React.FC<PropsWithChildren<SelfTimerProps>> = ({children, triggerDate}) => {
    const [isTriggered, setIsTriggered] = useState(triggerDate < new Date());

    useEffect(() => {
        const interval = setInterval(() => {
            const difference = + new Date(triggerDate) - + new Date();
            console.log("Evaluating", difference)
            if(!isTriggered && difference <= 0) {
                setIsTriggered(true);
                clearInterval(interval);
            }
        }, 1000);
        return () => {
            clearInterval(interval);
        };
    }, []);

    console.log("Refreshing", isTriggered)

    return isTriggered ? <>{children}</> : (
        <div className="loader-container">
            <div className="loader-text">Patience, elle arrive ...</div>
            <CountdownTimer targetDate={triggerDate}/>
            <img className="loader-img" src="./tink.png" alt="tink"/>
        </div>
    );
}

export default SelfTimer;
