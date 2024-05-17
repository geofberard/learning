import React, {useState} from 'react';
import './GameLoginHelp.css';
import Cell from "./Cell";

const gridCorrection: boolean[][] = [
    [false,false,false,false,false,false,false,true,false,true,false,true,false,true,false,false,false,false,false,false,false],
    [false,true,true,true,true,true,false,true,true,true,false,false,true,true,false,true,true,true,true,true,false],
    [false,true,false,false,false,true,false,true,false,false,true,true,true,true,false,true,false,false,false,true,false],
    [false,true,false,false,false,true,false,true,false,false,true,true,false,true,false,true,false,false,false,true,false],
    [false,true,false,false,false,true,false,true,false,true,true,false,false,true,false,true,false,false,false,true,false],
    [false,true,true,true,true,true,false,true,true,false,false,true,true,true,false,true,true,true,true,true,false],
    [false,false,false,false,false,false,false,true,false,true,false,true,false,true,false,false,false,false,false,false,false],
    [true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true],
    [false,false,false,false,true,true,false,true,false,false,false,false,true,false,true,true,false,false,false,true,false],
    [false,true,false,true,false,false,true,false,false,false,false,false,false,true,true,false,false,false,false,false,true],
    [false,false,false,false,true,true,false,false,true,false,false,true,true,true,true,true,false,false,true,false,true],
    [false,false,true,false,false,false,true,true,true,true,false,true,false,false,true,false,true,true,true,false,true],
    [true,true,true,true,true,true,false,true,true,false,true,false,false,true,true,false,true,false,true,true,true],
    [true,true,true,true,true,true,true,true,false,false,true,true,false,false,false,true,true,true,true,true,false],
    [false,false,false,false,false,false,false,true,true,true,true,false,true,false,true,true,false,true,false,true,true],
    [false,true,true,true,true,true,false,true,true,false,true,true,true,false,true,false,false,false,true,true,false],
    [false,true,false,false,false,true,false,true,true,true,false,true,true,true,false,true,true,true,false,true,true],
    [false,true,false,false,false,true,false,true,false,true,true,true,false,true,true,false,false,false,true,true,true],
    [false,true,false,false,false,true,false,true,false,true,false,false,true,true,false,true,true,true,true,true,true],
    [false,true,true,true,true,true,false,true,false,true,true,false,false,true,false,true,false,true,true,false,true],
    [false,false,false,false,false,false,false,true,false,true,true,false,true,true,true,false,false,true,true,true,true],
]

const gridInitial: boolean[][] = [
    [false,false,false,true,false,true,true,false,true,false,false,false,true,false,false,false,false,true,false,true,false],
    [false,true,true,true,false,false,true,false,true,true,false,true,true,false,true,true,false,true,false,true,false],
    [false,false,true,true,false,true,false,false,true,true,false,true,true,false,true,true,false,true,false,false,false],
    [false,true,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true],
    [false,false,false,true,false,true,true,false,true,false,false,true,true,false,false,false,false,true,true,false,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,false,false,true,true,true,false,false,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,false,false,false,false,true,false,false,false,false,true,true,true,true,true,true],
    [true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,true],
    [true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,true],
    [true,true,true,true,true,true,false,false,false,false,false,false,false,false,false,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,false,false,false,false,false,false,false,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,false,false,false,false,false,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,false,false,false,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
]

const cleanGrid: boolean[][] = [
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
    [true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true],
]

const rowRules: string[] = [
    "7 1 1 1 7",
    "1 1 2 1 1",
    "1 3 1 2 1 3 1",
    "1 3 1 2 1 1 3 1",
    "1 3 1 1 2 1 3 1",
    "1 1 2 1 1",
    "7 1 1 1 7",
    "1",
    "4 1 4 1 3 1",
    "1 1 2 6 5",
    "4 2 2 1",
    "2 3 1 2 1 1",
    "1 1 2 1 1",
    "2 3 1",
    "7 1 1 1 1",
    "1 1 1 1 3 1",
    "1 3 1 1 1 1",
    "1 3 1 1 1 3",
    "1 3 1 1 2 1",
    "1 1 1 2 1 1 1",
    "7 1 1 2",
]

const colRules: string[] = [
    "7 4 7",
    "1 1 1 2 1 1",
    "1 3 1 2 1 3 1",
    "1 3 1 1 2 1 3 1",
    "1 3 1 1 1 1 3 1",
    "1 1 1 1 1 1",
    "7 1 1 1 7",
    "2",
    "1 3 1 2 1 4",
    "2 1 3 2 1",
    "2 2 4 1 1",
    "1 1 2 1 1 3",
    "1 2 2 1 3 1 1",
    "1 1 3",
    "7 1 1 2",
    "1 1 1 2 1 1 1",
    "1 3 1 3 2 1 2",
    "1 3 1 3 1 1 1",
    "1 3 1 2 1 1",
    "1 1 3 1",
    "7 1 1 1",
]

const compareBooleanArrays = (arr1: boolean[], arr2: boolean[]): boolean => {
    if (arr1.length !== arr2.length) {
        return false;
    }

    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) {
            return false;
        }
    }

    return true;
}

const extractColumn = (colIndex: number, array: boolean[][]) => array.map(row => row[colIndex]);

const checkLine = (rowIndex: number, gridState: boolean[][], gridCorrection: boolean[][]) => {
    return compareBooleanArrays(gridState[rowIndex], gridCorrection[rowIndex]) ? "done" : "";
}

const checkColumn = (colIndex: number, gridState: boolean[][], gridCorrection: boolean[][]) => {
    return compareBooleanArrays(extractColumn(colIndex, gridState), extractColumn(colIndex, gridCorrection)) ? "done" : "";
}

const initGrid: () => boolean[][] = () => {
    return gridInitial;
}

const GameLoginHelp: React.FC = () => {
    const [gridState, setGridState] = useState(initGrid());

    const handleClick = (rowIndex: number, columnIndex: number) => {
        const newGridState = [...gridState];
        newGridState[rowIndex][columnIndex] = !newGridState[rowIndex][columnIndex];
        setGridState(newGridState);
    };

    return (
        <div className="game-login-help">
            <table className="grid">
                <tr>
                    <th/>
                    {colRules.map((colRules, colIndex) => (
                        <th className={`colHeader ${checkColumn(colIndex, gridState, gridCorrection)}`}>{colRules.replaceAll(" ","")}</th>
                    ))}
                </tr>
                {Array.from({ length: 21 }, () => Array(21).fill(0)).map((row, rowIndex) => (
                    <tr key={rowIndex} className="row">
                        <th className={`rowHeader ${checkLine(rowIndex, gridState, gridCorrection)}`}>
                            {rowRules[rowIndex]}
                        </th>
                        {row.map((cell, colIndex) => (
                            <Cell label={""}
                                  checked={!gridState[rowIndex][colIndex]}
                                  onClick={() => handleClick(rowIndex, colIndex)}/>
                        ))}
                    </tr>
                ))}
            </table>
            <button onClick={() => setGridState(cleanGrid)}>Let's go</button>
        </div>
    );
};

export default GameLoginHelp;
