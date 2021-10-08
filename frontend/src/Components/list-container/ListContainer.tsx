import React from 'react'
import TodoList from "./todo-list/TodoList";


//component imports
import {Fab, Toolbar} from "@mui/material";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import ScrollTop from "./scroll-to-top/ScrollToTop";
import {ITodoStatus} from "../../interfaces/ITodo";

//interface imports

type Props = {};



function ListContainer(props: Props){
    const lists = (Object.keys(ITodoStatus) as Array<keyof typeof ITodoStatus>).map(key => <TodoList todoStatus={ITodoStatus[key]} />)
    return(<div>
        <Toolbar id="back-to-top-anchor"/>
            {lists}
    <ScrollTop {...props}>
        <Fab color="secondary" size="small" aria-label="scroll back to top">
            <KeyboardArrowUpIcon/>
        </Fab>
    </ScrollTop></div>
    )


}

export default ListContainer;

