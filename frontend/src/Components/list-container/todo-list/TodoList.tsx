import React, {} from 'react'
import { useAppSelector} from "../../../app/hooks";
import { todosByStatus} from "../TodoListSlicer";

//component imports
import {List,  Paper} from "@mui/material";
import TodoListItem from "./todo-list-item/TodoListItem";

//interface imports
import {ITodoStatus} from "../../../interfaces/ITodo";

type Props = {
    todoStatus: ITodoStatus;
};

function TodoList({todoStatus}:Props){
    const todos = useAppSelector(todosByStatus[todoStatus]);
    return(
        <Paper sx={{ width: 400, height: 400, maxHeight:400, overflow: 'auto' }}>
            <List dense component="div" role="list">
                {todos.map((todo) => <TodoListItem todo={todo} key={todo.id}/>)}
            </List>
        </Paper>
    )
}

export default TodoList;
