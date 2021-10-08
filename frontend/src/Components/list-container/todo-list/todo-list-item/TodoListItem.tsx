import React from 'react'
import {ListItem, ListItemIcon} from "@mui/material";

//component imports
import Todo from "./todo/Todo";

//interface imports
import {ITodo} from "../../../../interfaces/ITodo";
type Props = {todo: ITodo}

function TodoListItem({todo}: Props){
    const {id, title, description, status} = todo
    const handleToggle = () => {
    };
    return(
        <ListItem
            key={id}
            role="listitem"
            // button
            // onClick={handleToggle()}
        >
            <ListItemIcon>

            </ListItemIcon>
            <Todo id={id} title={title} description={description} status={status}/>
        </ListItem>
    )
}

export default TodoListItem;
