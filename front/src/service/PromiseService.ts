// @ts-ignore
const baseApiUrl = process.env.REACT_APP_API_URL || "";

export const fetchText = (api: string) => fetch(`${baseApiUrl}${api}`).then((res) => res.text());
