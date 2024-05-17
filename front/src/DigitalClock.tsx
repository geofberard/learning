import React, {useState, useEffect} from 'react';
import './DigitalClock.css';

const isCurrentTimePalindrome = (time: Date): boolean => {
    const hours = time.getHours().toString().padStart(2, '0');
    const minutes = time.getMinutes().toString().padStart(2, '0');
    const seconds = time.getSeconds().toString().padStart(2, '0');

    const timeString = `${hours}${minutes}${seconds}`;
    const reversedTimeString = timeString.split('').reverse().join('');

    return timeString === reversedTimeString;
};

interface DigitalClockProps {
    onDone: () => void;
}

const DigitalClock: React.FC<DigitalClockProps> = ({onDone}) => {
    const [time, setTime] = useState<Date>(new Date());

    useEffect(() => {
        const interval = setInterval(() => {
            setTime(new Date());
        }, 1000);

        return () => clearInterval(interval);
    }, []);

    return (
        <>
            <div className="digital-clock">
                <p className="value">{time.getHours().toString().padStart(2, '0')}</p>
                <p className="separator">:</p>
                <p className="value">{time.getMinutes().toString().padStart(2, '0')}</p>
                <p className="separator">:</p>
                <p className="value">{time.getSeconds().toString().padStart(2, '0')}</p>
            </div>
            {isCurrentTimePalindrome(time) && (
                <img className="game-2-tinkerbell" onClick={() => onDone()}  src="./tink.png" alt="tink"/>
            )}
        </>
    );
};

export default DigitalClock;
