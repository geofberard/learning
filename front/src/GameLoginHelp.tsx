import React from 'react';
import './GameLoginHelp.css';

const GameLoginHelp: React.FC = () => {
    return (
        <div className="game-login-help">
            <div className="game-login-help-img">
                <img src="login-help1.png" alt={"rules"}/>
                <img src="login-help2.png" alt={"rules"}/>
            </div>
            <div className="game-login-help-details">
                La règle est simple: <br/> Il faut utiliser la locomotive L pour échanger la position des wagons A et B
                !
            </div>
            <div className="game-login-help-details">
                Attention:<br/>
                La locomotive doit finir à son point de départ
                Les points 1,2 et 3 sont des aiguillages et non des virages.
            </div>
            <div className="game-login-help-details">
                Tu peux demander à Jéremie, il est aussi dessus 😉
            </div>
        </div>
    );
};

export default GameLoginHelp;
