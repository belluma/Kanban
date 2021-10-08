import React from 'react'

//component imports
import {Card, CardContent, IconButton, Typography} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";

//interface imports
import {ITodo} from "../../../interfaces/ITodo";


function Todo({id, title, description}: ITodo){
    const deleteTodo =  (id:number) => id
    return(
        <Card sx={{ minWidth: 275 }}>
            <CardContent>
                <Typography sx={{display:"inline", float:"left"}} variant="h5" component="div">
                    {title}
                </Typography>
                <IconButton sx={{display:"inline", float:"right", }} onClick={() => deleteTodo(id)}>
                    {/*//.then(()=>dispatch(getApiData()))}>*/}
                    <DeleteIcon />
                </IconButton>
            </CardContent>
        </Card>
    )
}

export default Todo;
