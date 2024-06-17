const COOKIE_NAME = "question";

export const getCookie = () => {
    const name = `${COOKIE_NAME}=`;
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === " ") {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length) ;
        }
    }
    return undefined;
};

export const setCookie = (id: string | undefined) => {
    const d = new Date();
    d.setTime(d.getTime() + 10 * 365 * 24 * 60 * 60 * 1000);
    document.cookie = `${COOKIE_NAME}=${id};expires=${d.toUTCString()}`;
};

export const removeCookie = () => {
    document.cookie = `${COOKIE_NAME}=;expires=Thu, 01 Jan 1970 00:00:00 GMT`;
};
