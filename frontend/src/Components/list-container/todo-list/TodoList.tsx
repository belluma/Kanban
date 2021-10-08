import React, {useEffect} from 'react'
import {useAppDispatch} from "../../../app/hooks";

//component imports
//interface imports
import {ITodoStatus} from "../../../interfaces/ITodo";
import Todo from "./todo-list-item/todo/Todo";
import {getApiData} from "../TodoListSlicer";

type Props = {
    todoStatus: ITodoStatus;
};

function TodoList({todoStatus}:Props){
    const dispatch = useAppDispatch();
    useEffect(() =>{
        dispatch(getApiData())
    })
    const dummy = [...Array(50)].map(item => <li style={{fontSize:30}}>Life ho! sail to be robed.</li>)
    const todoList = [<Todo id={1} title="title" description="description" status={ITodoStatus.DONE}/>]
    return(
       <ul>{todoList}</ul>
    )
}

export default TodoList;
