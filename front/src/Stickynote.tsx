import React, {PropsWithChildren} from 'react';

const Stickynote: React.FC<PropsWithChildren> = ({children}) => {
  return (
      <div className="quote-container">
        <i className="pin"></i>
        <blockquote className="note yellow">
          {children}
        </blockquote>
      </div>
  )
};

export default Stickynote;
