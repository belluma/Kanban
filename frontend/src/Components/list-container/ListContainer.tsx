import React from 'react'
import TodoList from "./todo-list/TodoList";


//component imports
import {Fab, Toolbar, useScrollTrigger} from "@mui/material";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import ScrollTop from "./scroll-to-top/ScrollToTop";

//interface imports

type Props = {};

function ListContainer(props: Props){
    return(<div>
        <Toolbar id="back-to-top-anchor"/>
        <TodoList />
    <ScrollTop {...props}>
        <Fab color="secondary" size="small" aria-label="scroll back to top">
            <KeyboardArrowUpIcon/>
        </Fab>
    </ScrollTop></div>
    )


}

export default ListContainer;

