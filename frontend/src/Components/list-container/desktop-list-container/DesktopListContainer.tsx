import React from 'react'

//component imports
import {Grid} from "@mui/material";
import ButtonContainer from "./button-container/ButtonContainer";
import TodoList from "../todo-list/TodoList";

//interface imports
import {ITodoStatus} from "../../../interfaces/ITodo";

type Props = {};

function DesktopListContainer(props: Props) {
    const lists = (Object.keys(ITodoStatus) as Array<keyof typeof ITodoStatus>).map(key =>
        <TodoList todoStatus={ITodoStatus[key]} key={key}/>)

    return (
        <Grid container spacing={2} justifyContent="center" alignItems="center">
            <Grid item>
                {lists[0]}
            </Grid>
            <ButtonContainer index={0} />
            <Grid item>
                {lists[1]}
            </Grid>
            <ButtonContainer index={1}/>
            <Grid item>
                {lists[2]}
            </Grid>
        </Grid>
    )
}

export default DesktopListContainer;
