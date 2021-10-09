import React from 'react'
import {useDispatch} from "react-redux";
import {
    checkTodos,
} from "../../desktop-list-container/button-container/ButtonSlicer";

//component imports
import Todo from "./todo/Todo";
import {Checkbox, ListItem, ListItemIcon} from "@mui/material";

//interface imports
import {ITodo} from "../../../../interfaces/ITodo";

type Props = { todo: ITodo }

function TodoListItem({todo}: Props) {
    const dispatch = useDispatch();

    const {id, title, description, status} = todo
    const handleToggle = (e: React.MouseEvent) => {
        console.log(e)
    };
    return (
        <ListItem
            key={id}
            role="listitem"

        >
            <ListItemIcon>
                <Checkbox
                    // checked={checked}
                    tabIndex={-1}
                    disableRipple
                    onClick={() => dispatch(checkTodos({id, status}))}
                    // inputProps={{
                    //     'aria-labelledby': labelId,
                    // }}
                />
            </ListItemIcon>
            <Todo id={id} title={title} description={description}
                  status={status}/>
        </ListItem>
    )
}

export default TodoListItem;
