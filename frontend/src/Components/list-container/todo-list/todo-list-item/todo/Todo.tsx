import React, { useRef, useState} from 'react'

//component imports
import {Card, CardContent, ClickAwayListener, IconButton, Typography} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";

//interface imports
import {ITodo} from "../../../../../interfaces/ITodo";
import TodoDetails from "../todo-details/TodoDetails";

type Props = {
    todo: ITodo
}

function Todo({todo}: Props){
    const {id, title, description, status} = todo
    const [details, setDetails] = useState(false);
    const deleteTodo = (id: number) => id;

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
    return(
        <ClickAwayListener onClickAway={closeDetails}>
        <Card onClick={showDetails} sx={{ minWidth: 275, cursor:"pointer" }}>
            <CardContent ref={anchorRef} >
                <Typography sx={{display:"inline", float:"left",}} variant="h5" component="div">
                    {title}
                </Typography>
                <IconButton ref={deleteRef} sx={{display:"inline", float:"right", }} onClick={() => deleteTodo(id)}>
                    {/*//.then(()=>dispatch(getApiData()))}>*/}
                    <DeleteIcon />
                </IconButton>
            </CardContent>
            <TodoDetails todo={todo} open={details} anchorRef={anchorRef.current} closeDetails={closeDetails}/>
        </Card>
        </ClickAwayListener>
    )
}

export default Todo;
