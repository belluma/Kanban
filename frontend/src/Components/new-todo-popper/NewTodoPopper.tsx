import React, {useRef, useState} from 'react'
import {closeError} from "../list-container/TodoListSlicer";
import {useAppDispatch} from "../../app/hooks";

//component imports
import {
    Avatar,
        Box,
        Button,
        Card,
        CardActions,
        CardContent,
        CardHeader,
        Fade,
        Grid,
        Popper,
        Typography
} from "@mui/material";
import TextField from '@mui/material/TextField';

//interface imports

type Props = {
    anchorRef: HTMLElement | null
};

function NewTodoPopper({anchorRef}:Props, ref: any){
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [deadline, setDeadline] = useState(new Date());
    const dispatch = useAppDispatch();


    return(
                <Popper open={true} anchorEl={anchorRef} transition>
                    {({TransitionProps}) => (
                        <Fade {...TransitionProps} timeout={350}>
                            <Box sx={{border: 1, p: 1, bgcolor: 'background.paper'}}>
                                <Card>
                                    <CardHeader>
                                        <TextField></TextField>
                                    </CardHeader>

                                    <CardContent>
                                        <TextField></TextField>

                                    </CardContent>
                                    <CardActions>
                                        <Button onClick={() => {dispatch(closeError())}} variant="outlined" >save</Button>
                                    </CardActions>
                                </Card>
                            </Box>
                        </Fade>
                    )}
                </Popper>
    )
}

export default NewTodoPopper;
