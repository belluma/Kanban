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
    Grid,
    Popper,
    Typography
} from "@mui/material";


//interface imports


type Props = {
};

function WarningPopper(props: Props) {
    const dispatch = useAppDispatch();
    const status = useAppSelector(selectErrorStatus);
    const message = useAppSelector(selectErrorMessage);
    const error = useAppSelector(selectError);
    const errorAnchor = useRef(null);

    return (
        <Grid container justifyContent="center" alignItems="center">
            <Grid ref={errorAnchor} item>
        <Popper open={error} anchorEl={errorAnchor.current} transition>
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
            </Grid>
        </Grid>
    )
}

export default WarningPopper;
