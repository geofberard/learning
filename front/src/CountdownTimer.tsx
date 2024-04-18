import React, { useEffect, useState } from 'react';

interface CountdownProps {
  targetDate: Date;
}

interface TimeLeft {
  hours: number;
  minutes: number;
  seconds: number;
}

const CountdownTimer: React.FC<CountdownProps> = ({ targetDate }) => {
  const calculateTimeLeft: () => TimeLeft = () => {
    const difference = +new Date(targetDate) - +new Date();
    let timeLeft = {
      hours: 0,
      minutes: 0,
      seconds: 0,
    };

    if (difference > 0) {
      timeLeft = {
        hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
        minutes: Math.floor((difference / 1000 / 60) % 60),
        seconds: Math.floor((difference / 1000) % 60),
      };
    }

    return timeLeft;
  };

  const [timeLeft, setTimeLeft] = useState(calculateTimeLeft());

  useEffect(() => {
    const timer = setTimeout(() => {
      setTimeLeft(calculateTimeLeft());
    }, 1000);

    return () => clearTimeout(timer);
  });

  return (
      <div className="countdown-container">
        <div className="countdown-item">
          <span className="countdown-value">{timeLeft.hours.toString().padStart(2, '0')}</span>
          <span className="countdown-label">Heures</span>
        </div>
        <div className="countdown-item">
          <span className="countdown-value">{timeLeft.minutes.toString().padStart(2, '0')}</span>
          <span className="countdown-label">Minutes</span>
        </div>
        <div className="countdown-item">
          <span className="countdown-value">{timeLeft.seconds.toString().padStart(2, '0')}</span>
          <span className="countdown-label">Secondes</span>
        </div>
      </div>
  );
};

export default CountdownTimer;
