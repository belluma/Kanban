import React from 'react'
import {ITodoStatus} from "../../../interfaces/ITodo";
import TodoList from "../todo-list/TodoList";

//component imports

//interface imports

type Props = {};

function DesktopListContainer(props: Props){
    const lists = (Object.keys(ITodoStatus) as Array<keyof typeof ITodoStatus>).map(key => <TodoList todoStatus={ITodoStatus[key]} key={key} />)

    return(
       <div>{lists}</div>
    )
}

export default DesktopListContainer;
