import React, {useEffect} from 'react'
import {useAppDispatch, useAppSelector} from "../../../app/hooks";

//component imports
//interface imports
import {ITodoStatus} from "../../../interfaces/ITodo";
import Todo from "./todo-list-item/todo/Todo";
import {getApiData, todosByStatus} from "../TodoListSlicer";
import {List, ListItem, Paper} from "@mui/material";
import TodoListItem from "./todo-list-item/TodoListItem";

type Props = {
    todoStatus: ITodoStatus;
};

function TodoList({todoStatus}:Props){
    const dispatch = useAppDispatch();
    const todos = useAppSelector(todosByStatus[todoStatus]);
    const todoList = todos.map(todo => <Todo id={todo.id} title={todo.title} description={todo.description} status={todo.status}/>)
    return(
        <Paper sx={{ width: 400, height: 400, maxHeight:400, overflow: 'auto' }}>
            <List dense component="div" role="list">
                {todos.map((todo) => <TodoListItem todo={todo} key={todo.id}/>)}
                {/*<ListItem />*/}
            </List>
        </Paper>
    )
}

export default TodoList;
