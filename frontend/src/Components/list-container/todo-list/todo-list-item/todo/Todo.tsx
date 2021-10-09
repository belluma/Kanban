import React, {useRef, useState} from 'react'

//component imports
import {Card, CardContent,  IconButton, Typography} from "@mui/material";
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
    const deleteTodo =  (id:number) => id

    const anchorRef = useRef(null);

    const showDetails = (e:React.MouseEvent) => {
        setDetails(!details)
    }
    return(
        <Card onClick={showDetails} sx={{ minWidth: 275, cursor:"pointer" }}>
            <CardContent ref={anchorRef} >
                <Typography sx={{display:"inline", float:"left",}} variant="h5" component="div">
                    {title}
                </Typography>
                <IconButton sx={{display:"inline", float:"right", }} onClick={() => deleteTodo(id)}>
                    {/*//.then(()=>dispatch(getApiData()))}>*/}
                    <DeleteIcon />
                </IconButton>
            </CardContent>
            <TodoDetails todo={todo} open={details} anchorRef={anchorRef.current} />
        </Card>
    )
}

export default Todo;
