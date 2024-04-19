import React, {useEffect, useState} from 'react';

interface ImageLoaderProps {
    src: string
}

const ImageLoader: React.FC<ImageLoaderProps> = ({src}) => {
    const [loaded, setLoaded] = useState(false);
    const [timingOk, setTimingOk] = useState(false);

    useEffect(() => {
        const timer = setTimeout(() => {
            setTimingOk(true);
        }, 5000);

        return () => clearTimeout(timer);
    }, []);

    const redirect = (url: string) => {
        window.location.href = url;
    }

    let onDoubleClick = (event: React.MouseEvent<HTMLDivElement>) => {
        const divElement = event.target;
        const absoluteX = (document.documentElement.scrollLeft || document.body.scrollLeft) + event.clientX ;
        const absoluteY = (document.documentElement.scrollTop || document.body.scrollTop) + event.clientY ;
        if (divElement instanceof HTMLElement) {
            let relativeX = absoluteX / divElement.offsetWidth;
            let relativeY = absoluteY / divElement.offsetWidth;
            if(relativeX>0.93 &&relativeX<0.95 && relativeY>0.67 &&relativeY<0.69) {
                redirect("stereogram2.jpg");
            }
        }
    }

    return (
        <div className="App-container" onDoubleClick={onDoubleClick}>
            {!(loaded && timingOk) && (
                <div className="App-fallback">
                    <div className="loader-container">
                        <div className="loader-text">Patience, elles arrivent ...</div>
                        <div className="loader"></div>
                        <img className="loader-img" src="./tink.png" alt="tink"/>
                    </div>
                </div>
            )}
            <img src={src}
                 className="App-logo"
                 alt="logo"
                 onLoad={() => setLoaded(true)}/>
            <div className="App-target" onDoubleClick={() => redirect("stereogram.jpg")}></div>
            <div className="App-cible" onDoubleClick={() => redirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ")}></div>
        </div>
    )
};

export default ImageLoader;
