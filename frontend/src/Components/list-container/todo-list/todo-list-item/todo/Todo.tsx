import React, { useRef, useState} from 'react'
import { deleteTodo } from '../../../../../services/apiService';
import {useAppDispatch} from "../../../../../app/hooks";
import {getApiData} from "../../../TodoListSlicer";
//component imports
import {Card, CardContent, ClickAwayListener, IconButton, Typography} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import TodoDetails from "../todo-details/TodoDetails";

//interface imports
import {ITodo} from "../../../../../interfaces/ITodo";


type Props = {
    todo: ITodo
}

function Todo({todo}: Props){
    const {id, title, description, status} = todo
    const [details, setDetails] = useState(false);
    const dispatch = useAppDispatch();
    const anchorRef = useRef(null);
    const deleteRef = useRef(null);

    const showDetails = (e: React.MouseEvent) => {
        // @ts-ignore
        if(deleteRef.current !== null && deleteRef.current.contains(e.target)) return
        setDetails(!details);
    }
    const closeDetails = () => {
        setDetails(false)
    }
    const deleteAndUpdate = () =>{
        deleteTodo([id])
            .then(() => dispatch(getApiData()))
    }

    return(
        <ClickAwayListener onClickAway={closeDetails}>
        <Card onClick={showDetails} sx={{ minWidth: 275, cursor:"pointer" }}>
            <CardContent ref={anchorRef} >
                <Typography sx={{display:"inline", float:"left",}} variant="h5" component="div">
                    {title}
                </Typography>
                <IconButton ref={deleteRef} sx={{display:"inline", float:"right", }} onClick={deleteAndUpdate}>
                    <DeleteIcon />
                </IconButton>
            </CardContent>
            <TodoDetails todo={todo} open={details} anchorRef={anchorRef.current} closeDetails={closeDetails}/>
        </Card>
        </ClickAwayListener>
    )
}

export default Todo;
