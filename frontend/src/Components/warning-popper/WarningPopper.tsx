import React, {useRef} from 'react'
import {useAppDispatch, useAppSelector} from "../../app/hooks";
import {closeError, selectError, selectErrorMessage, selectErrorStatus} from "../list-container/TodoListSlicer";
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
    Popper,
    Typography
} from "@mui/material";


//interface imports


type Props = {
    anchorRef: HTMLElement | null
};

function WarningPopper({anchorRef}: Props) {
    const dispatch = useAppDispatch();
    const status = useAppSelector(selectErrorStatus);
    const message = useAppSelector(selectErrorMessage);
    const error = useAppSelector(selectError);

    return (
        <Popper open={error} anchorEl={anchorRef} transition>
            {({TransitionProps}) => (
                <Fade {...TransitionProps} timeout={350}>
                    <Box sx={{border: 1, p: 1, bgcolor: 'background.paper'}}>
                        <Card>
                            <CardHeader avatar={
                                <Avatar sx={{bgcolor: "red"}} aria-label="warning">
                                    R
                                </Avatar>
                            } title={`Error Code: ${status}`}/>
                            <CardContent>
                                <Typography variant="body2">
                                    {message}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Button onClick={() => {dispatch(closeError())}} variant="outlined" >Ok</Button>
                            </CardActions>
                        </Card>
                    </Box>
                </Fade>
            )}
        </Popper>
    )
}

export default WarningPopper;
