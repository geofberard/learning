// src/App.tsx
import React from 'react';
import './App.css';
import './EventList.css';

enum EventState {
  hidden, blurred, visible
}

interface Event {
  time: string;
  description: string;
  suffix: string;
  state: EventState;
}

const events: Event[] = [
  { time: '10:00', description: 'Petit-déjeuner', suffix: '', state: EventState.hidden },
  { time: '12:00', description: 'Déjeuner gastronomique : ', suffix: 'sdfsdf', state: EventState.blurred }, // + : "bien tenté flouté"
  { time: '14:00', description: 'Après-midi shopping VIP (Hermes, Dior, Channel)', suffix: '', state: EventState.visible },
  { time: '17:00', description: 'Tea time : Hotel Meurice', suffix: '', state: EventState.visible },
  { time: '20:00', description: 'Tea time : Hotel Meurice', suffix: '', state: EventState.hidden },
];

const App: React.FC = () => {
  return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Une Journée VIP à Paris</h1>
        </header>
        <main className="App-main">
          <div className="Card">
            <h2 className="EventDate">Date : <span className="blurred">Une DateEn</span> 2025</h2>
            <ul className="EventList">
              {events.map((event, index) => (
                  <li key={index} className="EventItem">
                    <span className="EventTime">{event.time}</span>
                      <span className="EventDescription">
                          {event.state === EventState.hidden
                              ? <span className="ToBeDefined"> À découvrir... </span>
                              : (
                                  <>
                                      <span>{event.description}</span>
                                      <span className={event.state === EventState.blurred ? 'blurred' : ''}>
                                    {event.suffix}
                                </span>
                                  </>
                              )
                          }
                      </span>
                  </li>
              ))}
            </ul>
          </div>
        </main>
      </div>
  );
};

export default App;
