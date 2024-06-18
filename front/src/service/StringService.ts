const normalizeString : (str: string) => string = (str) => {
    return str
        .normalize("NFD")                                 // Décompose les caractères accentués
        .replace(/[\u0300-\u036f]/g, "") // Supprime les diacritiques
        .replace(/[^\w\s]|_/g, "")       // Supprime la ponctuation
        .replace(/\s+/g, " ")            // Remplace les espaces multiples par un seul espace
        .trim()                                                 // Supprime les espaces en début et fin de chaîne
        .toLowerCase();
}

const levenshteinDistance : (a: string, b: string) => number = (a,b) => {
    const matrix = Array.from({ length: a.length + 1 }, (_, i) => Array(b.length + 1).fill(0));

    for (let i = 0; i <= a.length; i++) {
        matrix[i][0] = i;
    }
    for (let j = 0; j <= b.length; j++) {
        matrix[0][j] = j;
    }

    for (let i = 1; i <= a.length; i++) {
        for (let j = 1; j <= b.length; j++) {
            const cost = a[i - 1] === b[j - 1] ? 0 : 1;
            matrix[i][j] = Math.min(
                matrix[i - 1][j] + 1,     // deletion
                matrix[i][j - 1] + 1,     // insertion
                matrix[i - 1][j - 1] + cost  // substitution
            );
        }
    }

    return matrix[a.length][b.length];
}

export const compareStrings : (expected: string, input: string, tolerance?: number) => boolean = (expected, input, tolerance = 2) => {
    const normalizedExpected = normalizeString(expected);
    const normalizedInput = normalizeString(input);
    const distance = levenshteinDistance(normalizedExpected, normalizedInput);
    console.log("comparing", expected, input, tolerance, distance);
    return distance <= tolerance;
}