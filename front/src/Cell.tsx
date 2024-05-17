import React from 'react';
import './Cell.css';

interface CellProps {
    label: string,
    checked: boolean,
    onClick: ()=>void
}
const Cell: React.FC<CellProps> = ({label, checked, onClick}) => {
    return (
        <td className={`cell ${checked ? "checked" : ""}`}
            onClick={() => onClick()}>
            {label}
        </td>
    )
}

export default Cell;
