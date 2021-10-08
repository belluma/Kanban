import React from 'react'
import {ITodoStatus} from "../../../interfaces/ITodo";
import TodoList from "../todo-list/TodoList";
import {Container, Grid} from "@mui/material";
import ButtonContainer from "./button-container/ButtonContainer";

//component imports

//interface imports

type Props = {};

function DesktopListContainer(props: Props) {
    const lists = (Object.keys(ITodoStatus) as Array<keyof typeof ITodoStatus>).map(key =>
        <TodoList todoStatus={ITodoStatus[key]} key={key}/>)

    return (

        <Grid container spacing={2} justifyContent="center" alignItems="center">
            <Grid item>
                {lists[0]}
            </Grid>
            <ButtonContainer/>
            <Grid item>
                {lists[1]}
            </Grid>
            <ButtonContainer/>

            <Grid item>
                {lists[2]}
            </Grid>
        </Grid>
    )
}

export default DesktopListContainer;
