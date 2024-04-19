import React, {useEffect, useState} from 'react';

interface ImageLoaderProps {
    src: string
}

const ImageLoader: React.FC<ImageLoaderProps> = ({src}) => {
    const [loaded, setLoaded] = useState(false);
    const [timingOk, setTimingOk] = useState(false);
    const [clicked, setClicked] = useState(false);

    useEffect(() => {
        const timer = setTimeout(() => {
            setTimingOk(true);
        }, 5000);

        return () => clearTimeout(timer);
    }, []);

    const redirect = () => {
        window.location.href = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    }

    return (
        <div className="App-container">
            {!(loaded && timingOk) && (
                <div className="App-fallback">
                    <div className="loader-container">
                        <div className="loader-text">Patience, elle arrive ...</div>
                        <div className="loader"></div>
                        <img className="loader-img" src="./tink.png" alt="tink"/>
                    </div>
                </div>
            )}
            <img src={clicked ? "./stereogram.jpg" : src} className="App-logo" alt="logo" onLoad={() => setLoaded(true)}/>
            <div className="App-target" onDoubleClick={() => setClicked(true)}></div>
            <div className="App-cible" onDoubleClick={redirect}></div>
        </div>
    )
};

export default ImageLoader;
