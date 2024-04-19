import React from 'react';

interface StickynoteProps {
    text: string;
    author: string;
}

const Stickynote: React.FC<StickynoteProps> = ({text, author}) => {
    return (
        <div className="quote-container">
            <i className="pin"></i>
            <blockquote className="note yellow">
                {text}
                <cite className="author">{author}</cite>
            </blockquote>
        </div>
    )
};

export default Stickynote;
