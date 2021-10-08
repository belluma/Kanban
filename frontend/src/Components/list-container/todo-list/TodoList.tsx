import React, {useEffect} from 'react'
import {useAppDispatch, useAppSelector} from "../../../app/hooks";

//component imports
//interface imports
import {ITodoStatus} from "../../../interfaces/ITodo";
import Todo from "./todo-list-item/todo/Todo";
import {getApiData, todosByStatus} from "../TodoListSlicer";

type Props = {
    todoStatus: ITodoStatus;
};

function TodoList({todoStatus}:Props){
    const dispatch = useAppDispatch();
    const todos = useAppSelector(todosByStatus[todoStatus]);
    const todoList = todos.map(todo => <Todo id={todo.id} title={todo.title} description={todo.description} status={todo.status}/>)
    return(
       <ul>{todoList}</ul>
    )
}

export default TodoList;
